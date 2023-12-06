package com.latam.unamano.persistence.repositories.occupationRepository;

import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Long> {
    //@Query("SELECT o FROM Occupation o WHERE o.occupationName= :occupationName")
    Occupation findByOccupationName(@Param("occupationName") String occupationName);

}
