package com.latam.unamano.dto.task;

import com.latam.unamano.dto.occupationDto.OccupationMapper;
import com.latam.unamano.persistence.entities.task.Task;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TaskMapper {
    public TaskDTO taskToTaskDTO(Task task){
        return TaskDTO.builder().
                id(task.getId()).
                taskTitle(task.getTaskTitle()).
                description(task.getDescription()).
                dateCreated(task.getDateCreated()).
                dateUpdated(task.getDateUpdated()).
<<<<<<< HEAD
                price(task.getPrice()).
                status(task.getStatus())
=======
                price(task.getPrice())
                .currencyType(task.getCurrencyType())
                .status(task.getStatus())
>>>>>>> dev-backend
                .occupations(task.getOccupations().stream().map(OccupationMapper::occupationToDto).toList())
                .taskDate(task.getTaskDate())
                //.client(task.getClient())
                .address(task.getAddress())
                .build();
    }
    public Task taskDTOToTask(TaskDTO taskDTO){
        return Task.builder()
                .taskTitle(taskDTO.getTaskTitle())
                .description(taskDTO.getDescription())
                .dateCreated(taskDTO.getDateCreated())
                .dateUpdated(taskDTO.getDateUpdated())
                .price(taskDTO.getPrice())
<<<<<<< HEAD
=======
                .currencyType(taskDTO.getCurrencyType())
>>>>>>> dev-backend
                .status(taskDTO.getStatus())
                .occupations(taskDTO.getOccupations().stream().map(OccupationMapper::DtoToOccupation).toList())
                .taskDate(taskDTO.getTaskDate())
                .client(taskDTO.getClient())
                .address(taskDTO.getAddress())
                .build();
    }
}