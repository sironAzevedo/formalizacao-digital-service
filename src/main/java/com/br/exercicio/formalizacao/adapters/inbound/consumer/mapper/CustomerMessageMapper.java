package com.br.exercicio.formalizacao.adapters.inbound.consumer.mapper;

import com.br.exercicio.formalizacao.adapters.inbound.consumer.message.CustomerMessage;
import com.br.exercicio.formalizacao.application.core.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMessageMapper {
    CustomerMessageMapper INSTANCE = Mappers.getMapper(CustomerMessageMapper.class);

    @Mapping(target = "address", ignore = true)
    Customer toCustomer(CustomerMessage request);
}
