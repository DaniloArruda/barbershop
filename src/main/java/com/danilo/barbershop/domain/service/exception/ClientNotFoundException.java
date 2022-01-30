package com.danilo.barbershop.domain.service.exception;

import java.util.UUID;

public class ClientNotFoundException extends Exception {
    public ClientNotFoundException(UUID clientId) {
        super("client with id " + clientId + " not found");
    }
}
