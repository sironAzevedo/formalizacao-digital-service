package com.br.exercicio.formalizacao.repository;

import com.br.exercicio.formalizacao.domain.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByCpf(String cpf);
}
