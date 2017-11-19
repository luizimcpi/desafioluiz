package com.company.desafioluiz.web;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.company.desafioluiz.exception.InvalidCredentialsException;
import com.company.desafioluiz.model.User;
import com.company.desafioluiz.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTests {
	
	@Mock
	private LoginService loginService;
	
	@InjectMocks
	private LoginController loginController;
	
    @Test
    public void deveEfetuarLoginComSucesso() throws InvalidCredentialsException, UnsupportedEncodingException, NoSuchAlgorithmException {
    	User user = new User();
    	when(loginService.doLogin(Mockito.any(User.class))).thenReturn(Mockito.any(User.class));
    	assertNotNull( loginController.doLogin(user) );
    }
    
    @Test
    public void deveVerificarCredenciaisInvalidas() {
    	assertNotNull(loginController.handleCredentialsException());
    }

    @Test
    public void deveVerificarErroDeCriptografiaDeSenha() {
    	assertNotNull(loginController.handlePasswordEncryptionException());
    }
    
}