package com.latam.unamano.persistence.repositories.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.latam.unamano.persistence.entities.user.User;


public interface UserRepository extends JpaRepository<User, Long> {
	Optional<UserDetails> findByUsername(String username);

}
