package com.company.desafioluiz.util;

import com.company.desafioluiz.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RunWith(MockitoJUnitRunner.class)
public class LoginUtilTests {

	public static final String PASSWORD = "";
	public static final String EMAIL = "";
	public static final String TOKEN = "";
	public static final String NAME = "";


	@Test
	    public void should_return_true_when_pass_the_limit_of_thirty_minutes_and_hour_is_under_ten() throws Exception {

			LocalDateTime yesterday0230 = LocalDateTime.of(
		            LocalDate.now().minusDays(1),
		            LocalTime.parse("02:30"));
			User user = new User(NAME, EMAIL, PASSWORD, LocalDateTime.now(), yesterday0230, TOKEN);

			Assert.assertTrue(LoginUtil.lastLoginMoreThanThirtyMinutes(user));
	    }

		@Test
		public void should_return_true_when_pass_the_limit_of_thirty_minutes_and_hour_is_ten() throws Exception {

			LocalDateTime yesterday1030 = LocalDateTime.of(
					LocalDate.now().minusDays(1),
					LocalTime.parse("10:30"));
			User user = new User(NAME, EMAIL, PASSWORD, LocalDateTime.now(), yesterday1030, TOKEN);
			
			Assert.assertTrue(LoginUtil.lastLoginMoreThanThirtyMinutes(user));
		}
		
		@Test
		public void should_return_false_when_dont_pass_the_limit_of_thirty_minutes_and_hour_is_under_ten () throws Exception {

			LocalDateTime yesterday0230 = LocalDateTime.of(
					LocalDate.now().plusDays(1),
					LocalTime.parse("02:30"));
			User user = new User(NAME, EMAIL, PASSWORD, LocalDateTime.now(), yesterday0230, TOKEN);
			
			Assert.assertFalse(LoginUtil.lastLoginMoreThanThirtyMinutes(user));
		}

		@Test
		public void should_return_false_when_dont_pass_the_limit_of_thirty_minutes_and_hour_is_ten () throws Exception {

			LocalDateTime yesterday1030 = LocalDateTime.of(
					LocalDate.now().plusDays(1),
					LocalTime.parse("10:30"));
			User user = new User(NAME, EMAIL, PASSWORD, LocalDateTime.now(), yesterday1030, TOKEN);
			
			Assert.assertFalse(LoginUtil.lastLoginMoreThanThirtyMinutes(user));
		}

		@Test
		public void should_return_false_when_dont_pass_the_limit_of_thirty_minutes_and_minutes_is_ten () throws Exception {

			LocalDateTime yesterday1010 = LocalDateTime.of(
					LocalDate.now().plusDays(1),
					LocalTime.parse("10:10"));
			User user = new User(NAME, EMAIL, PASSWORD, LocalDateTime.now(), yesterday1010, TOKEN);
			
			Assert.assertFalse(LoginUtil.lastLoginMoreThanThirtyMinutes(user));
		}

		@Test
		public void should_return_false_when_dont_pass_the_limit_of_thirty_minutes_and_minutes_is_under_ten () throws Exception {

			LocalDateTime yesterday1005 = LocalDateTime.of(
					LocalDate.now().plusDays(1),
					LocalTime.parse("10:05"));
			User user = new User(NAME, EMAIL, PASSWORD, LocalDateTime.now(), yesterday1005, TOKEN);
			
			Assert.assertFalse(LoginUtil.lastLoginMoreThanThirtyMinutes(user));
		}
}
