package com.latam.unamano.service.clientService;

import com.latam.unamano.dto.clientDto.request.ClientCreateDto;
import com.latam.unamano.dto.clientDto.response.GetClient;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ClientServiceInterface {
    Optional<GetClient> save(ClientCreateDto clientCreateDto);
    Page<GetClient> getAll(int numberPage);
    Optional<GetClient> getById(Long id);

    Optional<GetClient> update(ClientCreateDto clientCreateDto);

    void delete(Long id);
}
