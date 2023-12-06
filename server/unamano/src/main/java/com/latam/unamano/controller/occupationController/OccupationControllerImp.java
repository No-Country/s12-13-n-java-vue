package com.latam.unamano.controller.occupationController;

import com.latam.unamano.commons.controller.GenericRestController;
import com.latam.unamano.commons.dto.response.CustomResponse;
import com.latam.unamano.persistence.entities.ocupationEntity.Occupation;

import com.latam.unamano.service.occupationService.OccupationServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.latam.unamano.commons.constants.GlobalApiConstant.DELETED_SUCCESSFULLY;
import static com.latam.unamano.commons.constants.GlobalApiConstant.NOT_FOUND;
import static com.latam.unamano.controller.occupationController.constanst.OccupationConstants.REQUEST_OCCUPATION;

@RestController
@RequestMapping(REQUEST_OCCUPATION)
public class OccupationControllerImp extends GenericRestController implements OccupationControllerInterface{

    private OccupationServiceInterface occupationServiceInterface;

    public OccupationControllerImp(OccupationServiceInterface occupationServiceInterface){
        this.occupationServiceInterface = occupationServiceInterface;
    }

    @Override
    public ResponseEntity<CustomResponse> saveOccupation(Occupation occupation) {
        return ok(occupationServiceInterface.save(occupation),null,REQUEST_OCCUPATION);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllOccupation() {
        return ok(occupationServiceInterface.getAll(),null,REQUEST_OCCUPATION);
    }

    @Override
    public ResponseEntity<CustomResponse> getOccupationById(Long id) {
        Optional<Occupation> occupation = occupationServiceInterface.getById(id);
        if (occupation.isEmpty()){
            return notFound(null,NOT_FOUND,REQUEST_OCCUPATION);
        }
        return ok(occupation,null,REQUEST_OCCUPATION);
    }

    @Override
    public ResponseEntity<CustomResponse> updateOccupation(Occupation occupation) {
        Optional<Occupation> updateOccupation = occupationServiceInterface.update(occupation);
        if (updateOccupation.isEmpty()){
            return notFound(null,NOT_FOUND,REQUEST_OCCUPATION);
        }
        return ok(occupation,null,REQUEST_OCCUPATION);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteOccupationById(Long id) {
        occupationServiceInterface.delete(id);
        return ok(null,DELETED_SUCCESSFULLY, REQUEST_OCCUPATION);
    }
}
