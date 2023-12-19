package com.latam.unamano.dto.clientDto.response;

import com.latam.unamano.persistence.entities.client.Client;
import com.latam.unamano.utils.Role;

public record GetClient(Long id_client,Long id_user, String username, String firstName,
                        String lastName,String email,Role rol,Long id_address,
                        String city, String country) {

    public GetClient(Client client){
        this(client.getId(),
                client.getUser().getId(),
                client.getUser().getUsername(),
                client.getUser().getFirstName(),
                client.getUser().getLastName(),
                client.getUser().getEmail(),
                client.getUser().getRole(),
                client.getUser().getAddresses().getId(),
                client.getUser().getAddresses().getCity(),
                client.getUser().getAddresses().getCountry());
    }
}