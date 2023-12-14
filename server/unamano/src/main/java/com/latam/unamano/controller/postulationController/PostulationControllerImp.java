package com.latam.unamano.controller.postulationController;

import com.latam.unamano.commons.controller.GenericRestController;
import com.latam.unamano.commons.dto.response.CustomResponse;
import com.latam.unamano.dto.postulationDto.request.CreatePostulation;
import com.latam.unamano.dto.postulationDto.request.UpdatePostulation;
import com.latam.unamano.dto.postulationDto.response.PostulationResponse;
import com.latam.unamano.persistence.entities.postulationEntity.Postulation;
import com.latam.unamano.service.postulationService.PostulationServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

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
        Optional<Postulation> postulation = postulationServiceInterface.save(createPostulation);
        return ok(new PostulationResponse(postulation.get()),CREATED,REQUEST_POSTULATION);
    }

    @Override
    public ResponseEntity<CustomResponse> getAll(int numberPage) {
        Page<PostulationResponse> postulations = postulationServiceInterface.getAll(numberPage);
        return ok(postulations,null,REQUEST_POSTULATION);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllPostulations(Long id, Pageable pageable) {
        return ok(postulationServiceInterface.getAllByWorkerId(id, pageable).map(PostulationResponse::new),null,REQUEST_POSTULATION);
    }

    @Override
    public ResponseEntity<CustomResponse> getPostulationById(Long id) {
        Optional<Postulation> postulation = postulationServiceInterface.getById(id);
        if (postulation.isEmpty()){
            return notFound(null,NOT_FOUND,REQUEST_POSTULATION);
        }
        return ok(postulation,null,REQUEST_POSTULATION);
    }

    @Override
    public ResponseEntity<CustomResponse> updatePostulation(UpdatePostulation updatePostulation) {
        Optional<Postulation> postulation = postulationServiceInterface.update(updatePostulation);
        if (postulation.isEmpty()){
            return notFound(null,NOT_FOUND,REQUEST_POSTULATION);
        }
        return ok(postulation,null,REQUEST_POSTULATION);
    }

    @Override
    public ResponseEntity<CustomResponse> deletePostulationById(Long id) {
        postulationServiceInterface.delete(id);
        return ok(null,DELETED_SUCCESSFULLY,REQUEST_POSTULATION);
    }
}
