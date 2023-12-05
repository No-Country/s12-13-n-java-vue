package com.latam.unamano.controller.occupationController;

import com.latam.unamano.commons.dto.response.CustomResponse;
import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.latam.unamano.commons.constants.GlobalApiConstant.ID_PARAM;

public interface OccupationControllerInterface {

    @PostMapping
    ResponseEntity<CustomResponse> saveOccupation(@RequestBody Occupation occupation);

    @GetMapping
    ResponseEntity<CustomResponse> getAllOccupation();

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getOccupationById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> updateOccupation(@RequestBody Occupation occupation);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deleteOccupationById(@PathVariable Long id);

}
