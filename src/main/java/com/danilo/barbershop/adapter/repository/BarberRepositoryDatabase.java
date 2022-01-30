package com.danilo.barbershop.adapter.repository;

import java.util.Optional;
import java.util.UUID;

import com.danilo.barbershop.domain.model.Barber;
import com.danilo.barbershop.domain.repository.BarberRepository;
import com.danilo.barbershop.domain.value_object.Name;

import org.springframework.stereotype.Repository;

@Repository
public class BarberRepositoryDatabase implements BarberRepository {

    @Override
    public Optional<Barber> findById(UUID id) throws Exception {
        return Optional.of(new Barber(new Name("Gato", "Sauro")));
    }

}
