package com.latam.unamano.service.workerService;

import com.latam.unamano.dto.workerDto.reponse.GetWorker;
import com.latam.unamano.dto.workerDto.request.WorkerCreateDto;
import com.latam.unamano.dto.workerDto.request.WorkerUpdateDto;
import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;
import com.latam.unamano.persistence.entities.workerEntity.Worker;
import com.latam.unamano.persistence.repositories.occupationRepository.OccupationRepository;
import com.latam.unamano.persistence.repositories.user.UserRepository;
import com.latam.unamano.persistence.repositories.workerRepository.WorkerRepository;
import com.latam.unamano.service.encript.Encryptor;
import com.latam.unamano.service.jwt.JwtService;
import com.latam.unamano.utils.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class WorkerService implements WorkerServiceInterface{

    private UserRepository userRepository;
    private WorkerRepository workerRepository;
    private OccupationRepository occupationRepository;
    private Encryptor encryptorPassword;
    private final JwtService jwtService;

    public WorkerService (UserRepository userRepository,WorkerRepository workerRepository,
                          OccupationRepository occupationRepository, Encryptor encryptor,
                          JwtService jwtService){
        this.userRepository = userRepository;
        this.workerRepository = workerRepository;
        this.occupationRepository = occupationRepository;
        this.encryptorPassword = encryptor;
        this.jwtService=jwtService;
    }


    @Override
    public Optional<GetWorker> save(WorkerCreateDto workerDto) {
        workerDto.user().setRole(Role.ROLE_WORKER);
        //Method to encrypt a given password
        workerDto.user().setPassword(encryptorPassword.encrypt(workerDto.user().getPassword()));

        if (workerDto.occupations().isEmpty()) {
            throw new IllegalArgumentException("The worker must have at least one occupation.");
        }
        List<Occupation> occupationList = new ArrayList<>();

        for (Long occupationId : workerDto.occupations()) {
            Optional<Occupation> occupationOptional = occupationRepository.findById(occupationId);
            if (occupationOptional.isPresent()) {
                occupationList.add(occupationOptional.get());
            } else {
                Optional.empty();
                throw new IllegalArgumentException("One of the specified occupations does not exist.");
            }
        }
        Worker worker = new Worker();
        worker.setUser(workerDto.user());
        worker.setOccupations(occupationList);
        userRepository.save(workerDto.user());

        return Optional.of(new GetWorker(workerRepository.save(worker)));
    }


    @Override
    public Page<GetWorker> getAll(int numberPage) {
        int pageSize = 10;
        PageRequest page = PageRequest.of(numberPage,pageSize);
        Page<Worker> workers = workerRepository.findAll(page);
        return workers.map(worker -> new GetWorker(worker));
    }

    @Override
    public Optional<GetWorker> getById(Long id) {
        Optional<Worker> workerOptional = workerRepository.findById(id);
        if (workerOptional.isPresent()){
            return Optional.of(new GetWorker(workerOptional.get()));
        }
        return Optional.empty();
    }


    @Override
    public Optional<GetWorker> update(WorkerUpdateDto workerUpdateDto) {
        workerUpdateDto.user().setRole(Role.ROLE_WORKER);
        //Method to encrypt a given password
        workerUpdateDto.user().setPassword(encryptorPassword.encrypt(workerUpdateDto.user().getPassword()));

        Optional<Worker> workerOptional = workerRepository.findById(workerUpdateDto.id());
        if (workerOptional.isPresent()){
            Worker worker = workerOptional.get();

            // Clear the occupations list of the worker
            worker.getOccupations().clear();

            // Add all the new occupations to the occupations list of the worker
            List<Occupation> occupations = occupationRepository.findAllById(workerUpdateDto.occupations());

            worker.getOccupations().addAll(occupations);
            worker.setUser(workerUpdateDto.user());

            userRepository.save(workerUpdateDto.user());
            Worker upgradedWorker = workerRepository.save(worker);
            return Optional.of(new GetWorker(upgradedWorker));
        }
        return Optional.empty();
    }


    @Override
    public void delete(Long id) {
        workerRepository.deleteById(id);
    }

    @Override
    public GetWorker getWorkerData(HttpServletRequest request) {

        return new GetWorker(getWorkerByJWT(request));
    }
    private Worker getWorkerByJWT(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = header.substring(7);
        String username = jwtService.getUsernameFromToken(token);
        return workerRepository.findByUserUsername(username);
    }
}
