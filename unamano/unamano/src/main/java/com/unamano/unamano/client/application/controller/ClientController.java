package com.unamano.unamano.client.application.controller;

import com.unamano.unamano.commons.dto.response.CustomResponse;
import com.unamano.unamano.client.application.service.ClientUseCase;
import com.unamano.unamano.client.domain.model.Client;
import org.springframework.http.ResponseEntity;
import com.unamano.unamano.commons.controller.GenericRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.unamano.unamano.client.application.controller.constants.ClientConstants.REQUEST_CLIENT;

import static com.unamano.unamano.commons.constants.GlobalApiConstant.*;


@RestController
@RequestMapping(REQUEST_CLIENT)
public class ClientController extends GenericRestController implements ClientControllerInterface {

    private ClientUseCase clientUseCase;

    public ClientController(ClientUseCase clientUseCase){
        this.clientUseCase = clientUseCase;
    }

    @Override
    public ResponseEntity<CustomResponse> getAll(int numberPage) {
        return ok(clientUseCase.getAll(numberPage),CREATED,REQUEST_CLIENT);

    }

    @Override
    public ResponseEntity<CustomResponse> save(Client client) {
        return ok(clientUseCase.save(client),CREATED,REQUEST_CLIENT);
    }

    // TODO: complete with UserControllerInterface methods...
}
