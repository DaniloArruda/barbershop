package com.danilo.barbershop.adapter.repository;

import java.util.Optional;
import java.util.UUID;

import com.danilo.barbershop.domain.model.Client;
import com.danilo.barbershop.domain.repository.ClientRepository;
import com.danilo.barbershop.domain.value_object.Name;

import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryDatabase implements ClientRepository {

    @Override
    public Optional<Client> findById(UUID id) throws Exception {
        return Optional.of(new Client(new Name("Danielle", "Oliveira")));
    }

}
