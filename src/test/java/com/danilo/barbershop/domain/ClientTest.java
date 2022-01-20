package com.danilo.barbershop.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    void shouldThrowExceptionWhenTryCreateAClientWithLittleName() {
        assertThrows(Exception.class, () -> new Client("la"));
    }
}
