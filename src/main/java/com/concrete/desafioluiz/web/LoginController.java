package com.concrete.desafioluiz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.concrete.desafioluiz.dto.ErrorMessage;
import com.concrete.desafioluiz.exception.InvalidCredentialsException;
import com.concrete.desafioluiz.model.User;
import com.concrete.desafioluiz.service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

		@Autowired 
		LoginService loginService;
	
	  	@RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<User> doLogin(@RequestBody User user) throws InvalidCredentialsException {
	  		User userBD = loginService.doLogin(user);
	  		return new ResponseEntity<User>(userBD, HttpStatus.OK);
	    }
	  	
	  	@ExceptionHandler(InvalidCredentialsException.class)
	    public ResponseEntity<ErrorMessage> handleCredentialsException() {
	  		ErrorMessage errorMessage = new ErrorMessage();
	  		errorMessage.setMensagem("Usuario e/ou senha invalidos");
	  		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
	    }
}
