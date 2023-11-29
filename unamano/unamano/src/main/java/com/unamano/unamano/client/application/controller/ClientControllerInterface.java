package com.unamano.unamano.client.application.controller;

import com.unamano.unamano.commons.dto.response.CustomResponse;
import com.unamano.unamano.client.domain.model.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.unamano.unamano.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;


public interface ClientControllerInterface {

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAll(@PathVariable int numberPage);

    @PostMapping
    ResponseEntity<CustomResponse> save(@RequestBody Client client);


    // TODO: complete with methods-> GetById, Create, Update, delete...
}
