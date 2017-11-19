package com.company.desafioluiz.web;

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

import com.company.desafioluiz.exception.EmailAlreadyExistsException;
import com.company.desafioluiz.model.User;
import com.company.desafioluiz.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

	private static final long VALID_USER_ID = 1L;
	private static final String TOKEN = "token";
	private static final String EMAIL_VALIDO = "luizhenrique.se@gmail.com";

	@Mock 
	UserService userService;

	@InjectMocks
	private UserController userController;
	
    @Test
    public void deveRetornarNoContentQuandoNaoExistirUsuariosNaLista() {
    	List<User> users = new ArrayList<>();
    	when(userService.getAllUsers()).thenReturn(users);
    	assertNotNull(userController.listAll());
    }
	
    @Test
    public void deveListarTodosOsUsuarios() {
    	List<User> users = new ArrayList<>();
    	User user = new User();
    	user.setEmail(EMAIL_VALIDO);
		users.add(user);
    	when(userService.getAllUsers()).thenReturn(users);
    	assertNotNull(userController.listAll());
    }
    
    @Test
    public void deveConsultarUmUsuarioEspecificoComSucesso() {
    	User user = new User();
    	user.setEmail(EMAIL_VALIDO);
    	when(userService.findById(VALID_USER_ID, TOKEN)).thenReturn(user);
    	assertNotNull(userController.getUserById(VALID_USER_ID, TOKEN));
    }
    
    @Test
    public void devePersistirUmUsuarioComSucesso() throws EmailAlreadyExistsException, UnsupportedEncodingException, NoSuchAlgorithmException {
    	User user = new User();
    	user.setEmail(EMAIL_VALIDO);
    	assertNotNull(userController.createUser(user));
    }
    @Test
    public void deveVerificarSeEmailValido() {
    	assertNotNull(userController.handleEmailException());
    }
    
    @Test
    public void deveVerificarSeTokenValido() {
    	assertNotNull(userController.handleTokenException());
    }

    @Test
    public void deveVerificarSessaoValida() {
    	assertNotNull(userController.handleSessionException());
    }
    
    @Test
    public void deveVerificarSeOcorreuErroEncryptPassword() {
    	assertNotNull(userController.handlePasswordEncryptionException());
    }
}