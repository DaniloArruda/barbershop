package com.danilo.barbershop.adapter.api.controller;

import javax.validation.Valid;

import com.danilo.barbershop.adapter.api.model.ResponseError;
import com.danilo.barbershop.domain.service.exception.BarberNotFoundException;
import com.danilo.barbershop.domain.service.exception.ClientNotFoundException;
import com.danilo.barbershop.domain.service.exception.TaskNotFoundException;
import com.danilo.barbershop.port.usecase.ClientSchedulingUseCase;
import com.danilo.barbershop.port.usecase.request.ClientSchedulingRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController()
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientSchedulingUseCase clientSchedulingUseCase;

    @PostMapping("/schedule")
    public ResponseEntity<Object> postMethodName(@Valid @RequestBody ClientSchedulingRequest request) {
        try {
            this.clientSchedulingUseCase.perform(request);
        } catch (ClientNotFoundException | BarberNotFoundException | TaskNotFoundException exception) {
            return ResponseEntity.unprocessableEntity()
                    .body(new ResponseError("UNPROCESSABLE_ENTITY", exception.getMessage()));
        } catch (Exception exception) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseError("UNKNOWN_ERROR", exception.getMessage()));
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
