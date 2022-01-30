package com.danilo.barbershop.domain.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.danilo.barbershop.domain.repository.AppointmentRepository;
import com.danilo.barbershop.domain.repository.BarberRepository;
import com.danilo.barbershop.domain.repository.ClientRepository;
import com.danilo.barbershop.domain.repository.TaskRepository;
import com.danilo.barbershop.domain.service.exception.BarberBusyException;
import com.danilo.barbershop.domain.service.exception.BarberNotFoundException;
import com.danilo.barbershop.domain.service.exception.ClientBusyException;
import com.danilo.barbershop.domain.service.exception.ClientNotFoundException;
import com.danilo.barbershop.domain.service.exception.TaskNotFoundException;

public class ClientService {
    private final ClientRepository clientRepository;
    private final AppointmentRepository appointmentRepository;
    private final BarberRepository barberRepository;
    private final TaskRepository taskRepository;

    public ClientService(ClientRepository clientRepository, AppointmentRepository appointmentRepository,
            BarberRepository barberRepository, TaskRepository taskRepository) {
        this.clientRepository = clientRepository;
        this.appointmentRepository = appointmentRepository;
        this.barberRepository = barberRepository;
        this.taskRepository = taskRepository;
    }

    public void scheduleTask(UUID clientId, UUID taskId, UUID barberId, LocalDateTime startAt) throws Exception {
        var client = this.clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        var barber = this.barberRepository.findById(barberId).orElseThrow(() -> new BarberNotFoundException(barberId));
        var task = this.taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));

        var endAt = startAt.plus(task.durationInMinutes);

        if (this.appointmentRepository.isBarberBusyDuringThisTime(barber, startAt, endAt)) {
            throw new BarberBusyException(barber, startAt, endAt);
        }

        if (this.appointmentRepository.isClientBusyDuringThisTime(client, startAt, endAt)) {
            throw new ClientBusyException(client, startAt, endAt);
        }

        var appointment = client.schedule(task).with(barber).startingAt(startAt).build();

        this.appointmentRepository.save(appointment);
    }
}
