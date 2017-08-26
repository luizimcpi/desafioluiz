package com.concrete.desafioluiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concrete.desafioluiz.model.User;
import com.concrete.desafioluiz.repository.UserRepositoryInterface;

@Service
public class UserService {
	
	@Autowired 
	UserRepositoryInterface userRepository;
	
	public void save(User user){
		userRepository.addUser(user);
	}
	
	public List<User> getAllUsers(){
		return userRepository.getAllUsers();
	}
	
}
