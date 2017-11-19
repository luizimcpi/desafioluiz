package com.company.desafioluiz.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.desafioluiz.exception.EmailAlreadyExistsException;
import com.company.desafioluiz.exception.InvalidSessionException;
import com.company.desafioluiz.exception.TokenInvalidException;
import com.company.desafioluiz.model.User;
import com.company.desafioluiz.repository.UserRepository;
import com.company.desafioluiz.util.LoginUtil;
import com.company.desafioluiz.util.impl.EncryptImpl;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EncryptImpl encrypt;

	public void save(User user)
			throws EmailAlreadyExistsException, UnsupportedEncodingException, NoSuchAlgorithmException {
		if (userRepository.findByEmail(user.getEmail()) == null) {
			LocalDateTime dataAtual = LocalDateTime.now();
			user.setPassword(encrypt.encryptPassword(user.getPassword()));
			user.setToken(this.generateUserToken());
			user.setCreated(dataAtual);
			user.setLastLogin(dataAtual);
			userRepository.save(user);
		} else {
			throw new EmailAlreadyExistsException();
		}

	}

	public User findById(long id, String token) throws TokenInvalidException {
		if (token == null || token.isEmpty()) {
			throw new TokenInvalidException();
		}

		User userBD = userRepository.findById(id);

		if (userBD != null) {
			if (!userBD.getToken().equals(token)) {
				throw new TokenInvalidException();
			}

			if (LoginUtil.lastLoginMoreThanThirtyMinutes(userBD)) {
				throw new InvalidSessionException();
			}

		}
		return userBD;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	private String generateUserToken() {
		Calendar cal = Calendar.getInstance();
		return UUID.randomUUID().toString().toUpperCase() + "|" + "userid" + "|" + cal.getTimeInMillis();
	}
}
