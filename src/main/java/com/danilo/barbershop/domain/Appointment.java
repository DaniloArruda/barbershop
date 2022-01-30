package com.danilo.barbershop.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {
    public final UUID id;
    public final LocalDateTime dateTime;
    public final Client client;
    public final Barber barber;
    public final Task task;

    public Appointment(LocalDateTime dateTime, Client client, Barber barber, Task task) {
        this.id = UUID.randomUUID();
        this.dateTime = dateTime;
        this.client = client;
        this.barber = barber;
        this.task = task;
    }
}
