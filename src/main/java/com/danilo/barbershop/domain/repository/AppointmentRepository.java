package com.danilo.barbershop.domain.repository;

import java.time.LocalDateTime;

import com.danilo.barbershop.domain.Appointment;
import com.danilo.barbershop.domain.Barber;
import com.danilo.barbershop.domain.Client;

public interface AppointmentRepository {
    public void save(Appointment appointment);

    public boolean barberIsCommitedAtTime(Barber barber, LocalDateTime dateTime);

    public boolean clientIsCommitedAtTime(Client client, LocalDateTime dateTime);
}
