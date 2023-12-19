package com.latam.unamano.service.clientService;

import com.latam.unamano.dto.clientDto.request.ClientCreateDto;
import com.latam.unamano.dto.clientDto.response.GetClient;
import com.latam.unamano.persistence.entities.client.Client;
import com.latam.unamano.persistence.entities.workerEntity.Worker;
import com.latam.unamano.persistence.repositories.clientRepository.ClientRepository;
import com.latam.unamano.persistence.repositories.user.UserRepository;
import com.latam.unamano.service.encript.Encryptor;
import com.latam.unamano.service.jwt.JwtService;
import com.latam.unamano.utils.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientService implements ClientServiceInterface{

    private ClientRepository clientRepository;
    private UserRepository userRepository;
    private Encryptor encryptorPassword;
    private final JwtService jwtService;

    public ClientService(ClientRepository clientRepository,UserRepository userRepository
            , Encryptor encryptor, JwtService jwtService){
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.encryptorPassword = encryptor;
        this.jwtService=jwtService;
    }
    @Override
    public Optional<GetClient> save(ClientCreateDto clientCreateDto) {
        clientCreateDto.user().setRole(Role.ROLE_CLIENT);
        //Method to encrypt a given password
        clientCreateDto.user().setPassword(encryptorPassword
                .encrypt(clientCreateDto.user().getPassword()));
        Client client = new Client();
            client.setUser(clientCreateDto.user());
            userRepository.save(clientCreateDto.user());
        return Optional.of(new GetClient(clientRepository.save(client)));
    }

    @Override
    public Page<GetClient> getAll(int numberPage) {
        int pageSize = 10;
        PageRequest page = PageRequest.of(numberPage,pageSize);
        Page<Client> clients = clientRepository.findAll(page);
        return clients.map(client -> new GetClient(client));
    }

    @Override
    public Optional<GetClient> getById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()){
            return Optional.of(new GetClient(clientOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<GetClient> update(ClientCreateDto clientCreateDto) {
        clientCreateDto.user().setRole(Role.ROLE_CLIENT);
        //Method to encrypt a given password
        clientCreateDto.user().setPassword(encryptorPassword
                .encrypt(clientCreateDto.user().getPassword()));

        Optional<Client> clientOptional = clientRepository.findById(clientCreateDto.id());
        if (clientOptional.isPresent()){

            Client client = clientOptional.get();
            client.setUser(clientCreateDto.user());

            userRepository.save(client.getUser());
            return Optional.of( new GetClient(clientRepository.save(client)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<GetClient> getByUserId(Long userId) {
        Optional<Client> clientOptional = clientRepository.findByUserId(userId);
        return clientOptional.map(GetClient::new);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public GetClient getClientData(HttpServletRequest request) {
        return new GetClient(getClientByJWT(request));
    }
    private Client getClientByJWT(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = header.substring(7);
        String username = jwtService.getUsernameFromToken(token);
        return clientRepository.findByUserUsername(username);
    }
}
