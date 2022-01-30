package com.danilo.barbershop.domain.service.exception;

import java.time.LocalDateTime;

import com.danilo.barbershop.domain.Client;

public class ClientBusyException extends Exception {
    public ClientBusyException(Client client, LocalDateTime startAt, LocalDateTime endAt) {
        super("the client " + client.name + " is already busy at interval between: " + startAt + " and " + endAt);
    }
}
