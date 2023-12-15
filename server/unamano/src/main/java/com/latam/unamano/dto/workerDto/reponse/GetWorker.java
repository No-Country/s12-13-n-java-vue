package com.latam.unamano.dto.workerDto.reponse;

import com.latam.unamano.dto.occupationDto.OccupationDTO;
import com.latam.unamano.dto.occupationDto.OccupationMapper;
import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;
import com.latam.unamano.persistence.entities.workerEntity.Worker;
import com.latam.unamano.utils.Role;

import java.util.List;

public record GetWorker(Long id_worker, Long id_user, String username, String firstName, String lastName, String email, Role rol,
                        Long id_address, String city, String country, List<OccupationDTO> occupationList) {

    public GetWorker(Worker worker){
        this(worker.getId(),worker.getUser().getId()
                , worker.getUser().getUsername()
                ,worker.getUser().getFirstName(),
                worker.getUser().getLastName(),
                worker.getUser().getEmail(),
                worker.getUser().getRole(),
                worker.getUser().getAddresses().getId(),
                worker.getUser().getAddresses().getCity(),
                worker.getUser().getAddresses().getCountry(),
                worker.getOccupations().stream().map(OccupationMapper::occupationToDto).toList());
    }
}
