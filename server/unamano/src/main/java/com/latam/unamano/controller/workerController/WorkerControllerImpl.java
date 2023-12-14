package com.latam.unamano.controller.workerController;

import com.latam.unamano.commons.controller.GenericRestController;
import com.latam.unamano.commons.dto.response.CustomResponse;
import com.latam.unamano.dto.workerDto.reponse.GetWorker;
import com.latam.unamano.dto.workerDto.request.WorkerCreateDto;
import com.latam.unamano.dto.workerDto.request.WorkerUpdateDto;

import com.latam.unamano.service.workerService.WorkerServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.latam.unamano.commons.constants.GlobalApiConstant.*;
import static com.latam.unamano.controller.workerController.constants.WorkerConstants.REQUEST_WORKER;

@RestController
@RequestMapping(REQUEST_WORKER)
public class WorkerControllerImpl extends GenericRestController implements WorkerControllerInterface{

    private WorkerServiceInterface workerServiceInterface;

    public WorkerControllerImpl(WorkerServiceInterface workerServiceInterface){
        this.workerServiceInterface = workerServiceInterface;
    }

    @Override
    public ResponseEntity saveWorker(WorkerCreateDto workerCreateDto) {
        return ok(workerServiceInterface.save(workerCreateDto),CREATED,REQUEST_WORKER);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllWorker(int numberPage) {
        Page<GetWorker> workerPage = workerServiceInterface.getAll(numberPage);
        return ok(workerPage,null,REQUEST_WORKER);
    }

    @Override
    public ResponseEntity<CustomResponse> getWorkerById(Long id) {
        Optional<GetWorker> worker = workerServiceInterface.getById(id);
        if (worker.isEmpty()){
            return notFound(null,NOT_FOUND,REQUEST_WORKER);
        }
        return ok(worker,null,REQUEST_WORKER);
    }

    @Override
    public ResponseEntity<CustomResponse> updateWorker(WorkerUpdateDto workerUpdateDto) {
        Optional<GetWorker> upgradedWorker = workerServiceInterface.update(workerUpdateDto);
        if (upgradedWorker.isEmpty()){
            return notFound(null,NOT_FOUND,REQUEST_WORKER);
        }
        return ok(upgradedWorker,null,REQUEST_WORKER);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteWorkerById(Long id) {
        workerServiceInterface.delete(id);
        return ok(null,DELETED_SUCCESSFULLY,REQUEST_WORKER);
    }
    @GetMapping("/data")
    @Secured("ROLE_WORKER")
    public GetWorker getWorkerData( HttpServletRequest request){
        return workerServiceInterface.getWorkerData(request);
    }

}
