package com.latam.unamano.service.postulationService;

import com.latam.unamano.dto.postulationDto.request.CreatePostulation;
import com.latam.unamano.dto.postulationDto.request.UpdatePostulation;
import com.latam.unamano.dto.postulationDto.response.PostulationResponse;
import com.latam.unamano.persistence.entities.postulationEntity.Postulation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostulationServiceInterface {

    Optional<Postulation> save(CreatePostulation createPostulation);
    Page<PostulationResponse> getAll(int numberPage);
    Page<Postulation> getAllByWorkerId(Long idWorker, Pageable pageable);
    Optional<Postulation> getById(Long id);
    Optional<Postulation> update(UpdatePostulation updatePostulation);
    void delete(Long id);

    Page<PostulationResponse> getPostulationsByTaskId(Pageable pageable, Long idTask);
}
