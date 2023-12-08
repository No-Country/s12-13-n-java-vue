package com.latam.unamano.controller.postulationController;

import com.latam.unamano.commons.controller.GenericRestController;
import com.latam.unamano.commons.dto.response.CustomResponse;
import com.latam.unamano.dto.postulationDto.CreatePostulation;
import com.latam.unamano.dto.postulationDto.UpdatePostulation;
import com.latam.unamano.persistence.entities.postulationEntity.Postulation;
import com.latam.unamano.service.postulationService.PostulationServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.latam.unamano.commons.constants.GlobalApiConstant.*;
import static com.latam.unamano.controller.postulationController.constanst.PostulationConstants.REQUEST_POSTULATION;

@RestController
@RequestMapping(REQUEST_POSTULATION)
public class PostulationControllerImp extends GenericRestController implements PostulationControllerInterface{

    private PostulationServiceInterface postulationServiceInterface;

    public PostulationControllerImp(PostulationServiceInterface postulationServiceInterface){
        this.postulationServiceInterface = postulationServiceInterface;
    }

    @Override
    public ResponseEntity<CustomResponse> savePostulation(CreatePostulation createPostulation) {
        Postulation postulation = postulationServiceInterface.save(createPostulation);
        return ok(postulation,CREATED,REQUEST_POSTULATION);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllPostulations(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<CustomResponse> getPostulationById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<CustomResponse> updatePostulation(UpdatePostulation updatePostulation) {
        return null;
    }

    @Override
    public ResponseEntity<CustomResponse> deletePostulationById(Long id) {
        return null;
    }
}
