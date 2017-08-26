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
		
		List<User> users =  userRepository.getAllUsers();
		if(users != null && !users.isEmpty()) {
			for (User user : users) {
				//pegar os telefones criar método no phone repository
			}
		}
		
		
		return users;
	}
	
}
