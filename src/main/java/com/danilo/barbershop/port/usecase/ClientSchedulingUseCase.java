package com.danilo.barbershop.port.usecase;

import com.danilo.barbershop.domain.service.ClientService;
import com.danilo.barbershop.port.mailer.Mailer;
import com.danilo.barbershop.port.usecase.request.ClientSchedulingRequest;

import org.springframework.stereotype.Service;

@Service
public class ClientSchedulingUseCase implements UseCase<ClientSchedulingRequest, Void> {
    private final ClientService clientService;
    private final Mailer mailer;

    public ClientSchedulingUseCase(ClientService clientService, Mailer mailer) {
        this.clientService = clientService;
        this.mailer = mailer;
    }

    @Override
    public Void perform(ClientSchedulingRequest request) throws Exception {
        this.clientService.scheduleTask(request.clientId, request.taskId, request.barberId, request.startAt);

        this.mailer.send("Appointment created!");

        return null;
    }

}
