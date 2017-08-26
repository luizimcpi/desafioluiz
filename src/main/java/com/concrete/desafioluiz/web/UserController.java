package com.concrete.desafioluiz.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.concrete.desafioluiz.exception.EmailAlreadyExistsException;
import com.concrete.desafioluiz.model.User;
import com.concrete.desafioluiz.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

		@Autowired 
		UserService userService;
	
		@RequestMapping(method = RequestMethod.GET)
	    public ResponseEntity<List<User>> listAll() {
	        List<User> users = userService.getAllUsers();
	        if(users.isEmpty()){
	            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	    }
		
	  	@RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<User> createUser(@RequestBody User user) throws EmailAlreadyExistsException {
	        userService.save(user);
	        return new ResponseEntity<User>(user, HttpStatus.CREATED);
	    }
	  	
	  	@ExceptionHandler(EmailAlreadyExistsException.class)
	    public String handleEmailException() {
	  		return "Email já cadastrado";
	    }
	
	  	/*
	  	 * JSON exemplo
	  	 * {
	  	 * 	"name" : "Joao da Silva",
	  	 * 	"email" : "joao@uol.com.br",
	  	 * 	"password" : "hunter2",
	  	 *  "phones" : [
	  	 *  	{
	  	 *  		"number" : "987654321",
	  	 *  		"ddd" : "21"
	  	 *  	}
	  	 *    ]
	  	 * }   
	  	 * 
	  	 * */
	
}
