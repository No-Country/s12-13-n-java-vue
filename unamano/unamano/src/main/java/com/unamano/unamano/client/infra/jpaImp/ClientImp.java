package com.unamano.unamano.client.infra.jpaImp;

import com.unamano.unamano.client.application.facade.ClientUserCaseFacade;
import com.unamano.unamano.client.domain.model.Client;
import com.unamano.unamano.client.infra.entity.ClientEntity;
import com.unamano.unamano.client.infra.jpaRepository.ClientRepository;
import com.unamano.unamano.client.infra.mapper.ClientMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientImp implements ClientUserCaseFacade {
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    public ClientImp(ClientRepository clientRepository, ClientMapper clientMapper){
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }



    @Override
    public Page<Client> getAllClient(int numberPage) {
        int pageSize = 10;
        PageRequest page = PageRequest.of(numberPage, pageSize);
        Page<ClientEntity> users = clientRepository.findAll(page);
        return users.map(clientMapper::clientEntityToClient);
    }

    @Override
    public Client getClientById(Long id) {
        Optional<ClientEntity> clientEntityOptional = clientRepository.findById(id);
        if (clientEntityOptional.isPresent()){
           ClientEntity clientEntity = clientEntityOptional.get();
           return clientMapper.clientEntityToClient(clientEntity);
        }
        return null;
    }

    @Override
    public Client saveClient(Client client) {
       return clientMapper.clientEntityToClient(
               clientRepository
                       .save(clientMapper.clientToClientEntity(client)));
    }

    @Override
    public Client updateClient(Client client) {
        Optional<ClientEntity> clientEntityOptional = clientRepository.findById(client.getId());
        if(clientEntityOptional.isPresent()){
            ClientEntity clientEntity = clientRepository.save(clientEntityOptional.get());
            return clientMapper.clientEntityToClient(clientEntity);
        }
        return null;
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
