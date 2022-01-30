package com.danilo.barbershop.domain.model;

import java.util.UUID;

import com.danilo.barbershop.domain.value_object.Name;

public class Barber {
    public final UUID id;
    public final Name name;

    public Barber(Name name) throws Exception {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
