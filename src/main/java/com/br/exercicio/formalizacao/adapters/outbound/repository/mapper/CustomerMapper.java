package com.br.exercicio.formalizacao.adapters.outbound.repository.mapper;

import com.br.exercicio.formalizacao.adapters.outbound.repository.entity.CustomerEntity;
import com.br.exercicio.formalizacao.application.core.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerEntity toCustomerEntity(Customer customer);

    Customer toCustomer(CustomerEntity customer);
}
