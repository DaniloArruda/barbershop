package com.danilo.barbershop.domain;

import java.util.UUID;

public class Client {
    public final UUID id;
    public final String name;

    public Client(String name) throws Exception {
        if (name.length() < 3)
            throw new Exception();

        this.id = UUID.randomUUID();
        this.name = name;
    }
}
