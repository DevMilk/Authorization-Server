package com.devmilk.authserver.auth.repository;

import java.util.Optional;

import com.devmilk.authserver.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);
}
