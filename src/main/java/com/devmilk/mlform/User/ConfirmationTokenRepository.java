package com.devmilk.mlform.User;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String token);
}