package com.unamano.unamano.client.application.facade;

import com.unamano.unamano.client.domain.model.Client;
import org.springframework.data.domain.Page;

public interface ClientUserCaseFacade {

    Page<Client> getAllClient(int numberPage);
    Client getClientById(Long id);
    Client saveClient(Client client);
    Client updateClient(Client client);
    void deleteClient(Long id);

}
