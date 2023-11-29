package com.unamano.unamano.client.infra.mapper;

import com.unamano.unamano.client.domain.model.Client;
import com.unamano.unamano.client.infra.entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client clientEntityToClient(ClientEntity clientEntity){
        return new Client.ClientBuilder()
                .id(clientEntity.getId())
                .username(clientEntity.getUsername())
                .lastname(clientEntity.getLastname())
                .build();
    }
    public ClientEntity clientToClientEntity(Client client){
        return new ClientEntity.ClientEntityBuilder()
                .id(client.getId())
                .username(client.getUsername())
                .lastname(client.getLastname())
                .build();
    }
}
