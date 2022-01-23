package com.danilo.barbershop.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void shouldThrowExceptionWhenDescriptionIsNullOrEmpty() {
        assertThrows(Exception.class, () -> new Task("", BigDecimal.valueOf(20), 20L));
    }

    @Test
    void shouldThrowExceptionWhenPriceIsLessOrEqualToZero() {
        assertThrows(Exception.class, () -> new Task("Corte moicano", BigDecimal.valueOf(0), 20L));
    }

    @Test
    void shouldThrowExceptionWhenDurationIsLessThanZero() {
        assertThrows(Exception.class, () -> new Task("Corte moicano", BigDecimal.valueOf(20), -20L));
    }
}
