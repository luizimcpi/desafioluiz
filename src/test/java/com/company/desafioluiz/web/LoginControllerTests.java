package com.company.desafioluiz.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.company.desafioluiz.DesafioluizApplication;
import com.company.desafioluiz.model.User;
import com.google.gson.Gson;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTests {
	
	private static final String VALID_API_LOGIN_ROUTE = "/api/login";
	private static final String INVALID_ROUTE = "/doLogin";
	private static final String INVALID_USER_PASSWORD = "Hunter27";
	private static final String INVALID_USER_EMAIL = "newuser@email.com";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

	private User INVALID_USER;
	
	private MockMvc mockMvc;

	@InjectMocks
	private LoginController loginController;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	private Gson userJson;
	
	
    @Before
    public void setup() throws Exception {
    	MockitoAnnotations.initMocks(this);
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        userJson = new Gson();
    }
    
    @Test
    public void should_return_status_unauthorized_when_user_with_bad_credentials() throws Exception {
    	INVALID_USER = new User();
		INVALID_USER.setEmail(INVALID_USER_EMAIL);
		INVALID_USER.setPassword(INVALID_USER_PASSWORD);
    	String invalidUserjsonString = userJson.toJson(INVALID_USER);
    	
    	this.mockMvc.perform(post(VALID_API_LOGIN_ROUTE)
    			.contentType(contentType)
    			.content(invalidUserjsonString))
    	.andExpect(status().isUnauthorized());
    }

    /*
    @Test
    public void should_return_status_ok_when_user_logged() throws Exception {
    	User VALID_USER;
    	VALID_USER = new User();
    	VALID_USER.setEmail("luizhenrique.se@gmail.com");
    	VALID_USER.setPassword("hunter2017");
    	String validUserjsonString = userJson.toJson(VALID_USER);
    	
    	Mockito.when(loginService.doLogin(VALID_USER)).thenReturn(VALID_USER);
    	
    	this.mockMvc.perform(post(VALID_API_LOGIN_ROUTE)
    			.contentType(contentType)
    			.content(validUserjsonString))
    	.andExpect(status().isOk());
    	
    }*/
    
    @Test
    public void should_return_not_found_when_a_request_route_doesnt_exist() throws Exception {
    	this.mockMvc.perform(post(INVALID_ROUTE)
    			.contentType(contentType))
                .andExpect(status().isNotFound());
    }

}