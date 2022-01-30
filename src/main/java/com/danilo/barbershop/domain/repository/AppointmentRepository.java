package com.danilo.barbershop.domain.repository;

import com.danilo.barbershop.domain.Appointment;

public interface AppointmentRepository {
    public void save(Appointment appointment);

    public boolean barberIsCommitedAtTime(Appointment appointment);

    public boolean clientIsCommitedAtTime(Appointment appointment);
}
