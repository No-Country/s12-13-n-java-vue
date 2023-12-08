package com.latam.unamano.service.postulationService;

import com.latam.unamano.dto.postulationDto.CreatePostulation;
import com.latam.unamano.dto.postulationDto.UpdatePostulation;
import com.latam.unamano.persistence.entities.postulationEntity.Postulation;
import com.latam.unamano.persistence.repositories.postulationRepository.PostulationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostulationService implements PostulationServiceInterface{

    private PostulationRepository postulationRepository;

    public PostulationService(PostulationRepository postulationRepository){
        this.postulationRepository = postulationRepository;
    }

    @Override
    public Postulation save(CreatePostulation createPostulation) {
        return null;
    }

    @Override
    public List<Postulation> getAll(Long id) {
        return null;
    }

    @Override
    public Optional<Postulation> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Postulation> update(UpdatePostulation updatePostulation) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
