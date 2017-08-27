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
public class UserControllerTests {
	
	private static final String VALID_USER_PASSWORD = "Hunter27";
	private static final String VALID_USER_EMAIL = "newuser@email.com";
	private static final String VALID_USER_NAME = "Valid User";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void should_return_status_created_when_user_is_created() throws Exception {
    	Gson userJson = new Gson();
    	User VALID_USER = new User();
    	VALID_USER.setName(VALID_USER_NAME);
    	VALID_USER.setEmail(VALID_USER_EMAIL);
    	VALID_USER.setPassword(VALID_USER_PASSWORD);
    	
    	String userjsonString = userJson.toJson(VALID_USER);
    	
    	System.out.println(userjsonString); 
    	
        this.mockMvc.perform(post("/api/users")
                .contentType(contentType)
                .content(userjsonString))
                .andExpect(status().isCreated());
    }

}