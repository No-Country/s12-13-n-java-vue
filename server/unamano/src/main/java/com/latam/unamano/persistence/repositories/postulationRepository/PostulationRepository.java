package com.latam.unamano.persistence.repositories.postulationRepository;

import com.latam.unamano.persistence.entities.postulationEntity.Postulation;
import com.latam.unamano.persistence.entities.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulationRepository extends JpaRepository<Postulation, Long> {


    Page<Postulation> getAllByWorkerId(Pageable pageable, @Param("id") Long id);

}
