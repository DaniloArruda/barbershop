package com.danilo.barbershop.domain.repository;

import java.time.LocalDateTime;

import com.danilo.barbershop.domain.model.Appointment;
import com.danilo.barbershop.domain.model.Barber;
import com.danilo.barbershop.domain.model.Client;

public interface AppointmentRepository {
    public void save(Appointment appointment);

    public boolean isBarberBusyDuringThisTime(Barber barber, LocalDateTime startAt, LocalDateTime endAt);

    public boolean isClientBusyDuringThisTime(Client client, LocalDateTime startAt, LocalDateTime endAt);
}
