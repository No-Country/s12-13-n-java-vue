package com.latam.unamano.controller.clientController;

import com.latam.unamano.commons.dto.response.CustomResponse;
import com.latam.unamano.dto.clientDto.request.ClientCreateDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static com.latam.unamano.commons.constants.GlobalApiConstant.*;
public interface ClientControllerInterface {
    @PostMapping(REGISTER)
    ResponseEntity<CustomResponse> saveClient(@Valid @RequestBody ClientCreateDto clientCreateDto);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllClient(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getClientById(@PathVariable Long id);

    @PutMapping
    @Secured("ROLE_CLIENT")
    ResponseEntity<CustomResponse> updateClient(@Valid @RequestBody ClientCreateDto clientCreateDto);

    @DeleteMapping(ID_PARAM)
    @Secured("ROLE_CLIENT")
    ResponseEntity<CustomResponse> deleteClientById(@PathVariable Long id);

}
