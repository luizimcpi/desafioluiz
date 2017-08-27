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
/*
    test dependencies
 	testCompile group: 'org.hamcrest', name: 'hamcrest-all', version: '1.3'
	testCompile group: 'org.mockito', name: 'mockito-all', version: '1.10.19'
	testCompile group: 'pl.pragmatists', name: 'JUnitParams', version: '1.0.6'
	testCompile group: 'org.powermock', name: 'powermock-module-junit4', version: '1.6.6'
	testCompile group: 'org.powermock', name: 'powermock-api-mockito', version: '1.6.6'
 */
