package com.latam.unamano.service.rating;

import com.latam.unamano.dto.rating.RatingAverage;
import com.latam.unamano.dto.rating.RatingMessage;
import com.latam.unamano.dto.rating.RatingRequestDto;
import com.latam.unamano.exceptions.IncorretRoleException;
import com.latam.unamano.exceptions.RatingNotFoundException;
import com.latam.unamano.exceptions.UserNotFoundException;
import com.latam.unamano.persistence.entities.rating.Rating;
import com.latam.unamano.persistence.entities.user.User;
import com.latam.unamano.persistence.repositories.rating.RatingRepository;
import com.latam.unamano.persistence.repositories.user.UserRepository;
import com.latam.unamano.utils.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class RatingService {

    RatingRepository ratingRepository;

    UserRepository userRepository;



    public RatingAverage getAverageWorker(Long workerID) {
        User user = validateWorkerId(workerID);
        List<Rating> listaRating =checkExistRating(user);
        Integer average = calculatingAverage(listaRating);
        return (new RatingAverage(average));
    }



    public RatingAverage getAverageClient(Long clientID) {
        User user = validateClientId(clientID);
        List<Rating> listaRating =checkExistRating(user);
        Integer average = calculatingAverage(listaRating);
        return (new RatingAverage(average));

    }

    public RatingMessage createWorkerRating(RatingRequestDto ratingRequestDto) {
        User user = validateWorkerId(ratingRequestDto.UserId());

        Integer value = validateRating(ratingRequestDto.rating());

        savingUser(user, value);

        return new RatingMessage("Saved Worker Rating !!!");
    }



    public RatingMessage createClienteRating(RatingRequestDto ratingRequestDto) {
        User user = validateClientId(ratingRequestDto.UserId());

        Integer value = validateRating(ratingRequestDto.rating());

        savingUser(user, value);

        return new RatingMessage("Saved Client Rating !!!");
    }



    private User validateWorkerId(Long userId) {
        var worker = userRepository.findById(userId);
        if(worker.isPresent()){
            User user = worker.get();
            if(!(user.getRole().equals(Role.ROLE_WORKER))){
                throw new RuntimeException(new IncorretRoleException("El Usuario no es un Worker"));
            }else{
                return user;
            }
        }else{
            throw new RuntimeException(new UserNotFoundException("Usuario No existe"));
        }
    }

    private User validateClientId(Long userId) {
        var client = userRepository.findById(userId);
        if(client.isPresent()){
            User user = client.get();
            if(!(user.getRole().equals(Role.ROLE_CLIENT))){
                throw new RuntimeException(new IncorretRoleException("El Usuario no es un Client"));
            }else{
                return user;
            }
        }else{
            throw new RuntimeException(new UserNotFoundException("Usuario No existe"));
        }
    }

    private Integer validateRating(Integer rating) {
        if(rating>5){
            return 5;
        }
        if (rating < 1){
            return 1;
        }
        return rating;
    }

    private void savingUser(User user, Integer value) {
        Rating rating = Rating.builder()
                .rating(value)
                .user(user)
                .build();
        ratingRepository.save(rating);
    }

    private Integer calculatingAverage(List<Rating> listaRating) {
        var x = listaRating.stream().mapToInt(Rating::getRating).average().orElse(0);

        return Double.valueOf(Math.round(x)).intValue();
    }

    private List<Rating> checkExistRating(User user) {
        var lista = ratingRepository.findAllByUser(user);
        if (lista.isEmpty()){
            throw new RuntimeException(new RatingNotFoundException("Usuario sin calificaiones"));
        }else{
            return lista;
        }
    }



}
