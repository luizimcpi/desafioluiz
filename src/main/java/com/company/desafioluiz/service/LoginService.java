package com.company.desafioluiz.service;

import com.company.desafioluiz.exception.InvalidCredentialsException;
import com.company.desafioluiz.model.User;
import com.company.desafioluiz.repository.UserRepositoryInterface;
import com.company.desafioluiz.util.impl.EncryptImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    UserRepositoryInterface userRepository;

    @Autowired
    EncryptImpl encrypt;

    public User doLogin(User user) throws InvalidCredentialsException, UnsupportedEncodingException, NoSuchAlgorithmException {
        final String passwordCriptografado = encrypt.encryptPassword(user.getPassword());
        final List<User> usersBD = userRepository.getUserByEmailAndPassword(user.getEmail(), passwordCriptografado);
        if (usersBD != null && !usersBD.isEmpty()) {
            return usersBD.get(0);
        } else {
            throw new InvalidCredentialsException();
        }
    }
}
