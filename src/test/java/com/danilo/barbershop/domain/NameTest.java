package com.danilo.barbershop.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.danilo.barbershop.domain.value_objects.Name;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(Exception.class, () -> new Name(null, "Arruda"));
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        assertThrows(Exception.class, () -> new Name("", "Arruda"));
    }
}
