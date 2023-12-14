package com.latam.unamano.persistence.repositories.workerRepository;


import com.latam.unamano.persistence.entities.client.Client;
import com.latam.unamano.persistence.entities.workerEntity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    Optional<Worker> findByUserId(Long userId);
}