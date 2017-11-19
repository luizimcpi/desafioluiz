package com.company.desafioluiz.web;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.desafioluiz.dto.ErrorMessage;
import com.company.desafioluiz.exception.EmailAlreadyExistsException;
import com.company.desafioluiz.exception.InvalidSessionException;
import com.company.desafioluiz.exception.TokenInvalidException;
import com.company.desafioluiz.keys.ErrorKeys;
import com.company.desafioluiz.model.User;
import com.company.desafioluiz.service.UserService;

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
		
		@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  	public ResponseEntity<User> getUserById(@PathVariable("id") long id, @RequestHeader("token") String token) throws TokenInvalidException, InvalidSessionException {
	    	User userBD = userService.findById(id, token);
	  		return new ResponseEntity<User>(userBD, HttpStatus.OK);
	  	}
		
	  	@RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<User> createUser(@RequestBody User user) throws EmailAlreadyExistsException, UnsupportedEncodingException, NoSuchAlgorithmException {
	        userService.save(user);
	        return new ResponseEntity<User>(user, HttpStatus.CREATED);
	    }
	  	
		@ExceptionHandler(EmailAlreadyExistsException.class)
	    public ResponseEntity<ErrorMessage> handleEmailException() {
	  		ErrorMessage errorMessage = new ErrorMessage();
	  		errorMessage.setMensagem(ErrorKeys.EMAIL_EXISTENTE.toString());
	  		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.OK);
	    }

		@ExceptionHandler(TokenInvalidException.class)
		public ResponseEntity<ErrorMessage> handleTokenException() {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setMensagem(ErrorKeys.UNAUTHORIZED.toString());
			return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
		}
		
		@ExceptionHandler(InvalidSessionException.class)
		public ResponseEntity<ErrorMessage> handleSessionException() {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setMensagem(ErrorKeys.INVALID_SESSION.toString());
			return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
		}

		@ExceptionHandler({UnsupportedEncodingException.class, NoSuchAlgorithmException.class} )
		public ResponseEntity<ErrorMessage> handlePasswordEncryptionException() {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setMensagem(ErrorKeys.PASSWORD_ENCRYPT_ERROR.toString());
			return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
}
