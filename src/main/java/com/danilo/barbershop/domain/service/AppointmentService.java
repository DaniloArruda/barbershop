package com.danilo.barbershop.domain.service;

import com.danilo.barbershop.domain.Appointment;
import com.danilo.barbershop.domain.repository.AppointmentRepository;
import com.danilo.barbershop.domain.service.exception.BarberCommitedException;
import com.danilo.barbershop.domain.service.exception.ClientCommitedException;

public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void save(Appointment appointment) throws BarberCommitedException, ClientCommitedException {
        if (this.appointmentRepository.barberIsCommitedAtTime(appointment.barber, appointment.dateTime)) {
            throw new BarberCommitedException(appointment.barber);
        }

        if (this.appointmentRepository.clientIsCommitedAtTime(appointment.client, appointment.dateTime)) {
            throw new ClientCommitedException(appointment.client);
        }

        this.appointmentRepository.save(appointment);
    }
}
