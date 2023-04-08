package com.br.exercicio.formalizacao.adapters;

import com.br.exercicio.formalizacao.adapters.outbound.repository.CustomerRepository;
import com.br.exercicio.formalizacao.adapters.outbound.repository.mapper.CustomerMapper;
import com.br.exercicio.formalizacao.application.core.domain.Customer;
import com.br.exercicio.formalizacao.application.ports.outbound.CustomerOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerAdapter implements CustomerOutputPort {

    private final CustomerRepository customerRepository;

    @Override
    public void insert(Customer customer) {
        var entity = CustomerMapper.INSTANCE.toCustomerEntity(customer);
        customerRepository.save(entity);
    }

    @Override
    public Optional<Customer> find(String id) {
        return customerRepository.findById(id)
                .map(CustomerMapper.INSTANCE::toCustomer);
    }

    @Override
    public void update(Customer customer) {
        var entity = CustomerMapper.INSTANCE.toCustomerEntity(customer);
        customerRepository.save(entity);
    }

    @Override
    public void delete(Customer customer) {
        var entity = CustomerMapper.INSTANCE.toCustomerEntity(customer);
        customerRepository.delete(entity);
    }
}
