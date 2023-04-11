package com.br.exercicio.formalizacao.events.consumer.mapper;

import com.br.exercicio.formalizacao.events.consumer.message.CustomerConsumerMessage;
import com.br.exercicio.formalizacao.domain.dto.CustomerRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMessageMapper {

   CustomerMessageMapper INSTANCE = Mappers.getMapper(CustomerMessageMapper.class);

    @Mapping(target = "zipCode", ignore = true)
    CustomerRequestDTO toCustomer(CustomerConsumerMessage consumerMessage);

}
