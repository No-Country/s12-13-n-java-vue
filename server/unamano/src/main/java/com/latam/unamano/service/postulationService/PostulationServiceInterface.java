package com.latam.unamano.service.postulationService;

import com.latam.unamano.dto.postulationDto.request.CreatePostulation;
import com.latam.unamano.dto.postulationDto.request.CreatePostulationByUser;
import com.latam.unamano.dto.postulationDto.request.UpdatePostulation;
import com.latam.unamano.dto.postulationDto.response.PostulationResponse;
import com.latam.unamano.persistence.entities.postulationEntity.Postulation;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PostulationServiceInterface {

    Optional<Postulation> save(CreatePostulation createPostulation);
    Optional<Postulation> saveByUserId(CreatePostulationByUser createPostulationByUser);
    Page<PostulationResponse> getAll(int numberPage);
    List<Postulation> getAllByWorkerId(Long idWorker);
    Optional<Postulation> getById(Long id);
    Optional<Postulation> update(UpdatePostulation updatePostulation);
    void delete(Long id);

}
