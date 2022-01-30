package com.danilo.barbershop.domain.repository;

import java.time.LocalDateTime;

import com.danilo.barbershop.domain.Appointment;
import com.danilo.barbershop.domain.Barber;
import com.danilo.barbershop.domain.Client;

public interface AppointmentRepository {
    public void save(Appointment appointment);

    public boolean isBarberBusyDuringThisTime(Barber barber, LocalDateTime startAt, LocalDateTime endAt);

    public boolean isClientBusyDuringThisTime(Client client, LocalDateTime startAt, LocalDateTime endAt);
}
