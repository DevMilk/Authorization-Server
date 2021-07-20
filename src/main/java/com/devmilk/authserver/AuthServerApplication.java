package com.devmilk.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO: Arayüz
@SpringBootApplication
@EnableAutoConfiguration
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

}
