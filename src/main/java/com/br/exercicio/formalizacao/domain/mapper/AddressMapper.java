package com.br.exercicio.formalizacao.adapters.outbound.client.mapper;

import com.br.exercicio.formalizacao.domain.entity.AddressClientResponseEntity;
import com.br.exercicio.formalizacao.application.core.domain.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address to(AddressClientResponseEntity addressClientResponseEntity);
}
