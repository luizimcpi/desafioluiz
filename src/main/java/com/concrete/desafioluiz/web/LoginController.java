package com.concrete.desafioluiz.web;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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
	    public ResponseEntity<User> doLogin(@RequestBody User user) throws InvalidCredentialsException, UnsupportedEncodingException, NoSuchAlgorithmException {
	  		User userBD = loginService.doLogin(user);
	  		return new ResponseEntity<User>(userBD, HttpStatus.OK);
	    }
	  	
	  	@ExceptionHandler(InvalidCredentialsException.class)
	    public ResponseEntity<ErrorMessage> handleCredentialsException() {
	  		ErrorMessage errorMessage = new ErrorMessage();
	  		errorMessage.setMensagem("Usuario e/ou senha invalidos");
	  		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
	    }
	  	
		@ExceptionHandler({UnsupportedEncodingException.class, NoSuchAlgorithmException.class} )
		public ResponseEntity<ErrorMessage> handlePasswordEncryptionException() {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setMensagem("Ocorreu um erro ao criptografar a senha do usuario, tente cadastrar novamente");
			return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
