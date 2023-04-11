package com.br.exercicio.formalizacao.service.impl;

import com.br.exercicio.formalizacao.domain.dto.CustomerRequestDTO;
import com.br.exercicio.formalizacao.domain.dto.CustomerResponseDTO;
import com.br.exercicio.formalizacao.domain.entity.AddressEntity;
import com.br.exercicio.formalizacao.domain.entity.CustomerEntity;
import com.br.exercicio.formalizacao.domain.mapper.AddressMapper;
import com.br.exercicio.formalizacao.domain.mapper.CustomerMapper;
import com.br.exercicio.formalizacao.events.producer.SendCpfValidationProducer;
import com.br.exercicio.formalizacao.repository.AddressClientRepository;
import com.br.exercicio.formalizacao.repository.CustomerRepository;
import com.br.exercicio.formalizacao.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AddressClientRepository addressClientRepository;
    private final SendCpfValidationProducer cpfValidationProducer;

    @Override
    public void insert(CustomerRequestDTO customer) {
        AddressEntity addressEntity = getAddress(customer.getZipCode());
        var entity = CustomerMapper.INSTANCE.toCustomerEntity(customer);
        entity.setAddress(addressEntity);
        log.info(entity.getId());
        customerRepository.save(entity);
        cpfValidationProducer.send(customer.getCpf());
    }

    @Override
    public CustomerResponseDTO find(String id) {
        return customerRepository.findById(id)
                .map(CustomerMapper.INSTANCE::toCustomerResponse)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public CustomerResponseDTO findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf)
                .map(CustomerMapper.INSTANCE::toCustomerResponse)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public void update(CustomerRequestDTO customer, String id) {
        CustomerResponseDTO pessoa = this.find(id);
        AddressEntity address = getAddress(customer.getZipCode());
        CustomerEntity customerEntity = CustomerMapper.INSTANCE.toCustomerEntity(customer);
        customerEntity.setId(pessoa.getId());
        customerEntity.setAddress(address);
        customerRepository.save(customerEntity);
    }

    @Override
    public void delete(String id) {
        var pessoa = this.find(id);
        customerRepository.deleteById(pessoa.getId());
    }

    private AddressEntity getAddress(String zipCode) {
        AddressEntity address = addressClientRepository.find(zipCode)
                .map(AddressMapper.INSTANCE::to)
                .orElseThrow(() -> new RuntimeException());
        return address;
    }
}
