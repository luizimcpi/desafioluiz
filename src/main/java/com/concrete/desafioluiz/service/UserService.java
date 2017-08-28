package com.concrete.desafioluiz.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concrete.desafioluiz.exception.EmailAlreadyExistsException;
import com.concrete.desafioluiz.model.User;
import com.concrete.desafioluiz.repository.UserRepositoryInterface;
import com.concrete.desafioluiz.util.EncryptUtil;

@Service
public class UserService {
	
	@Autowired 
	UserRepositoryInterface userRepository;
	
	public void save(User user) throws EmailAlreadyExistsException, UnsupportedEncodingException, NoSuchAlgorithmException {
		if(!userRepository.userExists(user.getEmail())) {
			user.setPassword(EncryptUtil.encryptPassword(user.getPassword()));
			user.setToken(this.generateUserToken());
			user.setCreated(LocalDateTime.now());
			user.setLast_login(LocalDateTime.now());
			userRepository.addUser(user);
		}else {
			throw new EmailAlreadyExistsException();
		}
		
	}
	
	public User findById(long id){
		return userRepository.getUserById(id);
	}
	
	
	public List<User> getAllUsers(){
		return userRepository.getAllUsers();
	}
	
	private String generateUserToken() {
		Calendar cal = Calendar.getInstance();
		return UUID.randomUUID().toString().toUpperCase() 
	            + "|" + "userid" + "|"
	            + cal.getTimeInMillis();
	}
}
