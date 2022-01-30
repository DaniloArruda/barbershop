package com.danilo.barbershop.adapter.api.model;

public class ResponseError {
    public final String code;
    public final String description;

    public ResponseError(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
