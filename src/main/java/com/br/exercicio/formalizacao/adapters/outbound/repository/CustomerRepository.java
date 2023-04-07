package com.br.exercicio.formalizacao.adapters.outbound.repository;

import com.br.exercicio.formalizacao.adapters.outbound.repository.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
}
