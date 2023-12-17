package com.latam.unamano.dto.task;

import com.latam.unamano.dto.clientDto.response.ClientData;
import com.latam.unamano.dto.occupationDto.OccupationDTO;
import com.latam.unamano.persistence.entities.addressEntity.Address;
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
public class CreateTaskDTO {
    private String taskTitle;
    private String description;
    private BigDecimal price;
    private String currencyType;
    private List<OccupationDTO> occupations;
    private LocalDateTime taskDate;
    private ClientData client;
    private Address address;
}
