package com.concrete.desafioluiz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.concrete.desafioluiz.model.User;
import com.concrete.desafioluiz.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

		@Autowired 
		UserService userService;
	
	  	@RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<Void> createUsuario(@RequestBody User usuario,  UriComponentsBuilder ucBuilder) {
	        System.out.println("Criando Usuario " + usuario.getName());
	        
	        userService.save(usuario);
	        
	        if(userService.getAllUsers() != null && !userService.getAllUsers().isEmpty()) {
	        	System.out.println("USUARIO CRIADO >>>> " + userService.getAllUsers().get(0));
	        }
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(usuario.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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
