package com.latam.unamano.persistence.repositories.user;

import java.util.Optional;

import com.latam.unamano.utils.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.latam.unamano.persistence.entities.user.User;
import org.springframework.web.bind.annotation.PathVariable;


public interface UserRepository extends JpaRepository<User, Long> {
	Optional<UserDetails> findByUsername(String username);

	@Query("SELECT u.role FROM User u WHERE u.username=:username")
	Role findRoleByUsername(@Param("username") String username);
}
