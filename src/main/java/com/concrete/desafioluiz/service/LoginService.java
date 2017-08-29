package com.concrete.desafioluiz.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concrete.desafioluiz.exception.InvalidCredentialsException;
import com.concrete.desafioluiz.model.User;
import com.concrete.desafioluiz.repository.UserRepositoryInterface;
import com.concrete.desafioluiz.util.EncryptUtil;

@Service
public class LoginService {
	
	@Autowired 
	UserRepositoryInterface userRepository;
	
	public User doLogin(User user) throws InvalidCredentialsException, UnsupportedEncodingException, NoSuchAlgorithmException{
		List<User> usersBD = userRepository.getUserByEmailAndPassword(user.getEmail(), EncryptUtil.encryptPassword(user.getPassword()));
		if(usersBD != null && !usersBD.isEmpty()) {
			return usersBD.get(0);
		}else {
			throw new InvalidCredentialsException();
		}
	}
}
