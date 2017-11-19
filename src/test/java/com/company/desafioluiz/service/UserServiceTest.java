package com.company.desafioluiz.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.company.desafioluiz.model.User;
import com.company.desafioluiz.repository.UserRepository;
import com.company.desafioluiz.util.impl.EncryptImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	public static final String EMAIL_VALIDO = "teste@desafio.com.br";
	public static final String PASSWORD_CRIPTOGRAFADO = "XXXXXXX";

	@Mock
	UserRepository userRepository;

	@Mock
	EncryptImpl encrypt;

	@InjectMocks
	UserService userService;

	@Test
	public void deveAdicionarUsuarioComSucesso() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		final User user = new User();
		user.setEmail(EMAIL_VALIDO);
		user.setPassword(PASSWORD_CRIPTOGRAFADO);
		when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
		when(encrypt.encryptPassword(user.getPassword())).thenReturn(PASSWORD_CRIPTOGRAFADO);
	}

	@Test
	public void deveBuscarTodosOsUsuarios() {
		final List<User> users = new ArrayList<>();
		final User user = new User();
		user.setEmail(EMAIL_VALIDO);
		user.setPassword(PASSWORD_CRIPTOGRAFADO);
		users.add(user);
		
		when(userRepository.findAll()).thenReturn(users);
		assertNotNull(userService.getAllUsers());
	}

}