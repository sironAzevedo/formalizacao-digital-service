package com.br.exercicio.formalizacao.adapters.outbound.repository;

import com.br.exercicio.formalizacao.adapters.outbound.repository.entity.AddressEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<AddressEntity, String> {
}
