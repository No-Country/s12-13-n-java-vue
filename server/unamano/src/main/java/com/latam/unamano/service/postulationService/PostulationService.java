package com.latam.unamano.service.postulationService;

import com.latam.unamano.dto.postulationDto.request.CreatePostulation;
import com.latam.unamano.dto.postulationDto.request.CreatePostulationByUser;
import com.latam.unamano.dto.postulationDto.request.UpdatePostulation;
import com.latam.unamano.dto.postulationDto.response.PostulationResponse;
import com.latam.unamano.persistence.entities.postulationEntity.Postulation;
import com.latam.unamano.persistence.entities.task.Task;
import com.latam.unamano.persistence.entities.workerEntity.Worker;
import com.latam.unamano.persistence.repositories.postulationRepository.PostulationRepository;
import com.latam.unamano.persistence.repositories.task.TaskRepository;
import com.latam.unamano.persistence.repositories.user.UserRepository;
import com.latam.unamano.persistence.repositories.workerRepository.WorkerRepository;
import com.latam.unamano.utils.PostulationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class PostulationService implements PostulationServiceInterface{

    private PostulationRepository postulationRepository;
    private UserRepository userRepository;
    private WorkerRepository workerRepository;
    private TaskRepository taskRepository;

    public PostulationService(PostulationRepository postulationRepository,
            UserRepository userRepository,WorkerRepository workerRepository, TaskRepository taskRepository){
        this.postulationRepository = postulationRepository;
        this.userRepository = userRepository;
        this.workerRepository = workerRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * Save new application by Worker ID and Task ID
     * @param createPostulation
     * @return Postulation
     */
    @Override
    public Optional<Postulation> save(CreatePostulation createPostulation) {
        Optional<Worker> workerOptional = workerRepository.findById(createPostulation.worker_id());
        Optional<Task> taskOptional = taskRepository.findById(createPostulation.task_id());
        if (workerOptional.isPresent() || taskOptional.isPresent()){
            Postulation postulation = new Postulation();
            postulation.setStatus(PostulationStatus.STARTED);
            postulation.setWorker(workerOptional.get());
            postulation.setTask(taskOptional.get());
            return Optional.of(postulationRepository.save(postulation));
        }
        return Optional.empty();
    }

    /**
     * Save new application by User ID and Task ID
     * @param createPostulationByUser
     * @return Postulation
     */
    @Override
    public Optional<Postulation> saveByUserId(CreatePostulationByUser createPostulationByUser) {
        //search worker by user id
        Optional<Worker> workerOptional = workerRepository.findByUserId(createPostulationByUser.user_id());
        Optional<Task> taskOptional = taskRepository.findById(createPostulationByUser.task_id());
        if (workerOptional.isPresent() || taskOptional.isPresent()){
            Worker worker = workerOptional.get();
            Postulation postulation = new Postulation();
            postulation.setStatus(PostulationStatus.STARTED);
            postulation.setWorker(worker);
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
    public List<Postulation> getAllByWorkerId(Long idWorker) {
        Optional<Worker> workerOptional = workerRepository.findById(idWorker);
        if (workerOptional.isPresent()){

            //TODO: get a list of postulations per worker id
            // return Optional.of(PostulationRepository......);

            return null;
        }
        return Collections.emptyList();
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
}
