package com.br.exercicio.formalizacao.service.impl;

import com.br.exercicio.formalizacao.domain.dto.CustomerRequestDTO;
import com.br.exercicio.formalizacao.domain.dto.CustomerResponseDTO;
import com.br.exercicio.formalizacao.domain.entity.AddressEntity;
import com.br.exercicio.formalizacao.domain.entity.CustomerEntity;
import com.br.exercicio.formalizacao.domain.entity.ProdutoEntity;
import com.br.exercicio.formalizacao.domain.mapper.AddressMapper;
import com.br.exercicio.formalizacao.domain.mapper.CustomerMapper;
import com.br.exercicio.formalizacao.domain.mapper.ProdutoMapper;
import com.br.exercicio.formalizacao.events.listener.event.NotificacaoNovaFormalizacaoEvent;
import com.br.exercicio.formalizacao.repository.AddressClientRepository;
import com.br.exercicio.formalizacao.repository.CustomerRepository;
import com.br.exercicio.formalizacao.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AddressClientRepository addressClientRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void insert(CustomerRequestDTO customer) {
        AddressEntity addressEntity = getAddress(customer.getZipCode());
        var entity = CustomerMapper.INSTANCE.toCustomerEntity(customer);
        entity.setAddress(addressEntity);;
        customerRepository.save(entity);
        applicationEventPublisher.publishEvent(new NotificacaoNovaFormalizacaoEvent(customer.getCpf()));
    }

    @Override
    public CustomerResponseDTO find(String cpf) {
        return customerRepository.findById(cpf)
                .map(CustomerMapper.INSTANCE::toCustomerResponse)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public void update(CustomerRequestDTO customer, String cpf) {
        CustomerResponseDTO pessoa = this.find(cpf);
        CustomerEntity customerEntity = CustomerMapper.INSTANCE.toCustomerEntity(customer);
        customerEntity.setAddress(getAddress(customer.getZipCode()));
        customerRepository.save(customerEntity);
    }

    @Override
    public void delete(String id) {
        var pessoa = this.find(id);
        customerRepository.deleteById(pessoa.getCpf());
    }

    private AddressEntity getAddress(String zipCode) {
        AddressEntity address = addressClientRepository.find(zipCode)
                .map(AddressMapper.INSTANCE::to)
                .orElseThrow(() -> new RuntimeException());
        return address;
    }

}
