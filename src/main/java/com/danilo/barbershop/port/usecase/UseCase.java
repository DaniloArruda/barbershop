package com.danilo.barbershop.port.usecase;

public interface UseCase<TRequest, TResponse> {
    public TResponse perform(TRequest request) throws Exception;
}
