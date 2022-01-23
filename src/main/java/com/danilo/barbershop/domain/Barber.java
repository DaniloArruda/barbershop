package com.danilo.barbershop.domain;

import java.util.UUID;

import com.danilo.barbershop.domain.value_objects.Name;

public class Barber {
    public final UUID id;
    public final Name name;

    public Barber(Name name) throws Exception {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
