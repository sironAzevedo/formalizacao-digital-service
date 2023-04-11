package com.br.exercicio.formalizacao.mock;

import com.br.exercicio.formalizacao.domain.dto.ProdutoDTO;
import com.br.exercicio.formalizacao.domain.entity.AddressClientResponseEntity;
import com.br.exercicio.formalizacao.domain.entity.AddressEntity;
import com.br.exercicio.formalizacao.domain.entity.CustomerEntity;
import com.br.exercicio.formalizacao.domain.entity.ProdutoEntity;
import com.br.exercicio.formalizacao.domain.enums.TipoProdutoEnum;
import com.br.exercicio.formalizacao.domain.mapper.ProdutoMapper;

import java.util.Arrays;

public final class MockTest {
    private MockTest(){}

    public static AddressClientResponseEntity addressClientResponseEntity() {
        AddressClientResponseEntity entity = new AddressClientResponseEntity();
        entity.setZipCode("01245789");
        entity.setStreet("Rua formalizao");
        entity.setCity("São Paulo");
        entity.setState("São Paulo");
        return entity;
    }

    public static ProdutoDTO produtoDTO() {
        return ProdutoDTO
                .builder()
                .tipoProduto(TipoProdutoEnum.CARTAO_CREDITO)
                .build();
    }

    public static CustomerEntity customerEntity() {
        CustomerEntity entity = new CustomerEntity();
        entity.setCpf("12345678900");
        entity.setNome("Teste Silva");
        entity.setIsValidCpf(Boolean.FALSE);
        entity.setAddress(addressEntity());
        entity.setProdutos(Arrays.asList(getProdutoEntity()));
        return entity;
    }

    private static ProdutoEntity getProdutoEntity() {
        return ProdutoMapper.INSTANCE.toProdutoEntity(produtoDTO());
    }

    private static AddressEntity addressEntity() {
        return AddressEntity
                .builder()
                .zipCode("01245789")
                .street("Rua formalizao")
                .city("São Paulo")
                .state("São Paulo")
                .build();
    }
}
