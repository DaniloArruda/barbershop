package com.danilo.barbershop.domain;

import java.util.UUID;

import com.danilo.barbershop.domain.value_object.Name;

public class Client {
    public final UUID id;
    public final Name name;

    public Client(Name name) throws Exception {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
