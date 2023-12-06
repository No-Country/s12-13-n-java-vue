package com.latam.unamano.controller.rating;

import com.latam.unamano.dto.rating.RatingAverage;
import com.latam.unamano.dto.rating.RatingMessage;
import com.latam.unamano.dto.rating.RatingRequestDto;
import com.latam.unamano.service.rating.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rating")
@AllArgsConstructor
public class ratingController {
    RatingService ratingService;

    @GetMapping(value="/averageWorker/{id}")
    ResponseEntity<RatingAverage>workerAverage(@RequestParam Long workerID){
        return ResponseEntity.ok(ratingService.getAverageWorker(workerID));
    }
    @GetMapping(value="/averageClient/{id}")
    ResponseEntity<RatingAverage>clientAverage(@RequestParam Long clientID){
        return ResponseEntity.ok(ratingService.getAverageClient(clientID));
    }

    @PostMapping(value = "/worker")
    ResponseEntity<RatingMessage> createWorkerRating(@RequestBody RatingRequestDto ratingRequestDto){
        return ResponseEntity.ok(ratingService.createWorkerRating(ratingRequestDto));
    }

    @PostMapping(value = "/client")
    ResponseEntity<RatingMessage> createClientRating(@RequestBody RatingRequestDto ratingRequestDto){
        return ResponseEntity.ok(ratingService.createClienteRating(ratingRequestDto));
    }


}
