package com.concrete.desafioluiz.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.concrete.desafioluiz.model.Phone;
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
	    public ResponseEntity<Void> createUser(@RequestBody User user,  UriComponentsBuilder ucBuilder) {
	        System.out.println("Criando Usuario >>>>> " + user.getName());
	        
	        this.setPhone(user);
	        userService.save(user);
	        
	        if(userService.getAllUsers() != null && !userService.getAllUsers().isEmpty()) {
	        	System.out.println("User Created >>>> " + userService.getAllUsers().get(0));
	        }
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	  	
	  	 protected void setPhone(User user) {
	         if(user.getPhones() != null) {
	             for (Phone phone : user.getPhones()) {
	                 phone.setUser(user);
	             }
	         }
	     }
	
	  	/*
	  	 * JSON exemplo
	  	 * {
	  	 * 	"name" : "João da Silva",
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
