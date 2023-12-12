package com.latam.unamano.dto.task;

import com.latam.unamano.dto.occupationDto.OccupationDTO;
import com.latam.unamano.dto.occupationDto.OccupationMapper;
import com.latam.unamano.persistence.entities.client.Client;
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
                price(task.getPrice())
                .currencyType(task.getCurrencyType())
                .status(task.getStatus())
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
                .currencyType(taskDTO.getCurrencyType())
                .status(taskDTO.getStatus())
                .occupations(taskDTO.getOccupations().stream().map(OccupationMapper::DtoToOccupation).toList())
                .taskDate(taskDTO.getTaskDate())
                .client(taskDTO.getClient())
                .address(taskDTO.getAddress())
                .build();
    }
    public TaskDTO createTaskDTOtoDTO(CreateTaskDTO createTaskDTO){
        return TaskDTO.builder()
                .taskTitle(createTaskDTO.getTaskTitle())
                .description(createTaskDTO.getDescription())
                .price(createTaskDTO.getPrice())
                .currencyType(createTaskDTO.getCurrencyType())
                .occupations(createTaskDTO.getOccupations().stream().map(OccupationDTO::new).toList())
                .taskDate(createTaskDTO.getTaskDate())
                .address(createTaskDTO.getAddress())
                .client(new Client())
                .build();
    }
}