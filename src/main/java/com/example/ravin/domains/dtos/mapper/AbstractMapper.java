package com.example.ravin.domains.dtos.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public abstract class AbstractMapper <T, U, V> {
    protected final ModelMapper mapper;

    public T toEntity(U u) {
        return mapper.map(u, getEntityClass());
    }

    public V toResponse(T t) {
        return mapper.map(t, getResponseClass());
    }

    public U toRequest(T t) {
        return mapper.map(t, getRequestClass());
    }

    public T updateEntity(T t, U u) {
        mapper.map(u, t);
        return t;
    }

    abstract Class<T> getEntityClass();
    abstract Class<U> getRequestClass();
    abstract Class<V> getResponseClass();
}
