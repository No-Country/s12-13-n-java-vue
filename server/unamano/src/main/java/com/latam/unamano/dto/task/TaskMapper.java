package com.latam.unamano.dto.task;

import com.latam.unamano.dto.clientDto.response.ClientData;
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
                .client(new ClientData(
                        task.getClient().getId(),
                        task.getClient().getUser().getId(),
                        task.getClient().getUser().getUsername(),
                        task.getClient().getUser().getFirstName(),
                        task.getClient().getUser().getLastName(),
                        task.getClient().getUser().getEmail(),
                        task.getClient().getUser().getRole(),
                        task.getClient().getUser().getAddresses().getId(),
                        task.getClient().getUser().getAddresses().getCity(),
                        task.getClient().getUser().getAddresses().getCountry(),
                        task.getClient().getUser().getProfilePicture())
                        )
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
                .client(new Client(taskDTO.getClient().getId(), null, null,null))
                .address(taskDTO.getAddress())
                .build();
    }

    public static Task createTaskDTOToTask(CreateTaskDTO createTaskDTO) {
        return Task.builder()
                .taskTitle(createTaskDTO.getTaskTitle())
                .description(createTaskDTO.getDescription())
                .price(createTaskDTO.getPrice())
                .currencyType(createTaskDTO.getCurrencyType())
                .occupations(createTaskDTO.getOccupations().stream().map(OccupationMapper::DtoToOccupation).toList())
                .taskDate(createTaskDTO.getTaskDate())
                .client(new Client(createTaskDTO.getClient().getId(), null, null,null))
                .address(createTaskDTO.getAddress())
                .build();
    }
}