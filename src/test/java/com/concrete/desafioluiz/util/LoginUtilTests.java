package com.concrete.desafioluiz.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.concrete.desafioluiz.DesafioluizApplication;
import com.concrete.desafioluiz.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DesafioluizApplication.class)
@WebAppConfiguration
public class LoginUtilTests {
	 
		@Test
	    public void should_return_true_when_pass_the_limit_of_thirty_minutes_and_hour_is_under_ten() throws Exception {
			User user = new User();
			
			LocalDateTime yesterday0230 = LocalDateTime.of(
		            LocalDate.now().minusDays(1),
		            LocalTime.parse("02:30"));
			user.setLast_login(yesterday0230);
			
			Assert.assertTrue(LoginUtil.lastLoginMoreThanThirtyMinutes(user));
	    }

		@Test
		public void should_return_true_when_pass_the_limit_of_thirty_minutes_and_hour_is_ten() throws Exception {
			User user = new User();
			
			LocalDateTime yesterday1030 = LocalDateTime.of(
					LocalDate.now().minusDays(1),
					LocalTime.parse("10:30"));
			user.setLast_login(yesterday1030);
			
			Assert.assertTrue(LoginUtil.lastLoginMoreThanThirtyMinutes(user));
		}
		
		@Test
		public void should_return_false_when_dont_pass_the_limit_of_thirty_minutes_and_hour_is_under_ten () throws Exception {
			User user = new User();
			
			LocalDateTime yesterday0230 = LocalDateTime.of(
					LocalDate.now().plusDays(1),
					LocalTime.parse("02:30"));
			user.setLast_login(yesterday0230);
			
			Assert.assertFalse(LoginUtil.lastLoginMoreThanThirtyMinutes(user));
		}

		@Test
		public void should_return_false_when_dont_pass_the_limit_of_thirty_minutes_and_hour_is_ten () throws Exception {
			User user = new User();
			
			LocalDateTime yesterday1030 = LocalDateTime.of(
					LocalDate.now().plusDays(1),
					LocalTime.parse("10:30"));
			user.setLast_login(yesterday1030);
			
			Assert.assertFalse(LoginUtil.lastLoginMoreThanThirtyMinutes(user));
		}

		@Test
		public void should_return_false_when_dont_pass_the_limit_of_thirty_minutes_and_minutes_is_ten () throws Exception {
			User user = new User();
			
			LocalDateTime yesterday1010 = LocalDateTime.of(
					LocalDate.now().plusDays(1),
					LocalTime.parse("10:10"));
			user.setLast_login(yesterday1010);
			
			Assert.assertFalse(LoginUtil.lastLoginMoreThanThirtyMinutes(user));
		}

		@Test
		public void should_return_false_when_dont_pass_the_limit_of_thirty_minutes_and_minutes_is_under_ten () throws Exception {
			User user = new User();
			
			LocalDateTime yesterday1005 = LocalDateTime.of(
					LocalDate.now().plusDays(1),
					LocalTime.parse("10:05"));
			user.setLast_login(yesterday1005);
			
			Assert.assertFalse(LoginUtil.lastLoginMoreThanThirtyMinutes(user));
		}
}
