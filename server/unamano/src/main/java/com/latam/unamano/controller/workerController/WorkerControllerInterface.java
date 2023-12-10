package com.latam.unamano.controller.workerController;

import com.latam.unamano.commons.dto.response.CustomResponse;
import com.latam.unamano.dto.workerDto.request.WorkerCreateDto;
import com.latam.unamano.dto.workerDto.request.WorkerUpdateDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static com.latam.unamano.commons.constants.GlobalApiConstant.*;
public interface WorkerControllerInterface {
    @PostMapping(REGISTER)
    ResponseEntity<CustomResponse> saveWorker(@Valid @RequestBody WorkerCreateDto workerCreateDto);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllWorker(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getWorkerById(@PathVariable Long id);

    @PutMapping
    @Secured("ROLE_WORKER")
    ResponseEntity<CustomResponse> updateWorker(@Valid @RequestBody WorkerUpdateDto workerUpdateDto);

    @DeleteMapping(ID_PARAM)
    @Secured("ROLE_WORKER")
    ResponseEntity<CustomResponse> deleteWorkerById(@PathVariable Long id);

}
