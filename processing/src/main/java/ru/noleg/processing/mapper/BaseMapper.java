package ru.noleg.processing.mapper;

import java.util.List;

public interface BaseMapper<E, D> {
    E mapToEntity(D dto);

    D mapToDto(E entity);

    List<E> mapToEntityList(List<D> dtoList);

    List<D> mapToDtoList(List<E> entityList);
}
