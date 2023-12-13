package com.latam.unamano.controller.postulationController;

import com.latam.unamano.commons.dto.response.CustomResponse;
import com.latam.unamano.dto.postulationDto.request.CreatePostulation;
import com.latam.unamano.dto.postulationDto.request.UpdatePostulation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static com.latam.unamano.commons.constants.GlobalApiConstant.*;

public interface PostulationControllerInterface {

    @PostMapping
    ResponseEntity<CustomResponse> savePostulation(@RequestBody CreatePostulation createPostulation);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAll(@PathVariable int numberPage);

    @GetMapping(GET_ALL_BY_ID)
    @Secured("ROLE_WORKER")
    ResponseEntity<CustomResponse> getAllPostulations(@PathVariable Long idWorker);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getPostulationById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> updatePostulation(@RequestBody UpdatePostulation updatePostulation);

    @DeleteMapping(ID_PARAM)
    @Secured("ROLE_WORKER")
    ResponseEntity<CustomResponse> deletePostulationById(@PathVariable Long id);

}
