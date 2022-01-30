package com.danilo.barbershop.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.danilo.barbershop.domain.Barber;

public interface BarberRepository {
    public Optional<Barber> findById(UUID id);
}
