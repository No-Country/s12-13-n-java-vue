package com.unamano.unamano.client.infra.jpaRepository;

import com.unamano.unamano.client.infra.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
