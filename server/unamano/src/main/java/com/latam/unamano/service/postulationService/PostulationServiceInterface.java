package com.latam.unamano.service.postulationService;

import com.latam.unamano.dto.postulationDto.CreatePostulation;
import com.latam.unamano.dto.postulationDto.UpdatePostulation;
import com.latam.unamano.persistence.entities.postulationEntity.Postulation;

import java.util.List;
import java.util.Optional;

public interface PostulationServiceInterface {

    Postulation save(CreatePostulation createPostulation);
    List<Postulation> getAll(Long id);
    Optional<Postulation> getById(Long id);
    Optional<Postulation> update(UpdatePostulation updatePostulation);
    void delete(Long id);

}
