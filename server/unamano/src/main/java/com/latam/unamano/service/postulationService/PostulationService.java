package com.latam.unamano.service.postulationService;

import com.latam.unamano.dto.postulationDto.request.AcceptPostulation;
import com.latam.unamano.dto.postulationDto.request.CreatePostulation;
import com.latam.unamano.dto.postulationDto.request.UpdatePostulation;
import com.latam.unamano.dto.postulationDto.response.PostulationResponse;
import com.latam.unamano.dto.task.TaskDTO;
import com.latam.unamano.dto.task.TaskMapper;
import com.latam.unamano.persistence.entities.postulationEntity.Postulation;
import com.latam.unamano.persistence.entities.task.Task;
import com.latam.unamano.persistence.entities.workerEntity.Worker;
import com.latam.unamano.persistence.repositories.postulationRepository.PostulationRepository;
import com.latam.unamano.persistence.repositories.task.TaskRepository;
import com.latam.unamano.persistence.repositories.workerRepository.WorkerRepository;
import com.latam.unamano.utils.PostulationStatus;
import com.latam.unamano.utils.TaskStatus;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostulationService implements PostulationServiceInterface{

    private PostulationRepository postulationRepository;
    private WorkerRepository workerRepository;
    private TaskRepository taskRepository;

    public PostulationService(PostulationRepository postulationRepository,
                              WorkerRepository workerRepository, TaskRepository taskRepository){
        this.postulationRepository = postulationRepository;
        this.workerRepository = workerRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Postulation> save(CreatePostulation createPostulation) {
        Optional<Worker> workerOptional = workerRepository.findById(createPostulation.worker_id());
        Optional<Task> taskOptional = taskRepository.findById(createPostulation.task_id());
        if (workerOptional.isPresent() && taskOptional.isPresent()){
            Postulation postulation = new Postulation();
            postulation.setStatus(PostulationStatus.STARTED);
                postulation.setWorker(workerOptional.get());
                postulation.setTask(taskOptional.get());
            return Optional.of(postulationRepository.save(postulation));
        }
        return Optional.empty();
    }

    @Override
    public Page<PostulationResponse> getAll(int numberPage) {
        int pageSize = 10;
        PageRequest page = PageRequest.of(numberPage,pageSize);
        Page<Postulation> postulations = postulationRepository.findAll(page);
        return postulations.map(postulation -> new PostulationResponse(postulation));
    }

    /**
     * *********** JUAN ***************
     * get list of postulations by worker id
     * @param idWorker
     * @return List<Postulation>
     */
    @Override
    public Page<Postulation> getAllByWorkerId(Long idWorker, Pageable pageable) {
        Optional<Worker> workerOptional = workerRepository.findById(idWorker);

        if (workerOptional.isPresent()){
            return postulationRepository.getAllByWorkerId(pageable, idWorker);
        }
        return Page.empty();
    }

    @Override
    public Optional<Postulation> getById(Long id) {
        Optional<Postulation> postulationOptional = postulationRepository.findById(id);
        if (postulationOptional.isPresent()){
            return Optional.of(postulationOptional.get());
        }
        return Optional.empty();
    }

    /***
     * change application status
     * @param updatePostulation
     * @return Postulation
     */
    @Override
    public Optional<Postulation> update(UpdatePostulation updatePostulation) {
        Optional<Postulation> postulationOptional = postulationRepository.findById(updatePostulation.id());
        if (postulationOptional.isPresent()){
            Postulation postulation = new Postulation();
            postulation.setStatus(updatePostulation.status());
            return Optional.of(postulationRepository.save(postulation));
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        postulationRepository.deleteById(id);
    }

    @Override
    public Page<PostulationResponse> getPostulationsByTaskId(Pageable pageable, Long idTask) {
        try{
            return postulationRepository.getAllByTaskId(pageable, idTask).map(PostulationResponse::new);
        }catch (Exception e){
            throw new EntityNotFoundException("No existe una tarea con el id " + idTask);
        }

    }

    @Override
    public TaskDTO acceptPostulation(AcceptPostulation acceptPostulation) {
        try{
		Long w_id=null;
		Long c_id;
            List<Postulation> postulations = postulationRepository.getAllByTaskId(acceptPostulation.task_id());
            for (Postulation p: postulations) {
                if (p.getId().equals(acceptPostulation.postulation_id())){
                    p.setStatus(PostulationStatus.APPROVED);
			        w_id= p.getWorker().getId();
                } else {
                    p.setStatus(PostulationStatus.CANCELED);
                }
                postulationRepository.save(p);
            }
            Task task = taskRepository.findById(acceptPostulation.task_id()).get();
            c_id= task.getClient().getId();
            if(w_id!=null&&c_id!=null)
                System.out.println("Id del worker: " + w_id + " id del cliente " + c_id);
            task.setStatus(TaskStatus.INPROGRESS);
            taskRepository.save(task);
		//sale el chat
            return TaskMapper.taskToTaskDTO(task);
        }catch (Exception e){
            throw new EntityNotFoundException("Error en el ingreso de datos." +
                    "No existe una tarea o una postulación con los id ingresados");
        }

    }
}
