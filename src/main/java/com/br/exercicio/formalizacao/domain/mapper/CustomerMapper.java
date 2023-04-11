package com.br.exercicio.formalizacao.domain.mapper;

import com.br.exercicio.formalizacao.domain.dto.CustomerRequestDTO;
import com.br.exercicio.formalizacao.domain.dto.CustomerResponseDTO;
import com.br.exercicio.formalizacao.domain.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    CustomerEntity toCustomerEntity(CustomerRequestDTO customer);


    @Mapping(target="isValidCpf", expression = "java(customerEntity.getIsValidCpf())")
    CustomerResponseDTO toCustomerResponse(CustomerEntity customerEntity);

    @Mapping(target="isValidCpf", expression = "java(customerEntity.getIsValidCpf())")
    CustomerEntity toCustomerEntity(CustomerResponseDTO customerEntity);
}
