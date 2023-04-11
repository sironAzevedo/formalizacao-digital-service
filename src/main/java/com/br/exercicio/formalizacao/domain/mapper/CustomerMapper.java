package com.br.exercicio.formalizacao.domain.mapper;

import com.br.exercicio.formalizacao.domain.dto.CustomerRequestDTO;
import com.br.exercicio.formalizacao.domain.dto.CustomerResponseDTO;
import com.br.exercicio.formalizacao.domain.dto.ProdutoDTO;
import com.br.exercicio.formalizacao.domain.entity.CustomerEntity;
import com.br.exercicio.formalizacao.domain.entity.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    //@Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "produtos", expression = "java(resolveProdutoEntities(customer.getProdutos()))")
    CustomerEntity toCustomerEntity(CustomerRequestDTO customer);

    @Mapping(target="isValidCpf", expression = "java(customerEntity.getIsValidCpf())")
    @Mapping(target = "produtos", expression = "java(resolveProdutoDtos(customerEntity.getProdutos()))")
    CustomerResponseDTO toCustomerResponse(CustomerEntity customerEntity);

    @Mapping(target="isValidCpf", expression = "java(customerEntity.getIsValidCpf())")
    CustomerEntity toCustomerEntity(CustomerResponseDTO customerEntity);

    default List<ProdutoEntity> resolveProdutoEntities(List<ProdutoDTO> produtos) {
        return ProdutoMapper.INSTANCE.from(produtos);
    }

    default List<ProdutoDTO> resolveProdutoDtos(List<ProdutoEntity> produtos) {
        return ProdutoMapper.INSTANCE.fromProdutoDto(produtos);
    }
}
