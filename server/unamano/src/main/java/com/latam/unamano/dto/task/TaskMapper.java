package com.latam.unamano.dto.task;

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
                price(task.getPrice()).
                status(task.getStatus())
                //.occupations(task.getOccupations().stream().map(OccupationMapper::occupationToDto).toList())
                .taskDate(task.getTaskDate())
                .build();
    }
    public Task taskDTOToTask(TaskDTO taskDTO){
        return Task.builder()
                .taskTitle(taskDTO.getTaskTitle())
                .description(taskDTO.getDescription())
                .dateCreated(taskDTO.getDateCreated())
                .dateUpdated(taskDTO.getDateUpdated())
                .price(taskDTO.getPrice())
                .status(taskDTO.getStatus())
                //.occupations(taskDTO.getOccupations().stream().map(OccupationMapper::DtoToOccupation).toList())
                .taskDate(taskDTO.getTaskDate())
                .build();
    }
}