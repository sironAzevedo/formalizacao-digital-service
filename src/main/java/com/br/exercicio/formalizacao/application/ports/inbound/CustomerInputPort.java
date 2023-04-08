package com.br.exercicio.formalizacao.application.ports.inbound;

import com.br.exercicio.formalizacao.application.core.domain.Customer;

public interface CustomerInputPort {

    void insert(Customer customer, String zipCode);

    Customer find(String id);

    void update(Customer customer, String zipCode);

    void delete(String id);
}
