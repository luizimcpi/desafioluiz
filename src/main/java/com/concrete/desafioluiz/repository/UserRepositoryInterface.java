package com.concrete.desafioluiz.repository;

import java.util.List;

import com.concrete.desafioluiz.model.User;

public interface UserRepositoryInterface {
	  	List<User> getAllUsers();
	    User getUserById(Long userId);
	    void addUser(User user);
	    void updateUser(User user);
	    void deleteUser(Long userId);
	    boolean userExists(String email);
	    List<User> getUserByEmailAndPassword(String email, String password);
}
