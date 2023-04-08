package com.br.exercicio.formalizacao.application.ports.outbound;

import com.br.exercicio.formalizacao.application.core.domain.Customer;

import java.util.Optional;

public interface CustomerOutputPort {
    void insert(Customer customer);

    Optional<Customer> find(String id);

    void update(Customer customer);

    void delete(Customer customer);
}
