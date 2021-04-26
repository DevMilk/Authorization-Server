package com.devmilk.mlform.auth.controllers;

import javax.validation.Valid;

import com.devmilk.mlform.auth.exceptions.emailAlreadyExistsException;
import com.devmilk.mlform.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmilk.mlform.auth.payload.request.LoginRequest;
import com.devmilk.mlform.auth.payload.request.SignupRequest;
import com.devmilk.mlform.auth.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		return ResponseEntity.ok(userService.authenticate(
				loginRequest.getEmail(),loginRequest.getPassword()));
	}


	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		try {
			userService.createNewUser(signUpRequest.getEmail(),signUpRequest.getPassword());
			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
		catch(emailAlreadyExistsException e){
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

	}
}
