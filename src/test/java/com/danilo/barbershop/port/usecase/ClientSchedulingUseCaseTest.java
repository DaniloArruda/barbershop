package com.danilo.barbershop.port.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.danilo.barbershop.domain.model.Barber;
import com.danilo.barbershop.domain.model.Client;
import com.danilo.barbershop.domain.model.Task;
import com.danilo.barbershop.domain.repository.AppointmentRepository;
import com.danilo.barbershop.domain.repository.BarberRepository;
import com.danilo.barbershop.domain.repository.ClientRepository;
import com.danilo.barbershop.domain.repository.TaskRepository;
import com.danilo.barbershop.domain.service.ClientService;
import com.danilo.barbershop.domain.value_object.Name;
import com.danilo.barbershop.port.mailer.Mailer;
import com.danilo.barbershop.port.usecase.request.ClientSchedulingRequest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ClientSchedulingUseCaseTest {
    private final ClientSchedulingUseCase clientSchedulingUseCase;

    private final ClientRepository clientRepository;
    private final AppointmentRepository appointmentRepository;
    private final BarberRepository barberRepository;
    private final TaskRepository taskRepository;
    private final ClientService clientService;
    private final Mailer mailer;

    public ClientSchedulingUseCaseTest() {
        this.clientRepository = Mockito.mock(ClientRepository.class);
        this.appointmentRepository = Mockito.mock(AppointmentRepository.class);
        this.barberRepository = Mockito.mock(BarberRepository.class);
        this.taskRepository = Mockito.mock(TaskRepository.class);
        this.clientService = new ClientService(clientRepository, appointmentRepository, barberRepository,
                taskRepository);

        this.mailer = Mockito.mock(Mailer.class);
        this.clientSchedulingUseCase = new ClientSchedulingUseCase(this.clientService, this.mailer);
    }

    @Test
    void whenClientScheduleATask_thenAnEmailIsSendedToTheClient() throws Exception {
        var client = new Client(new Name("Danilo", "Arruda"));
        var barber = new Barber(new Name("Gato", "Sauro"));
        var task = new Task("moicano", BigDecimal.valueOf(20), 20);

        when(this.clientRepository.findById(any())).thenReturn(Optional.of(client));
        when(this.barberRepository.findById(any())).thenReturn(Optional.of(barber));
        when(this.taskRepository.findById(any())).thenReturn(Optional.of(task));

        var request = new ClientSchedulingRequest(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
                LocalDateTime.now());

        this.clientSchedulingUseCase.perform(request);

        verify(this.mailer, times(1)).send(any());
    }
}
