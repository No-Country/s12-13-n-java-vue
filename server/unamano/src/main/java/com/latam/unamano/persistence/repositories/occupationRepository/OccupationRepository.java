package com.latam.unamano.persistence.repositories.occupationRepository;

import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Long> {
}
