package com.danilo.barbershop.adapter.repository;

import java.time.LocalDateTime;

import com.danilo.barbershop.domain.model.Appointment;
import com.danilo.barbershop.domain.model.Barber;
import com.danilo.barbershop.domain.model.Client;
import com.danilo.barbershop.domain.repository.AppointmentRepository;

import org.springframework.stereotype.Repository;

@Repository
public class AppointmentRepositoryDatabase implements AppointmentRepository {

    @Override
    public void save(Appointment appointment) {
    }

    @Override
    public boolean isBarberBusyDuringThisTime(Barber barber, LocalDateTime startAt, LocalDateTime endAt) {
        return false;
    }

    @Override
    public boolean isClientBusyDuringThisTime(Client client, LocalDateTime startAt, LocalDateTime endAt) {
        return false;
    }

}
