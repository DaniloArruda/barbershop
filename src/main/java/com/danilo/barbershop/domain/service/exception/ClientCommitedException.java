package com.danilo.barbershop.domain.service.exception;

import com.danilo.barbershop.domain.Client;

public class ClientCommitedException extends Exception {
    public ClientCommitedException(Client client) {
        super("the client " + client.name + " is already commited");
    }
}
