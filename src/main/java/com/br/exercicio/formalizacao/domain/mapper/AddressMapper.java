package com.br.exercicio.formalizacao.domain.mapper;

import com.br.exercicio.formalizacao.domain.entity.AddressClientResponseEntity;
import com.br.exercicio.formalizacao.domain.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressEntity to(AddressClientResponseEntity addressClientResponseEntity);
}
