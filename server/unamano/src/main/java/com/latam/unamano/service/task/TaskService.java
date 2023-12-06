package com.latam.unamano.service.task;

import com.latam.unamano.dto.occupationDto.OccupationDTO;
import com.latam.unamano.dto.occupationDto.OccupationMapper;
import com.latam.unamano.dto.task.TaskDTO;
import com.latam.unamano.exceptions.BadDataEntryException;
import com.latam.unamano.dto.task.TaskMapper;
import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;
import com.latam.unamano.persistence.repositories.addressRespository.AddressRepository;
import com.latam.unamano.persistence.repositories.user.UserRepository;
import com.latam.unamano.service.occupationService.OccupationService;
import com.latam.unamano.utils.TaskStatus;
import com.latam.unamano.persistence.entities.task.Task;
import com.latam.unamano.persistence.repositories.task.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final OccupationService occupationService;
    private final AddressRepository addressRepository;

    public TaskService(TaskRepository taskRepository, OccupationService occupationService, UserRepository userRepository, AddressRepository addressRepository){
        this.taskRepository = taskRepository;
        this.occupationService= occupationService;
        this.userRepository=userRepository;
        this.addressRepository=addressRepository;


    }



    public Page<Task> findTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
    @Transactional
    public TaskDTO createTask(TaskDTO taskDTO) {
        validateTaskData(taskDTO);
        taskDTO.setDateCreated(LocalDateTime.now());
        taskDTO.setDateUpdated(LocalDateTime.now());
        taskDTO.setStatus(TaskStatus.PUBLISHED);
        Task task = TaskMapper.taskDTOToTask(taskDTO);

        task.setOccupations(task
                .getOccupations()
                .stream()
                .map(occupationService::findByOccupationName)
                .toList());



        //task.setClient(userRepository.findById(taskDTO.getClient().getId()).get());
        task.setAddress(addressRepository.save(taskDTO.getAddress()));

        task = taskRepository.save(task);
        return TaskMapper.taskToTaskDTO(task);
    }

    private void validateTaskData(TaskDTO taskDTO) {
        //taskTitle
        if(taskDTO.getTaskTitle()==null||taskDTO.getTaskTitle().isBlank())
            throw new BadDataEntryException("El título de la tarea no puede estar vacío");
        //description
        if(taskDTO.getDescription()==null||taskDTO.getDescription().isBlank())
            throw new BadDataEntryException("La descripción de la tarea no puede estar vacía");
        //price
        if(taskDTO.getPrice()==null||taskDTO.getPrice().compareTo(BigDecimal.ZERO)<0)
            throw new BadDataEntryException("El precio por la tarea no puede estar vacío o ser menor a 0");
        if(taskDTO.getAddress()==null)
            throw new BadDataEntryException("La dirección en la que se va a realizar la tarea no puede estar vacía");
        if(taskDTO.getClient()==null|| taskDTO.getClient().getId()==null)
            throw new BadDataEntryException("Es requerido el id del cliente que desea realizar la tarea");
        if(!userRepository.existsById(taskDTO.getClient().getId()))
            throw new EntityNotFoundException("No se encontró en la base de datos el cliente con el id " + taskDTO.getClient().getId());
        if (taskDTO.getOccupations()==null||taskDTO.getOccupations().isEmpty())
            throw new BadDataEntryException("Es requerido ingresar al menos una ocupación o categoría para crear una tarea");
        //validar nombres de categorias
        for (OccupationDTO o:taskDTO.getOccupations()) {
            if(occupationService.findByOccupationName(o)==null)
                throw new EntityNotFoundException("No se encontró en la base de datos la ocupación o categoría con el nombre " + o.getOccupationName());
        }
    }

    //TODO Completar
    //TODO Separar Validaciones
    @Transactional
    public TaskDTO updateTask(TaskDTO taskDTO) {
        if(!taskRepository.existsById(taskDTO.getId())){
            throw new EntityNotFoundException("No existe una tarea con el id ingresado");
        }
        Task taskUpdated = taskRepository.findById(taskDTO.getId()).get();
        if (taskDTO.getTaskTitle()!=null&&!taskDTO.getTaskTitle().isBlank()){
                taskUpdated.setTaskTitle(taskDTO.getTaskTitle());
                taskUpdated.setDateUpdated(LocalDateTime.now());
        }
        if (taskDTO.getDescription()!=null&&!taskDTO.getDescription().isBlank()){
                taskUpdated.setDescription(taskDTO.getDescription());
                taskUpdated.setDateUpdated(LocalDateTime.now());
        }
        if((taskDTO.getPrice() != null) && (taskDTO.getPrice().compareTo(BigDecimal.ZERO) > 0)){
            taskUpdated.setPrice(taskDTO.getPrice());
            taskUpdated.setDateUpdated(LocalDateTime.now());
        }
        if (taskDTO.getStatus()!=null&&!taskDTO.getStatus().toString().isBlank()){
            taskUpdated.setStatus(taskDTO.getStatus());
            taskUpdated.setDateUpdated(LocalDateTime.now());
        }
        //update taskDate
        if(taskDTO.getTaskDate()!=null){
            taskUpdated.setTaskDate(taskDTO.getTaskDate());
            taskUpdated.setDateUpdated(LocalDateTime.now());
        }

        //update Occupations
        //TODO IN PROGRESS
/*
        if(taskDTO.getOccupations()!=null){
            taskUpdated.getOccupations().clear();
            for (OccupationDTO oc: taskDTO.getOccupations()){
                taskUpdated.getOccupations().add(occupationService.findByOccupationName(oc));
            }

            taskUpdated.setDateUpdated(LocalDateTime.now());
        }*/
        taskUpdated=taskRepository.save(taskUpdated);

        return TaskMapper.taskToTaskDTO(taskUpdated);


    }

    public TaskDTO findTaskById(Long id) {
        try {
            return TaskMapper.taskToTaskDTO(taskRepository.findById(id).get());
        }catch(Exception e){
            throw new EntityNotFoundException("No existe la tarea con el id: " + id);
        }
    }

    public String deleteTaskById(Long id) {
        try {
            taskRepository.deleteById(id);
            return "La tarea con id: " + id + " fue eliminada";
        }catch(Exception e){
            throw new EntityNotFoundException("No existe la tarea con el id: " + id);
        }
    }


    public String hideTaskById(Long id) {
        try {
            Task task = taskRepository.findById(id).get();
            task.setStatus(TaskStatus.REMOVED);
            taskRepository.save(task);
            return "La tarea con id: " + id + " fue eliminada";
        }catch(Exception e){
            throw new EntityNotFoundException("No existe la tarea con el id: " + id);
        }
    }

    public Page<Task> findTaskByStatusPUBLISHED(Pageable pageable) {
        return taskRepository.findByStatusPUBLISHED(pageable);
    }

    public Page<Task> findTasksByOccupation(Pageable pageable, String occupationName) {

        return taskRepository.findByOccupationOccupationName(pageable, occupationName);
    }


}
