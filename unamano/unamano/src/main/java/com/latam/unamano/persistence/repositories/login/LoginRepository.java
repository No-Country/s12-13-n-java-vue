package com.latam.unamano.persistence.repositories.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.latam.unamano.persistence.entities.Login;


public interface LoginRepository extends JpaRepository<Login, Long> {
	Optional<UserDetails> findByUsername(String username);
}
