package com.latam.unamano.persistence.repositories.clientRepository;

import com.latam.unamano.persistence.entities.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByUserUsername(@Param("username")String username);
}
