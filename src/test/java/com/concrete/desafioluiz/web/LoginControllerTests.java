package com.concrete.desafioluiz.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.concrete.desafioluiz.DesafioluizApplication;
import com.concrete.desafioluiz.model.User;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DesafioluizApplication.class)
@WebAppConfiguration
public class LoginControllerTests {
	
	private static final String VALID_API_LOGIN_ROUTE = "/api/login";
	private static final String INVALID_ROUTE = "/doLogin";
	private static final String VALID_USER_PASSWORD = "Hunter27";
	private static final String VALID_USER_EMAIL = "newuser@email.com";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

	private User VALID_USER;
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private String userjsonString = "";
	
	
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        
    	Gson userJson = new Gson();
    	VALID_USER = new User();
    	VALID_USER.setEmail(VALID_USER_EMAIL);
    	VALID_USER.setPassword(VALID_USER_PASSWORD);
    	
    	userjsonString = userJson.toJson(VALID_USER);
    }
    
    @Test
    public void should_return_status_unauthorized_when_user_with_bad_credentials() throws Exception {
    	this.mockMvc.perform(post(VALID_API_LOGIN_ROUTE)
    			.contentType(contentType)
    			.content(userjsonString))
    	.andExpect(status().isUnauthorized());
    }
    
    @Test
    public void should_return_not_found_when_a_request_route_doesnt_exist() throws Exception {
    	this.mockMvc.perform(post(INVALID_ROUTE)
    			.contentType(contentType))
                .andExpect(status().isNotFound());
    }

}