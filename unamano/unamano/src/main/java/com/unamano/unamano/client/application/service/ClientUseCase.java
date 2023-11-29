package com.unamano.unamano.client.application.service;

import com.unamano.unamano.client.application.facade.ClientUserCaseFacade;
import com.unamano.unamano.client.domain.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class ClientUseCase {

    private ClientUserCaseFacade userUserCaseFacade;

    public ClientUseCase(ClientUserCaseFacade userUserCaseFacade){
        this.userUserCaseFacade = userUserCaseFacade;
    }


    public Page<Client> getAll(int numberPage) {
        return userUserCaseFacade.getAllClient(numberPage);
    }

    public Client getById(Long id) {
        return userUserCaseFacade.getClientById(id);
    }

    public Client save(Client client) {
        return userUserCaseFacade.saveClient(client);
    }

    public Client update(Client client) {
        return userUserCaseFacade.updateClient(client);
    }
    public void delete(Long id) {
        userUserCaseFacade.deleteClient(id);
    }

}
