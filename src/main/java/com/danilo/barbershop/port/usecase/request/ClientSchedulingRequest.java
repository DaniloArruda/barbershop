package com.danilo.barbershop.port.usecase.request;

import java.time.LocalDateTime;
import java.util.UUID;

public class ClientSchedulingRequest {
    public final UUID clientId;
    public final UUID taskId;
    public final UUID barberId;
    public final LocalDateTime startAt;

    public ClientSchedulingRequest(UUID clientId, UUID taskId, UUID barberId, LocalDateTime startAt) {
        this.clientId = clientId;
        this.taskId = taskId;
        this.barberId = barberId;
        this.startAt = startAt;
    }
}
