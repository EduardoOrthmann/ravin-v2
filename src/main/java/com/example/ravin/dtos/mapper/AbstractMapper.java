package com.example.ravin.dtos.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public abstract class AbstractMapper <Entity, Request, Response> {
    protected final ModelMapper mapper;

    public Entity toEntity(Request request) {
        return mapper.map(request, getEntityClass());
    }

    public Response toResponse(Entity entity) {
        return mapper.map(entity, getResponseClass());
    }

    public Request toRequest(Entity entity) {
        return mapper.map(entity, getRequestClass());
    }

    public Entity updateEntity(Entity entity, Request request) {
        mapper.map(request, entity);
        return entity;
    }

    abstract Class<Entity> getEntityClass();
    abstract Class<Request> getRequestClass();
    abstract Class<Response> getResponseClass();
}
