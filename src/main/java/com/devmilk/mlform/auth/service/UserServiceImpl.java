package com.devmilk.mlform.auth.service;

import com.devmilk.mlform.auth.exceptions.emailAlreadyExistsException;
import com.devmilk.mlform.auth.exceptions.userNotFoundException;
import com.devmilk.mlform.auth.models.Role;
import com.devmilk.mlform.auth.models.User;
import com.devmilk.mlform.auth.payload.response.JwtResponse;
import com.devmilk.mlform.auth.security.jwt.JwtUtils;
import com.devmilk.mlform.auth.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmilk.mlform.auth.repository.UserRepository;

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

	@Override
	public User getUserByEmail(String email) throws userNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if(!user.isPresent()) throw new userNotFoundException("User Not Found");
		return user.get();
	}

	public void createNewUser(String email, String password) throws emailAlreadyExistsException {
		if(userRepository.existsByEmail(email))
			throw new emailAlreadyExistsException("Email already exists");

		userRepository.save(new User(email,password));
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
