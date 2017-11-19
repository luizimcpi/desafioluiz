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

import com.company.desafioluiz.exception.InvalidCredentialsException;
import com.company.desafioluiz.model.User;
import com.company.desafioluiz.repository.UserRepository;
import com.company.desafioluiz.util.impl.EncryptImpl;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {


    public static final String EMAIL_VALIDO = "teste@desafio.com.br";
    public static final String PASSWORD_CRIPTOGRAFADO = "XXXXXXX";
    public static final String PASSWORD_INVALIDO = "";

    @Mock
    EncryptImpl encrypt;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    LoginService loginService;

    @Test
    public void deveEfetuarLoginComSucesso() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        final List<User> users = new ArrayList<>();
        final User user = new User();

        user.setEmail(EMAIL_VALIDO);
        user.setPassword(PASSWORD_CRIPTOGRAFADO);
        users.add(user);

        when(encrypt.encryptPassword(user.getPassword())).thenReturn(PASSWORD_CRIPTOGRAFADO);
        when(userRepository.findByEmailAndPassword(EMAIL_VALIDO, PASSWORD_CRIPTOGRAFADO)).thenReturn(user);
        User userRetorno = loginService.doLogin(user);
        assertNotNull(userRetorno);
    }

    @Test
    public void deveRetornarErroQuandoUsuarioErrarCredenciais() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        final List<User> users = new ArrayList<>();
        when(encrypt.encryptPassword(PASSWORD_INVALIDO)).thenReturn(PASSWORD_CRIPTOGRAFADO);
        when(userRepository.findByEmailAndPassword(EMAIL_VALIDO, PASSWORD_INVALIDO)).thenThrow(InvalidCredentialsException.class);
    }
}
