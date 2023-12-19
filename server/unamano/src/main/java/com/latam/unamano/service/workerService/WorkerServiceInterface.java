package com.latam.unamano.service.workerService;

import com.latam.unamano.dto.workerDto.reponse.GetWorker;
import com.latam.unamano.dto.workerDto.request.WorkerCreateDto;
import com.latam.unamano.dto.workerDto.request.WorkerUpdateDto;
import com.latam.unamano.persistence.entities.workerEntity.Worker;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface WorkerServiceInterface {

    Optional<GetWorker> save(WorkerCreateDto workerCreateDto);
    Page<GetWorker> getAll(int numberPage);
    Optional<GetWorker> getById(Long id);
    Optional<GetWorker> update(WorkerUpdateDto workerUpdateDto);
    void delete(Long id);

    GetWorker getWorkerData(HttpServletRequest request);
}
