package com.danilo.barbershop.domain.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {
    public final UUID id;
    public final LocalDateTime startAt;
    public final LocalDateTime endAt;
    public final Duration durationInMinutes;
    public final Client client;
    public final Barber barber;
    public final Task task;

    public Appointment(LocalDateTime startAt, Client client, Barber barber, Task task) {
        this.id = UUID.randomUUID();
        this.startAt = startAt;
        this.client = client;
        this.barber = barber;
        this.task = task;

        this.durationInMinutes = this.task.durationInMinutes;
        this.endAt = this.startAt.plus(this.durationInMinutes);
    }
}
