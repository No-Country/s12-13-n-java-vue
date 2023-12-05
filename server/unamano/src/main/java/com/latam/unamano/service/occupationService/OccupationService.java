package com.latam.unamano.service.occupationService;


import com.latam.unamano.dto.occupationDto.OccupationDTO;
import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;
import com.latam.unamano.persistence.repositories.occupationRepository.OccupationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OccupationService implements OccupationServiceInterface{

    private OccupationRepository occupationRepository;

    public OccupationService(OccupationRepository occupationRepository){
        this.occupationRepository = occupationRepository;
    }

    @Override
    public Occupation save(Occupation occupation) {
        return occupationRepository.save(occupation);
    }

    @Override
    public List<Occupation> getAll() {
        return occupationRepository.findAll();
    }

    @Override
    public Optional<Occupation> getById(Long id) {
        Optional<Occupation> occupationOptional = occupationRepository.findById(id);
        if (occupationOptional.isPresent()){
            return Optional.of(occupationOptional.get());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Occupation> update(Occupation occupation) {
        Optional<Occupation> occupationOptional = occupationRepository.findById(occupation.getId());
        if (occupationOptional.isPresent()){
            return Optional.of(occupationRepository.save(occupation));
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        occupationRepository.deleteById(id);
    }

    public Occupation findByOccupationName(Occupation occupation){
        return occupationRepository.findByOccupationName(occupation.getOccupationName());
    }

}
