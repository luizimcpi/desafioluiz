package com.concrete.desafioluiz.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(PowerMockRunner.class)
@SpringBootTest
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest(UserController.class)
public class UserControllerTests {
	
	@Autowired
	private WebApplicationContext context;
    private MockMvc mvc;
	
    @Before
	public void
	setup()
			throws Exception {
		MockitoAnnotations.initMocks(this);

		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.build();
	}
    
	@Test
	public void 
		should_return_status_created_when_user_is_created() throws Exception 
	{
		  mvc.perform(post("/api/users"))
		 .andExpect(status().isCreated());
	}
}
