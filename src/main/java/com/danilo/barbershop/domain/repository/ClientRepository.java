package com.danilo.barbershop.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.danilo.barbershop.domain.model.Client;

public interface ClientRepository {
    public Optional<Client> findById(UUID id) throws Exception;
}
