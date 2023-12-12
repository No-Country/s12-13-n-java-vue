package com.latam.unamano.dto.task;

import com.latam.unamano.dto.occupationDto.OccupationDTO;
import com.latam.unamano.persistence.entities.addressEntity.Address;
import com.latam.unamano.persistence.entities.client.Client;
import com.latam.unamano.utils.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
public class CreateTaskDTO {
    private String taskTitle;
    private String description;
    private BigDecimal price;
    private String currencyType;
    private List<String> occupations;
    private LocalDateTime taskDate;
    private Address address;
}
