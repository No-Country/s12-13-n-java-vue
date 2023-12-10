package com.latam.unamano.dto.workerDto.reponse;

import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;
import com.latam.unamano.persistence.entities.workerEntity.Worker;
import com.latam.unamano.utils.Role;

import java.util.List;

public record GetWorker(Long id_worker,Long id_user, String firstName, String lastName, String email, Role rol,
                        Long id_address, String city, String country, List<Occupation> occupationList) {

    public GetWorker(Worker worker){
        this(worker.getId(),worker.getUser().getId(),worker.getUser().getFirstName(),
                worker.getUser().getLastName(),
                worker.getUser().getEmail(),
                worker.getUser().getRole(),
                worker.getUser().getAddresses().getId(),
                worker.getUser().getAddresses().getCity(),
                worker.getUser().getAddresses().getCountry(),
                worker.getOccupations());
    }
}
