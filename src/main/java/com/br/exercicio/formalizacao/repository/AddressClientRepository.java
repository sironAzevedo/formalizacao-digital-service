package com.br.exercicio.formalizacao.repository;

import com.br.exercicio.formalizacao.domain.entity.AddressClientResponseEntity;

import java.util.Optional;

public interface AddressRepository {
    Optional<AddressClientResponseEntity> find(String zipCode);
}
