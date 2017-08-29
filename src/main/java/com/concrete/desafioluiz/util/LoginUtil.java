package com.concrete.desafioluiz.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.concrete.desafioluiz.model.User;

public class LoginUtil {
	
	public static boolean lastLoginMoreThanThirtyMinutes(User user){
		
		long limitMinutes = 30L;
		String actualHour = "" ;
		if(LocalDateTime.now().getHour() < 10) {
			actualHour = "0" + String.valueOf(LocalDateTime.now().getHour());
		}else {
			actualHour = String.valueOf(LocalDateTime.now().getHour());
		}
		
		String actualMinutes = "";
		if(LocalDateTime.now().getMinute() < 10) {
			actualMinutes = "0" + String.valueOf(LocalDateTime.now().getMinute());
		}else {
			actualMinutes = String.valueOf(LocalDateTime.now().getMinute());
		}
		String actualHourFormated = actualHour +":"+actualMinutes; 
		
		LocalDateTime actual    = LocalDateTime.of(
	            LocalDate.now(),
	            LocalTime.parse(actualHourFormated));
		
		String lastLoginHour = "";
		if(user.getLast_login().getHour() < 10) {
			lastLoginHour = "0" + String.valueOf(user.getLast_login().getHour());
		}else {
			lastLoginHour = String.valueOf(user.getLast_login().getHour());
		}
		String lastLoginMinutes="";
		if(user.getLast_login().getMinute() < 10) {
			lastLoginMinutes = "0" + String.valueOf(user.getLast_login().getMinute());
		}else {
			lastLoginMinutes = String.valueOf(user.getLast_login().getMinute());
		}
		String lastLoginFormatedHour = lastLoginHour + ":" + lastLoginMinutes;
		
	    LocalDateTime userLastLogin = LocalDateTime.of(
	    		user.getLast_login().toLocalDate(),
	            LocalTime.parse(lastLoginFormatedHour));

	    
		return  Duration.between(userLastLogin, actual).toMinutes() > limitMinutes;
	}
}
