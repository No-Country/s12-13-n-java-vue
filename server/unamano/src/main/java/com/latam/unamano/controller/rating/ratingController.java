package com.latam.unamano.controller.rating;

import com.latam.unamano.dto.rating.RatingAverage;
import com.latam.unamano.dto.rating.RatingMessage;
import com.latam.unamano.dto.rating.RatingRequestDto;
import com.latam.unamano.service.rating.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rating")
@AllArgsConstructor
public class ratingController {
    RatingService ratingService;

    @GetMapping(value="/averageWorker/{id}")
    @Secured("ROLE_CLIENT")
    @Operation(summary = "Esta operación devuelve la calificación promedio de un trabajador" +
            " específico identificado por su workerID. La calificación se calcula a partir de" +
            " las evaluaciones recibidas por el trabajador y se presenta en el cuerpo de la" +
            " respuesta.")
    ResponseEntity<RatingAverage>workerAverage(@RequestParam Long workerID){

        return ResponseEntity.ok(ratingService.getAverageWorker(workerID));
    }
    @GetMapping(value="/averageClient/{id}")
    @Secured("ROLE_WORKER")
    @Operation(summary = "Recupera la calificación promedio de un cliente particular" +
            " identificado por su clientID. El cálculo se basa en las calificaciones" +
            " proporcionadas por el cliente y se devuelve como parte de la respuesta")
    ResponseEntity<RatingAverage>clientAverage(@RequestParam Long clientID){
        return ResponseEntity.ok(ratingService.getAverageClient(clientID));
    }

    @PostMapping(value = "/worker")
    @Secured("ROLE_CLIENT")
    @Operation(summary = "Esta operación registra una nueva calificación para el " +
            "trabajador indicado en el cuerpo de la solicitud (ratingRequestDto). " +
            "Antes de guardar la calificación, se valida la existencia del trabajador, " +
            "la autenticidad del ID de usuario y la validez de la calificación proporcionada.")
    ResponseEntity<RatingMessage> createWorkerRating(@RequestBody RatingRequestDto ratingRequestDto){
        return ResponseEntity.ok(ratingService.createWorkerRating(ratingRequestDto));
    }

    @PostMapping(value = "/client")
    @Secured("ROLE_WORKER")
    @Operation(summary = "Registra una nueva calificación para el cliente mencionado" +
            " en el cuerpo de la solicitud (ratingRequestDto). Antes de guardar la calificación," +
            " se verifica la existencia del cliente, se valida el ID de usuario y se" +
            " verifica la validez de la calificación proporcionada.")
    ResponseEntity<RatingMessage> createClientRating(@RequestBody RatingRequestDto ratingRequestDto){
        return ResponseEntity.ok(ratingService.createClienteRating(ratingRequestDto));
    }


}
