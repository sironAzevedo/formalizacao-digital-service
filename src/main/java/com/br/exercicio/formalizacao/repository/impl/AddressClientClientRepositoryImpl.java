package com.br.exercicio.formalizacao.repository.impl;

import com.br.exercicio.formalizacao.adapters.outbound.client.AddressClient;
import com.br.exercicio.formalizacao.domain.entity.AddressClientResponseEntity;
import com.br.exercicio.formalizacao.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {

    private final AddressClient addressClient;
    @Override
    public Optional<AddressClientResponseEntity> find(String zipCode) {
        return Optional.ofNullable(addressClient.find(zipCode));
    }
}
