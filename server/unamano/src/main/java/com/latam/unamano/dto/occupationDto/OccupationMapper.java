package com.latam.unamano.dto.occupationDto;

import com.latam.unamano.dto.task.TaskMapper;
import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OccupationMapper {
    public Occupation DtoToOccupation(OccupationDTO occupationDTO){
        return Occupation.builder()
                .occupationName(occupationDTO.getOccupationName())
                .description(occupationDTO.getDescription())
                .color((occupationDTO.getColor()))
                //.task(occupationDTO.getTask())
                .build();
    }
    public OccupationDTO occupationToDto(Occupation occupation){
        return OccupationDTO.builder()
                .id(occupation.getId())
                .occupationName(occupation.getOccupationName())
                .description(occupation.getDescription())
                .color(occupation.getColor())
                //.task(occupation.getTask())
                .build();

    }
    public OccupationDTO occupationToDtoWithTasks(Occupation occupation){
        return OccupationDTO.builder()
                .id(occupation.getId())
                .occupationName(occupation.getOccupationName())
                .description(occupation.getDescription())
                .task(occupation.getTask().stream().map(TaskMapper::taskToTaskDTO).toList())
                .build();

    }

}
