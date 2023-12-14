package com.latam.unamano.persistence.repositories.clientRepository;

import com.latam.unamano.persistence.entities.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUserId(Long userId);
}
