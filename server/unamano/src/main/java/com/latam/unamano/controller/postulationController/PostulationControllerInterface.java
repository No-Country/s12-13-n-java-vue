package com.latam.unamano.controller.postulationController;

import com.latam.unamano.commons.dto.response.CustomResponse;
import com.latam.unamano.dto.postulationDto.CreatePostulation;
import com.latam.unamano.dto.postulationDto.UpdatePostulation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.latam.unamano.commons.constants.GlobalApiConstant.*;

public interface PostulationControllerInterface {

    @PostMapping
    ResponseEntity<CustomResponse> savePostulation(@RequestBody CreatePostulation createPostulation);

    @GetMapping(GET_ALL_BY_ID)
    ResponseEntity<CustomResponse> getAllPostulations(@PathVariable Long id);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getPostulationById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> updatePostulation(@RequestBody UpdatePostulation updatePostulation);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deletePostulationById(@PathVariable Long id);

}
