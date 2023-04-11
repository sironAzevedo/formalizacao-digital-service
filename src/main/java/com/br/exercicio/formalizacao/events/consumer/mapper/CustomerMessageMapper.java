package com.br.exercicio.formalizacao.domain.mapper;

import com.br.exercicio.formalizacao.domain.dto.CustomerConsumerMessage;
import com.br.exercicio.formalizacao.domain.dto.CustomerRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMessageMapper {

   CustomerMessageMapper INSTANCE = Mappers.getMapper(CustomerMessageMapper.class);

    @Mapping(target = "address", ignore = true)
    CustomerRequestDTO toCustomer(CustomerConsumerMessage consumerMessage);

}
