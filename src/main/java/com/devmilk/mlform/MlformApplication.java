package com.devmilk.mlform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//TODO: Kullanıcı token id'lerini değiştir
//TODO: Vertification adresi http://127.0.0.1/api/auth/vertification yerine /api/auth/signup/vertification olarak döndürülüyor düzelt
//TODO: Arayüz
@SpringBootApplication
@EnableAutoConfiguration
public class MlformApplication {

	public static void main(String[] args) {
		SpringApplication.run(MlformApplication.class, args);
	}

}
