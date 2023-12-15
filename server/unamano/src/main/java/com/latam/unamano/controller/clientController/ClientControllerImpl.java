package com.latam.unamano.controller.clientController;

import com.latam.unamano.commons.controller.GenericRestController;
import com.latam.unamano.commons.dto.response.CustomResponse;
import com.latam.unamano.dto.clientDto.request.ClientCreateDto;

import com.latam.unamano.dto.clientDto.response.GetClient;
import com.latam.unamano.dto.workerDto.reponse.GetWorker;
import com.latam.unamano.service.clientService.ClientServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.latam.unamano.commons.constants.GlobalApiConstant.*;
import static com.latam.unamano.controller.clientController.constants.ClientConstants.REQUEST_CLIENT;

@RestController
@RequestMapping(REQUEST_CLIENT)
public class ClientControllerImpl extends GenericRestController implements ClientControllerInterface {

    private ClientServiceInterface clientServiceInterface;

    public ClientControllerImpl(ClientServiceInterface clientServiceInterface){
        this.clientServiceInterface = clientServiceInterface;
    }

    @Override
    public ResponseEntity<CustomResponse> saveClient(ClientCreateDto clientCreateDto) {
        return ok(clientServiceInterface.save(clientCreateDto),CREATED,REQUEST_CLIENT);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllClient(int numberPage) {
        Page<GetClient> clients = clientServiceInterface.getAll(numberPage);
        return ok(clients,null,REQUEST_CLIENT);
    }

    @Override
    public ResponseEntity<CustomResponse> getClientById(Long id) {
       Optional<GetClient> clients = clientServiceInterface.getById(id);
       if (clients.isEmpty()){
           return notFound(clients,NOT_FOUND,REQUEST_CLIENT);
       }
        return ok(clients,null,REQUEST_CLIENT);
    }

    @Override
    public ResponseEntity<CustomResponse> updateClient(ClientCreateDto clientCreateDto) {
        Optional<GetClient> updateClient = clientServiceInterface.update(clientCreateDto);
        if (updateClient.isEmpty()){
            notFound(null,NOT_FOUND,REQUEST_CLIENT);
        }
        return ok(updateClient,null,REQUEST_CLIENT);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteClientById(Long id) {
        return null;
    }

    @GetMapping("/data")
    @Secured("ROLE_CLIENT")
    public GetClient getClientData(HttpServletRequest request){
        return clientServiceInterface.getClientData(request);
    }
}
