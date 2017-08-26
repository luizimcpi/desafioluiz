package com.concrete.desafioluiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioluizApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioluizApplication.class, args);
	}
}
//Acessar o h2 console http://localhost:8080/h2-console/ colocar no campo  JDBC:URL ->  jdbc:h2:mem:testdb