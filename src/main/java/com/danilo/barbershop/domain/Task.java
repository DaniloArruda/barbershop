package com.danilo.barbershop.domain;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

public class Task {
    public final UUID id;
    public final String description;
    public final BigDecimal price;
    public final Duration durationInMinutes;

    public Task(String description, BigDecimal price, Duration durationInMinutes) throws Exception {
        if (description == null || description.isEmpty())
            throw new Exception();

        if (price.compareTo(BigDecimal.ZERO) <= 0)
            throw new Exception();

        if (durationInMinutes.isNegative())
            throw new Exception();

        this.id = UUID.randomUUID();
        this.description = description;
        this.price = price;
        this.durationInMinutes = durationInMinutes;
    }

    public Task(String description, BigDecimal price, long durationInMinutes) throws Exception {
        this(description, price, Duration.ofMinutes(durationInMinutes));
    }
}
