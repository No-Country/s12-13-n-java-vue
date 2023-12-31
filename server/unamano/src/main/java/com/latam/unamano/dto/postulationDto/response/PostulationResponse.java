package com.latam.unamano.dto.postulationDto.response;

import com.latam.unamano.persistence.entities.postulationEntity.Postulation;
import com.latam.unamano.utils.PostulationStatus;

public record PostulationResponse(Long id, PostulationStatus status, Long idWorker, String firstname, String lastname,
                                  String city, String country, Long idTask, String taskTitle, String description) {

    public PostulationResponse(Postulation postulation){
        this(postulation.getId(), postulation.getStatus(), postulation.getWorker().getId(),
                postulation.getWorker().getUser().getFirstName(),
                postulation.getWorker().getUser().getLastName(),
                postulation.getWorker().getUser().getAddresses().getCity(),
                postulation.getWorker().getUser().getAddresses().getCountry(),
                postulation.getTask().getId(),
                postulation.getTask().getTaskTitle(),
                postulation.getTask().getDescription());
    }
}
