package com.devmilk.authserver.auth.service;

import com.devmilk.authserver.auth.exceptions.InvalidTokenException;
import com.devmilk.authserver.auth.exceptions.emailAlreadyExistsException;
import com.devmilk.authserver.auth.exceptions.userNotFoundException;
import com.devmilk.authserver.auth.models.User;
import com.devmilk.authserver.auth.payload.response.JwtResponse;
import com.devmilk.authserver.auth.security.jwt.JwtUtils;
import com.devmilk.authserver.auth.security.services.EmailSenderService;
import com.devmilk.authserver.auth.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devmilk.authserver.auth.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private EmailSenderService emailSenderService;

	@Override
	public User getUserByEmail(String email) throws userNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if(!user.isPresent()) throw new userNotFoundException("User Not Found");
		return user.get();
	}

	@Override
	public void vertificateEmail(Long id) throws InvalidTokenException {

		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new InvalidTokenException("Invalid Token");
		User user_to_enable = user.get();
		user_to_enable.setEnabled(true);
		userRepository.save(user_to_enable);
	}

	public User createNewUser(String email, String password) throws emailAlreadyExistsException {
		if(userRepository.existsByEmail(email))
			throw new emailAlreadyExistsException("Email already exists");

		User new_user = new User(email,new BCryptPasswordEncoder().encode(password));
		userRepository.save(new_user);
		return new_user;
	}

	@Override
	public void sendEmailVertification(String auth_path,User user) {

		System.out.println(user.getId());
		String vertification_url = auth_path + "/vertification?token=" + user.getId();

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmail());
		message.setSubject("Email Vertification");
		message.setText(vertification_url);
		emailSenderService.sendEmail(message);

	}


	@Override
	public JwtResponse authenticate(String email, String password) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, password));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getEmail(),
				roles);
	}

}
