package com.br.exercicio.formalizacao.adapters.inbound.controller.mapper;

import com.br.exercicio.formalizacao.adapters.inbound.controller.request.CustomerRequest;
import com.br.exercicio.formalizacao.adapters.inbound.controller.response.CustomerResponse;
import com.br.exercicio.formalizacao.application.core.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "isValidCpf", ignore = true)
    Customer to(CustomerRequest request);
    CustomerResponse toCustomerResponse(Customer customer);
}
