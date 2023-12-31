package com.latam.unamano.service.task;

import com.latam.unamano.dto.addressDto.AddressDto;
import com.latam.unamano.dto.occupationDto.OccupationDTO;
import com.latam.unamano.dto.task.CreateTaskDTO;
import com.latam.unamano.dto.task.TaskDTO;
import com.latam.unamano.dto.task.UpdateTaskDTO;
import com.latam.unamano.exceptions.BadDataEntryException;
import com.latam.unamano.dto.task.TaskMapper;
import com.latam.unamano.exceptions.OperationDeniedException;
import com.latam.unamano.exceptions.UpdateDeniedException;
import com.latam.unamano.persistence.entities.addressEntity.Address;
import com.latam.unamano.persistence.entities.postulationEntity.Postulation;
import com.latam.unamano.persistence.repositories.addressRespository.AddressRepository;
import com.latam.unamano.persistence.repositories.clientRepository.ClientRepository;
import com.latam.unamano.persistence.repositories.postulationRepository.PostulationRepository;
import com.latam.unamano.persistence.repositories.user.UserRepository;
import com.latam.unamano.service.occupationService.OccupationService;
import com.latam.unamano.utils.PostulationStatus;
import com.latam.unamano.utils.TaskStatus;
import com.latam.unamano.persistence.entities.task.Task;
import com.latam.unamano.persistence.repositories.task.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final OccupationService occupationService;
    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;
    private final PostulationRepository postulationRepository;


    public TaskService(TaskRepository taskRepository
            , OccupationService occupationService
            , UserRepository userRepository
            , AddressRepository addressRepository
            , ClientRepository clientRepository
    , PostulationRepository postulationRepository
    ){
        this.taskRepository = taskRepository;
        this.occupationService= occupationService;
        this.userRepository=userRepository;
        this.addressRepository=addressRepository;
        this.clientRepository=clientRepository;
        this.postulationRepository=postulationRepository;


    }



    public Page<Task> findTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
    @Transactional
    public TaskDTO createTask(CreateTaskDTO createTaskDTO) {
        validateTaskData(createTaskDTO);

        Task task = TaskMapper.createTaskDTOToTask(createTaskDTO);
        task.setDateCreated(LocalDateTime.now());
        task.setDateUpdated(LocalDateTime.now());
        task.setStatus(TaskStatus.PUBLISHED);
        task.setOccupations(task
                .getOccupations()
                .stream()
                .map(occupationService::findByOccupationName)
                .toList());
        task.setClient(clientRepository.findById(createTaskDTO.getClient().getId()).get());
        task.setAddress(addressRepository.save(createTaskDTO.getAddress()));

        task = taskRepository.save(task);
        System.out.println("Id del cliente " + task.getClient().getUser().getId());
        return TaskMapper.taskToTaskDTO(task);
    }

    private void validateTaskData(CreateTaskDTO createTaskDTO) {
        //taskTitle
        if(createTaskDTO.getTaskTitle()==null|| createTaskDTO.getTaskTitle().isBlank())
            throw new BadDataEntryException("El título de la tarea no puede estar vacío");
        //description
        if(createTaskDTO.getDescription()==null|| createTaskDTO.getDescription().isBlank())
            throw new BadDataEntryException("La descripción de la tarea no puede estar vacía");
        //price
        if(createTaskDTO.getPrice()==null|| createTaskDTO.getPrice().compareTo(BigDecimal.ZERO)<0)
            throw new BadDataEntryException("El precio por la tarea no puede estar vacío o ser menor a 0");
        if(createTaskDTO.getCurrencyType()==null|| createTaskDTO.getCurrencyType().isBlank())
            throw new BadDataEntryException("El tipo de moneda no puede estar vacío");
        if(createTaskDTO.getAddress()==null)
            throw new BadDataEntryException("La dirección en la que se va a realizar la tarea no puede estar vacía");
        if(createTaskDTO.getClient()==null|| createTaskDTO.getClient().getId()==null)
            throw new BadDataEntryException("Es requerido el id del cliente que desea realizar la tarea");
        if(!clientRepository.existsById(createTaskDTO.getClient().getId()))
            throw new EntityNotFoundException("No se encontró en la base de datos el cliente con el id " + createTaskDTO.getClient().getId());
        if (createTaskDTO.getOccupations()==null|| createTaskDTO.getOccupations().isEmpty())
            throw new BadDataEntryException("Es requerido ingresar al menos una ocupación o categoría para crear una tarea");
        //validar nombres de categorias
        for (OccupationDTO o: createTaskDTO.getOccupations()) {
            if(occupationService.findByOccupationName(o)==null)
                throw new EntityNotFoundException("No se encontró en la base de datos la ocupación o categoría con el nombre " + o.getOccupationName());
        }
    }

    //TODO Completar
    //TODO Separar Validaciones
    @Transactional
    public TaskDTO updateTask(UpdateTaskDTO updateTaskDTO) {
        if(!taskRepository.existsById(updateTaskDTO.getId())){
            throw new EntityNotFoundException("No existe una tarea con el id ingresado");
        }
        Task taskUpdated = taskRepository.findById(updateTaskDTO.getId()).get();
        if(!taskUpdated.getStatus().equals(TaskStatus.PUBLISHED)){
            throw new UpdateDeniedException("No pueden modificarse tareas en progreso, canceladas o finalizadas");
        }
        if (updateTaskDTO.getTaskTitle()!=null&&!updateTaskDTO.getTaskTitle().isBlank()){
                taskUpdated.setTaskTitle(updateTaskDTO.getTaskTitle());
                taskUpdated.setDateUpdated(LocalDateTime.now());
        }
        if (updateTaskDTO.getDescription()!=null&&!updateTaskDTO.getDescription().isBlank()){
                taskUpdated.setDescription(updateTaskDTO.getDescription());
                taskUpdated.setDateUpdated(LocalDateTime.now());
        }
        if((updateTaskDTO.getPrice() != null) && (updateTaskDTO.getPrice().compareTo(BigDecimal.ZERO) > 0)){
            taskUpdated.setPrice(updateTaskDTO.getPrice());
            taskUpdated.setDateUpdated(LocalDateTime.now());
        }
        if(updateTaskDTO.getCurrencyType()!=null && !updateTaskDTO.getCurrencyType().isBlank()){
            taskUpdated.setCurrencyType(updateTaskDTO.getCurrencyType());
            taskUpdated.setDateUpdated(LocalDateTime.now());
        }
        /*
        if (taskDTO.getStatus()!=null&&!taskDTO.getStatus().toString().isBlank()){
            taskUpdated.setStatus(taskDTO.getStatus());
            taskUpdated.setDateUpdated(LocalDateTime.now());
        }*/
        //update taskDate
        if(updateTaskDTO.getTaskDate()!=null){
            taskUpdated.setTaskDate(updateTaskDTO.getTaskDate());
            taskUpdated.setDateUpdated(LocalDateTime.now());
        }

        if(updateTaskDTO.getAddress()!=null){
            updateAddress(taskUpdated.getAddress().getId(), updateTaskDTO.getAddress());
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

    private void updateAddress(Long id, AddressDto address) {
        Address addressUpdated = addressRepository.findById(id).get();
        if(address.street()!=null&&!address.street().isBlank()){
            addressUpdated.setStreet(address.street());
        }
        if(address.number()!=null&&!address.number().isBlank()){
            addressUpdated.setNumber(address.number());
        }
        if(address.state()!=null&&!address.state().isBlank()){
            addressUpdated.setState(address.state());
        }
        if(address.city()!=null&&!address.city().isBlank()){
            addressUpdated.setCity(address.city());
        }
        if(address.country()!=null&&!address.country().isBlank()){
            addressUpdated.setCountry(address.country());
        }
        addressRepository.save(addressUpdated);
    }

    public TaskDTO findTaskById(Long id) {
        try {
            return TaskMapper.taskToTaskDTO(taskRepository.findById(id).get());
        }catch(Exception e){
            throw new EntityNotFoundException("No existe la tarea con el id: " + id);
        }
    }
    @Transactional
    public String deleteTaskById(Long id) {
        if(!taskRepository.existsById(id))
            throw new EntityNotFoundException("No existe la tarea con el id: " + id);
        Task task = taskRepository.findById(id).get();
        if(!task.getStatus().equals(TaskStatus.PUBLISHED))
            throw new OperationDeniedException("No está permitido eliminar tareas que ya están siendo realizadas, fueron canceladas o fueron terminadas");
        List<Postulation> postulations = postulationRepository.findByTaskId(id);
        for (Postulation p: postulations) {
            postulationRepository.deleteById(p.getId());
        }
        taskRepository.deleteById(id);
        return "La tarea con id: " + id + " fue eliminada";
    }

    @Transactional
    public String hideTaskById(Long id) {
        if(!taskRepository.existsById(id))
            throw new EntityNotFoundException("No existe la tarea con el id: " + id);
        Task task = taskRepository.findById(id).get();
        if(!task.getStatus().equals(TaskStatus.PUBLISHED))
            throw new OperationDeniedException("No está permitido eliminar tareas que ya están siendo realizadas, fueron canceladas o fueron terminadas");
        task.setStatus(TaskStatus.REMOVED);
        taskRepository.save(task);
        return "La tarea con id: " + id + " fue eliminada";

    }

    public Page<Task> findTaskByStatusPUBLISHED(Pageable pageable) {
        return taskRepository.findByStatusPUBLISHED(pageable);
    }

    public Page<Task> findTasksByOccupation(Pageable pageable, String occupationName) {

        return taskRepository.findByOccupationOccupationName(pageable, occupationName);
    }


    public Page<TaskDTO> findByClientId(Pageable pageable, Long id) {
        return taskRepository.findByClientId(pageable, id).map(TaskMapper::taskToTaskDTO);
    }

    public Page<TaskDTO> findByClientIdAndStatus(Pageable pageable, Long id, TaskStatus status) {
        return taskRepository.findByClientIdAndStatus(pageable, id, status).map(TaskMapper::taskToTaskDTO);
    }

    public Page<TaskDTO> findByWorkerIdAndStatus(Pageable pageable, Long id, TaskStatus task_status, PostulationStatus postulation_status) {
        return postulationRepository.findByWorkerIdAndTaskStatusAndStatus(pageable, id, task_status, postulation_status).map(Postulation::getTask).map(TaskMapper::taskToTaskDTO);
    }

    public TaskDTO completedTaskById(Long id) {
        if(id==null) {
            throw new BadDataEntryException("Es necesario un id para esta petición");
        }
        if(!taskRepository.existsById(id)){
            throw new EntityNotFoundException("No existe la tarea con el id ingresado");
        }
        Task task = taskRepository.findById(id).get();
        if(!task.getStatus().equals(TaskStatus.INPROGRESS)){
            throw new OperationDeniedException("Unicamente pueden marcarse como completadas las tareas que tienen un trabajador asignado");
        }
        task.setStatus(TaskStatus.COMPLETED);
        //TODO Acá quizás se pueda disparar algo para habilitar las calificaciones entre usuarios
        return TaskMapper.taskToTaskDTO(taskRepository.save(task));


    }
}
