package com.latam.unamano.service.occupationService;

import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;

import java.util.List;
import java.util.Optional;

public interface OccupationServiceInterface {

    Occupation save(Occupation occupation);
<<<<<<< HEAD
    List<Occupation> getAll();
=======
    List<?> getAll();
>>>>>>> dev-backend
    Optional<Occupation> getById(Long id);
    Optional<Occupation> update(Occupation occupation);
    void delete(Long id);

}
