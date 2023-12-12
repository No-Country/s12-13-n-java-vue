package com.latam.unamano.dto.task;

import com.latam.unamano.dto.occupationDto.OccupationDTO;
import com.latam.unamano.persistence.entities.addressEntity.Address;
import com.latam.unamano.persistence.entities.client.Client;
import com.latam.unamano.utils.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateTaskDTO {
    private String taskTitle;
    private String description;
    private BigDecimal price;
    private String currencyType;
    private LocalDateTime taskDate;

}
