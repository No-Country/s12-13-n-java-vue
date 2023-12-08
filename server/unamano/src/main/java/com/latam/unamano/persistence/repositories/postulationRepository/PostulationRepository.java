package com.latam.unamano.persistence.repositories.postulationRepository;

import com.latam.unamano.persistence.entities.postulationEntity.Postulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostulationRepository extends JpaRepository<Postulation, Long> {
}
