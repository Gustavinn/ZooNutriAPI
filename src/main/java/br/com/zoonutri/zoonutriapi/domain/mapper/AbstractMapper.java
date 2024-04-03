package br.com.zoonutri.zoonutriapi.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;

import java.util.List;

public interface AbstractMapper<T, D> {

    T mapToEntity(D dto);

    @InheritInverseConfiguration
    D mapToDto(T entity);

    List<D> toDtoList(List<T> entities);

    List<T> toEntityList(List<D> dtos);

}
