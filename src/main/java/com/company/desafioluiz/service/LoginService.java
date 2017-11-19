package com.company.desafioluiz.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.desafioluiz.exception.InvalidCredentialsException;
import com.company.desafioluiz.model.User;
import com.company.desafioluiz.repository.UserRepository;
import com.company.desafioluiz.util.impl.EncryptImpl;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EncryptImpl encrypt;

    public User doLogin(User user) throws InvalidCredentialsException, UnsupportedEncodingException, NoSuchAlgorithmException {
        final String passwordCriptografado = encrypt.encryptPassword(user.getPassword());
        final User userBD = userRepository.findByEmailAndPassword(user.getEmail(), passwordCriptografado);
        if (userBD != null) {
            return userBD;
        } else {
            throw new InvalidCredentialsException();
        }
    }
}
