package com.br.exercicio.formalizacao.service;

import com.br.exercicio.formalizacao.domain.dto.CustomerRequestDTO;
import com.br.exercicio.formalizacao.domain.dto.CustomerResponseDTO;

public interface ICustomerService {

    void insert(CustomerRequestDTO customer);

    CustomerResponseDTO find(String id);

    CustomerResponseDTO findByCpf(String id);

    void update(CustomerRequestDTO customer, String id);

    void delete(String id);
}
