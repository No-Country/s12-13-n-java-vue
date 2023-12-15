package com.latam.unamano.service.userService;

import com.latam.unamano.dto.clientDto.response.GetClient;
import com.latam.unamano.dto.workerDto.reponse.GetWorker;
import com.latam.unamano.persistence.entities.user.User;
import com.latam.unamano.persistence.repositories.clientRepository.ClientRepository;
import com.latam.unamano.persistence.repositories.user.UserRepository;
import com.latam.unamano.persistence.repositories.workerRepository.WorkerRepository;
import com.latam.unamano.service.jwt.JwtService;
import com.latam.unamano.utils.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private  UserRepository userRepository;
    private  JwtService jwtService;
    private ClientRepository clientRepository;
    private WorkerRepository workerRepository;
    public UserService(UserRepository userRepository, JwtService jwtService,
                       ClientRepository clientRepository, WorkerRepository workerRepository){
        this.userRepository=userRepository;
        this.jwtService=jwtService;
        this.clientRepository=clientRepository;
        this.workerRepository=workerRepository;
    }




    public Object getUserDataByJWT(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = header.substring(7);
        String username = jwtService.getUsernameFromToken(token);
        Role role = userRepository.findRoleByUsername(username);
        if(role.equals(role.ROLE_CLIENT)){
            return new GetClient(clientRepository.findByUserUsername(username));
        }
        return new GetWorker(workerRepository.findByUserUsername(username));
    }
}
