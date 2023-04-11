package com.br.exercicio.formalizacao.repository;

import com.br.exercicio.formalizacao.domain.entity.AddressClientResponseEntity;

import java.util.Optional;

public interface AddressClientRepository {
    Optional<AddressClientResponseEntity> find(String zipCode);
}
