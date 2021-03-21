package com.devmilk.mlform.User;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    void saveConfirmationToken(ConfirmationToken confirmationToken) {

        confirmationTokenRepository.save(confirmationToken);
    }
    void deleteConfirmationToken(Long id){

        confirmationTokenRepository.deleteById(id);
    }

    Optional<ConfirmationToken> findConfirmationTokenByToken(String token) {

        return confirmationTokenRepository.findConfirmationTokenByConfirmationToken(token);
    }
}