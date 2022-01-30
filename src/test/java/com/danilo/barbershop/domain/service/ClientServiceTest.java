package com.danilo.barbershop.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.danilo.barbershop.domain.Barber;
import com.danilo.barbershop.domain.Client;
import com.danilo.barbershop.domain.Task;
import com.danilo.barbershop.domain.repository.AppointmentRepository;
import com.danilo.barbershop.domain.repository.BarberRepository;
import com.danilo.barbershop.domain.repository.ClientRepository;
import com.danilo.barbershop.domain.repository.TaskRepository;
import com.danilo.barbershop.domain.service.exception.BarberBusyException;
import com.danilo.barbershop.domain.service.exception.BarberNotFoundException;
import com.danilo.barbershop.domain.service.exception.ClientBusyException;
import com.danilo.barbershop.domain.service.exception.ClientNotFoundException;
import com.danilo.barbershop.domain.service.exception.TaskNotFoundException;
import com.danilo.barbershop.domain.value_object.Name;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ClientServiceTest {
    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final BarberRepository barberRepository;
    private final TaskRepository taskRepository;

    private final ClientService clientService;

    public ClientServiceTest() {
        this.appointmentRepository = Mockito.mock(AppointmentRepository.class);
        this.clientRepository = Mockito.mock(ClientRepository.class);
        this.barberRepository = Mockito.mock(BarberRepository.class);
        this.taskRepository = Mockito.mock(TaskRepository.class);
        this.clientService = new ClientService(clientRepository, appointmentRepository, barberRepository,
                taskRepository);
    }

    @Test
    void whenBarberIsCommitedAtThisTime_thenShouldNotSaveAppointment() throws Exception {
        var taskId = UUID.randomUUID();
        var clientId = UUID.randomUUID();
        var barberId = UUID.randomUUID();

        var task = new Task("moicano", BigDecimal.valueOf(20), 20);
        var client = new Client(new Name("Danielle", "Oliveira"));
        var barber = new Barber(new Name("Danilo", "Arruda"));
        var startAt = LocalDateTime.of(2022, 2, 20, 10, 30);

        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(this.barberRepository.findById(barberId)).thenReturn(Optional.of(barber));
        when(this.taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        when(this.appointmentRepository.isBarberBusyDuringThisTime(barber, startAt,
                startAt.plus(task.durationInMinutes))).thenReturn(true);
        when(this.appointmentRepository.isClientBusyDuringThisTime(client, startAt,
                startAt.plus(task.durationInMinutes))).thenReturn(false);

        assertThrows(BarberBusyException.class,
                () -> this.clientService.scheduleTask(clientId, taskId, barberId, startAt));
    }

    @Test
    void whenBarberIsNotCommitedAtThisTime_thenShouldSaveAppointment() throws Exception {
        var taskId = UUID.randomUUID();
        var clientId = UUID.randomUUID();
        var barberId = UUID.randomUUID();

        var task = new Task("moicano", BigDecimal.valueOf(20), 20);
        var client = new Client(new Name("Danielle", "Oliveira"));
        var barber = new Barber(new Name("Danilo", "Arruda"));
        var startAt = LocalDateTime.of(2022, 2, 20, 10, 30);

        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(this.barberRepository.findById(barberId)).thenReturn(Optional.of(barber));
        when(this.taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        when(this.appointmentRepository.isBarberBusyDuringThisTime(barber, startAt,
                startAt.plus(task.durationInMinutes))).thenReturn(false);
        when(this.appointmentRepository.isClientBusyDuringThisTime(client, startAt,
                startAt.plus(task.durationInMinutes))).thenReturn(false);

        assertDoesNotThrow(() -> {
            this.clientService.scheduleTask(clientId, taskId, barberId, startAt);
            verify(this.appointmentRepository, times(1)).save(any());
        });
    }

    @Test
    void whenClientIsCommitedAtThisTime_thenShouldNotSaveAppointment() throws Exception {
        var taskId = UUID.randomUUID();
        var clientId = UUID.randomUUID();
        var barberId = UUID.randomUUID();

        var task = new Task("moicano", BigDecimal.valueOf(20), 20);
        var client = new Client(new Name("Danielle", "Oliveira"));
        var barber = new Barber(new Name("Danilo", "Arruda"));
        var startAt = LocalDateTime.of(2022, 2, 20, 10, 30);

        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(this.barberRepository.findById(barberId)).thenReturn(Optional.of(barber));
        when(this.taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        when(this.appointmentRepository.isBarberBusyDuringThisTime(barber, startAt,
                startAt.plus(task.durationInMinutes))).thenReturn(false);
        when(this.appointmentRepository.isClientBusyDuringThisTime(client, startAt,
                startAt.plus(task.durationInMinutes))).thenReturn(true);

        assertThrows(ClientBusyException.class,
                () -> this.clientService.scheduleTask(clientId, taskId, barberId, startAt));
    }

    @Test
    void whenClientIsNotCommitedAtThisTime_thenShouldSaveAppointment() throws Exception {
        var taskId = UUID.randomUUID();
        var clientId = UUID.randomUUID();
        var barberId = UUID.randomUUID();

        var task = new Task("moicano", BigDecimal.valueOf(20), 20);
        var client = new Client(new Name("Danielle", "Oliveira"));
        var barber = new Barber(new Name("Danilo", "Arruda"));
        var startAt = LocalDateTime.of(2022, 2, 20, 10, 30);

        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(this.barberRepository.findById(barberId)).thenReturn(Optional.of(barber));
        when(this.taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        when(this.appointmentRepository.isBarberBusyDuringThisTime(barber, startAt,
                startAt.plus(task.durationInMinutes))).thenReturn(false);
        when(this.appointmentRepository.isClientBusyDuringThisTime(client, startAt,
                startAt.plus(task.durationInMinutes))).thenReturn(false);

        assertDoesNotThrow(() -> {
            this.clientService.scheduleTask(clientId, taskId, barberId, startAt);
            verify(this.appointmentRepository, times(1)).save(any());
        });
    }

    @Test
    void whenClientIdDoesNotExist_thenThrowNotFoundExceptionOnSchedule() throws Exception {
        var taskId = UUID.randomUUID();
        var clientId = UUID.randomUUID();
        var barberId = UUID.randomUUID();

        var task = new Task("moicano", BigDecimal.valueOf(20), 20);
        var barber = new Barber(new Name("Danilo", "Arruda"));
        var startAt = LocalDateTime.of(2022, 2, 20, 10, 30);

        when(this.clientRepository.findById(clientId)).thenReturn(Optional.empty());
        when(this.barberRepository.findById(barberId)).thenReturn(Optional.of(barber));
        when(this.taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        assertThrows(ClientNotFoundException.class,
                () -> this.clientService.scheduleTask(clientId, taskId, barberId, startAt));
    }

    @Test
    void whenTaskIdDoesNotExist_thenThrowNotFoundExceptionOnSchedule() throws Exception {
        var taskId = UUID.randomUUID();
        var clientId = UUID.randomUUID();
        var barberId = UUID.randomUUID();

        var client = new Client(new Name("Danielle", "Oliveira"));
        var barber = new Barber(new Name("Danilo", "Arruda"));
        var startAt = LocalDateTime.of(2022, 2, 20, 10, 30);

        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(this.barberRepository.findById(barberId)).thenReturn(Optional.of(barber));
        when(this.taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class,
                () -> this.clientService.scheduleTask(clientId, taskId, barberId, startAt));
    }

    @Test
    void whenBarberIdDoesNotExist_thenThrowNotFoundExceptionOnSchedule() throws Exception {
        var taskId = UUID.randomUUID();
        var clientId = UUID.randomUUID();
        var barberId = UUID.randomUUID();

        var task = new Task("moicano", BigDecimal.valueOf(20), 20);
        var client = new Client(new Name("Danielle", "Oliveira"));
        var startAt = LocalDateTime.of(2022, 2, 20, 10, 30);

        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(this.barberRepository.findById(barberId)).thenReturn(Optional.empty());
        when(this.taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        assertThrows(BarberNotFoundException.class,
                () -> this.clientService.scheduleTask(clientId, taskId, barberId, startAt));
    }
}
