package com.latam.unamano.dto.task;


import com.latam.unamano.dto.occupationDto.OccupationDTO;
import com.latam.unamano.persistence.entities.addressEntity.Address;
import com.latam.unamano.persistence.entities.client.Client;
import com.latam.unamano.persistence.entities.user.User;
import com.latam.unamano.utils.TaskStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private Long id;
    private String taskTitle;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private BigDecimal price;
<<<<<<< HEAD
=======
    private String currencyType;
>>>>>>> dev-backend
    private TaskStatus status;
    private List<OccupationDTO> occupations;
    private LocalDateTime taskDate;
    private User client;
    private Address address;

    //TODO Relaciones entre clases
    /*
    private Client client;
    private Worker worker;
    private List<Occupation> occupations;
    private Address address;
    private Rating workerRating;
    private Rating clientRating;
    private List<Postulation> postulations;
    private List<WorkingDay> workingDay;
    */
}
