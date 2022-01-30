package com.danilo.barbershop.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.danilo.barbershop.domain.Appointment;
import com.danilo.barbershop.domain.Barber;
import com.danilo.barbershop.domain.Client;
import com.danilo.barbershop.domain.Task;
import com.danilo.barbershop.domain.repository.AppointmentRepository;
import com.danilo.barbershop.domain.service.exception.BarberCommitedException;
import com.danilo.barbershop.domain.service.exception.ClientCommitedException;
import com.danilo.barbershop.domain.value_object.Name;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AppointmentServiceTest {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentService appointmentService;

    public AppointmentServiceTest() {
        this.appointmentRepository = Mockito.mock(AppointmentRepository.class);
        this.appointmentService = new AppointmentService(appointmentRepository);
    }

    @Test
    void whenBarberIsCommitedAtThisTime_thenShouldNotSaveAppointment() throws Exception {
        var task = new Task("moicano", BigDecimal.valueOf(20), 20);
        var client = new Client(new Name("Danielle", "Oliveira"));
        var barber = new Barber(new Name("Danilo", "Arruda"));
        var dateTime = LocalDateTime.of(2022, 2, 20, 10, 30);
        var appointment = new Appointment(dateTime, client, barber, task);

        when(this.appointmentRepository.barberIsCommitedAtTime(appointment)).thenReturn(true);

        assertThrows(BarberCommitedException.class, () -> this.appointmentService.save(appointment));
    }

    @Test
    void whenBarberIsNotCommitedAtThisTime_thenShouldSaveAppointment() throws Exception {
        var task = new Task("moicano", BigDecimal.valueOf(20), 20);
        var client = new Client(new Name("Danielle", "Oliveira"));
        var barber = new Barber(new Name("Danilo", "Arruda"));
        var dateTime = LocalDateTime.of(2022, 2, 20, 10, 30);
        var appointment = new Appointment(dateTime, client, barber, task);

        when(this.appointmentRepository.barberIsCommitedAtTime(appointment)).thenReturn(false);

        assertDoesNotThrow(() -> this.appointmentService.save(appointment));
    }

    @Test
    void whenClientIsCommitedAtThisTime_thenShouldNotSaveAppointment() throws Exception {
        var task = new Task("moicano", BigDecimal.valueOf(20), 20);
        var client = new Client(new Name("Danielle", "Oliveira"));
        var barber = new Barber(new Name("Danilo", "Arruda"));
        var dateTime = LocalDateTime.of(2022, 2, 20, 10, 30);
        var appointment = new Appointment(dateTime, client, barber, task);

        when(this.appointmentRepository.barberIsCommitedAtTime(appointment)).thenReturn(false);
        when(this.appointmentRepository.clientIsCommitedAtTime(appointment)).thenReturn(true);

        assertThrows(ClientCommitedException.class, () -> this.appointmentService.save(appointment));
    }

    @Test
    void whenClientIsNotCommitedAtThisTime_thenShouldSaveAppointment() throws Exception {
        var task = new Task("moicano", BigDecimal.valueOf(20), 20);
        var client = new Client(new Name("Danielle", "Oliveira"));
        var barber = new Barber(new Name("Danilo", "Arruda"));
        var dateTime = LocalDateTime.of(2022, 2, 20, 10, 30);
        var appointment = new Appointment(dateTime, client, barber, task);

        when(this.appointmentRepository.barberIsCommitedAtTime(appointment)).thenReturn(false);
        when(this.appointmentRepository.clientIsCommitedAtTime(appointment)).thenReturn(false);

        assertDoesNotThrow(() -> this.appointmentService.save(appointment));
    }
}
