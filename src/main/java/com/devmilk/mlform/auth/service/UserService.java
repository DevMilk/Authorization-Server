package com.devmilk.mlform.auth.service;

import com.devmilk.mlform.auth.exceptions.InvalidTokenException;
import com.devmilk.mlform.auth.exceptions.emailAlreadyExistsException;
import com.devmilk.mlform.auth.exceptions.userNotFoundException;
import com.devmilk.mlform.auth.models.User;
import com.devmilk.mlform.auth.payload.response.JwtResponse;

public interface UserService {
    public JwtResponse authenticate(String email, String password);
    public User createNewUser(String email, String password) throws emailAlreadyExistsException;
    public void sendEmailVertification(String auth_path,User user);
    public User getUserByEmail(String email) throws userNotFoundException;
    public void vertificateEmail(Long token) throws InvalidTokenException;
}
