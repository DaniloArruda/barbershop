package com.danilo.barbershop.domain.service.exception;

import com.danilo.barbershop.domain.Barber;

public class BarberCommitedException extends Exception {
    public BarberCommitedException(Barber barber) {
        super("the barber " + barber.name + " is already commited");
    }
}
