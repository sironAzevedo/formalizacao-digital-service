package com.br.exercicio.formalizacao.application.ports.outbound;

import com.br.exercicio.formalizacao.application.core.domain.Customer;

public interface CustomerOutputPort {
    void insert(Customer customer);
}
