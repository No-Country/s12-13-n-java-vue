package com.latam.unamano.persistence.repositories.clientRepository;

import com.latam.unamano.persistence.entities.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c.id FROM Client c INNER JOIN c.user u WHERE u.username=:username")
    Long findByUsernameClientId(@Param("username")String username);
}
