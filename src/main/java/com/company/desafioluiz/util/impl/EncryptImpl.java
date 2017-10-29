package com.company.desafioluiz.util.impl;

import com.company.desafioluiz.util.Encrypt;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class EncryptImpl implements Encrypt {

    @Override
    public String encryptPassword(String originalPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(originalPassword.getBytes("UTF-8"));

        StringBuilder passwordEncrypted = new StringBuilder();
        for (byte b : messageDigest) {
            passwordEncrypted.append(String.format("%02X", 0xFF & b));
        }

        return passwordEncrypted.toString();
    }

}
