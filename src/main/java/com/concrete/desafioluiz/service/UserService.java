package com.concrete.desafioluiz.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concrete.desafioluiz.exception.EmailAlreadyExistsException;
import com.concrete.desafioluiz.model.User;
import com.concrete.desafioluiz.repository.UserRepositoryInterface;

@Service
public class UserService {
	
	@Autowired 
	UserRepositoryInterface userRepository;
	
	public void save(User user) throws EmailAlreadyExistsException {
		if(!userRepository.userExists(user.getEmail())) {
			user.setToken(this.generateUserToken());
			Date data = new Date(System.currentTimeMillis());  
			SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy"); 
			user.setCreated(formatarDate.format(data));
			user.setLast_login(formatarDate.format(data));
			userRepository.addUser(user);
		}else {
			throw new EmailAlreadyExistsException();
		}
		
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
