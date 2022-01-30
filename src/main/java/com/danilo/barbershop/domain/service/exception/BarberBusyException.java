package com.danilo.barbershop.domain.service.exception;

import java.time.LocalDateTime;

import com.danilo.barbershop.domain.model.Barber;

public class BarberBusyException extends Exception {
    public BarberBusyException(Barber barber, LocalDateTime startAt, LocalDateTime endAt) {
        super("the barber " + barber.name + " is already busy at interval between: " + startAt + " and " + endAt);
    }
}
