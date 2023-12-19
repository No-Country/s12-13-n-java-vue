package com.latam.unamano.service.clientService;

import com.latam.unamano.dto.clientDto.request.ClientCreateDto;
import com.latam.unamano.dto.clientDto.response.GetClient;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ClientServiceInterface {
    Optional<GetClient> save(ClientCreateDto clientCreateDto);
    Page<GetClient> getAll(int numberPage);
    Optional<GetClient> getById(Long id);

    Optional<GetClient> update(ClientCreateDto clientCreateDto);
    Optional<GetClient> getByUserId(Long userId);
    void delete(Long id);

    GetClient getClientData(HttpServletRequest request);
}
