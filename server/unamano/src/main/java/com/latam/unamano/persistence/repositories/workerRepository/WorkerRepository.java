package com.latam.unamano.persistence.repositories.workerRepository;


import com.latam.unamano.persistence.entities.workerEntity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    /**
     * Get worker by User ID
     */
    Optional<Worker> findByUserId(Long userId);
}