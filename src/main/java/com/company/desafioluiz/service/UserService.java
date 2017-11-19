package com.company.desafioluiz.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.desafioluiz.exception.EmailAlreadyExistsException;
import com.company.desafioluiz.exception.InvalidSessionException;
import com.company.desafioluiz.exception.TokenInvalidException;
import com.company.desafioluiz.model.User;
import com.company.desafioluiz.repository.UserRepository;
import com.company.desafioluiz.util.LoginUtil;
import com.company.desafioluiz.util.impl.EncryptImpl;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EncryptImpl encrypt;

    public void save(User user) throws EmailAlreadyExistsException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            final String encryptedPassword = encrypt.encryptPassword(user.getPassword());
            User newUser = new User(user.getName(), user.getEmail(), encryptedPassword, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), "");
            userRepository.save(newUser);
        } else {
            throw new EmailAlreadyExistsException();
        }

    }

    public User findById(long id, String token) throws TokenInvalidException {
        if (token == null || token.isEmpty()) {
            throw new TokenInvalidException();
        }

        User userBD = userRepository.findById(id);

        if (userBD != null) {
            if (!userBD.getToken().equals(token)) {
                throw new TokenInvalidException();
            }

            if (LoginUtil.lastLoginMoreThanThirtyMinutes(userBD)) {
                throw new InvalidSessionException();
            }

        }
        return userBD;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
