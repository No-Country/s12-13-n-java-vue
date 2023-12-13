package com.latam.unamano.persistence.repositories.postulationRepository;

import com.latam.unamano.persistence.entities.postulationEntity.Postulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulationRepository extends JpaRepository<Postulation, Long> {

    //TODO: create a new query to get a list of postulations by worker id
    //TODO: ** JUAN **

    //List<Postulation>....
}
