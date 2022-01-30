package com.danilo.barbershop.port.usecase.request;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public class ClientSchedulingRequest {
    @NotNull
    public final UUID clientId;

    @NotNull
    public final UUID taskId;

    @NotNull
    public final UUID barberId;

    @NotNull
    public final LocalDateTime startAt;

    public ClientSchedulingRequest(UUID clientId, UUID taskId, UUID barberId, LocalDateTime startAt) {
        this.clientId = clientId;
        this.taskId = taskId;
        this.barberId = barberId;
        this.startAt = startAt;
    }
}
