package com.latam.unamano.dto.occupationDto;

import com.latam.unamano.dto.task.TaskDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OccupationDTO {
    private Long id;
    private String occupationName;
    private String description;
    private List<TaskDTO> task;
}
