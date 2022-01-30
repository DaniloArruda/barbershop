package com.danilo.barbershop.adapter.repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import com.danilo.barbershop.domain.model.Task;
import com.danilo.barbershop.domain.repository.TaskRepository;

import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryDatabase implements TaskRepository {

    @Override
    public Optional<Task> findById(UUID id) throws Exception {
        return Optional.of(new Task("Moicano", BigDecimal.valueOf(20), 20));
    }

}
