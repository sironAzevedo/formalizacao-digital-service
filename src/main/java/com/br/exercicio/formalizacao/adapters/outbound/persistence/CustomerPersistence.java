package com.br.exercicio.formalizacao.adapters.outbound.persistence;

import com.br.exercicio.formalizacao.adapters.outbound.repository.CustomerRepository;
import com.br.exercicio.formalizacao.adapters.outbound.repository.mapper.CustomerMapper;
import com.br.exercicio.formalizacao.application.core.domain.Customer;
import com.br.exercicio.formalizacao.application.ports.outbound.CustomerOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerPersistence implements CustomerOutputPort {

    private final CustomerRepository customerRepository;

    @Override
    public void insert(Customer customer) {
        var entity = CustomerMapper.INSTANCE.to(customer);
        customerRepository.save(entity);
    }
}
