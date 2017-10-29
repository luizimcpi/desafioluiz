package com.company.desafioluiz.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface Encrypt {

    public String encryptPassword(String originalPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException;

}
