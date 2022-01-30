package com.danilo.barbershop.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.danilo.barbershop.domain.Task;

public interface TaskRepository {
    public Optional<Task> findById(UUID id);
}
