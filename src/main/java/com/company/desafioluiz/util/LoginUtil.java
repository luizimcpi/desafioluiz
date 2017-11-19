package com.company.desafioluiz.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.company.desafioluiz.model.User;

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
		if(user.getLastLogin().getHour() < 10) {
			lastLoginHour = "0" + String.valueOf(user.getLastLogin().getHour());
		}else {
			lastLoginHour = String.valueOf(user.getLastLogin().getHour());
		}
		String lastLoginMinutes="";
		if(user.getLastLogin().getMinute() < 10) {
			lastLoginMinutes = "0" + String.valueOf(user.getLastLogin().getMinute());
		}else {
			lastLoginMinutes = String.valueOf(user.getLastLogin().getMinute());
		}
		String lastLoginFormatedHour = lastLoginHour + ":" + lastLoginMinutes;
		
	    LocalDateTime userLastLogin = LocalDateTime.of(
	    		user.getLastLogin().toLocalDate(),
	            LocalTime.parse(lastLoginFormatedHour));

	    
		return  Duration.between(userLastLogin, actual).toMinutes() > limitMinutes;
	}
}
