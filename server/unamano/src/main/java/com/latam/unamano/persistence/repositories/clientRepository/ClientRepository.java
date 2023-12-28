package com.latam.unamano.persistence.repositories.clientRepository;

import com.latam.unamano.persistence.entities.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUserId(Long userId);
    Client findByUserUsername(@Param("username")String username);
}
