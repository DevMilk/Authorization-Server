package com.devmilk.mlform.auth.service;

import com.devmilk.mlform.auth.exceptions.emailAlreadyExistsException;
import com.devmilk.mlform.auth.exceptions.userNotFoundException;
import com.devmilk.mlform.auth.models.User;
import com.devmilk.mlform.auth.payload.response.JwtResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    public JwtResponse authenticate(String email, String password);
    public void createNewUser(String email, String password) throws emailAlreadyExistsException;
    public User getUserByEmail(String email) throws userNotFoundException;
}
