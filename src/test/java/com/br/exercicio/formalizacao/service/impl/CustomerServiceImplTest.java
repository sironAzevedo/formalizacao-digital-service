package com.br.exercicio.formalizacao.service.impl;

import com.br.exercicio.formalizacao.config.AbstractTest;
import com.br.exercicio.formalizacao.domain.dto.CustomerRequestDTO;
import com.br.exercicio.formalizacao.domain.dto.ProdutoDTO;
import com.br.exercicio.formalizacao.domain.entity.CustomerEntity;
import com.br.exercicio.formalizacao.domain.enums.TipoProdutoEnum;
import com.br.exercicio.formalizacao.repository.AddressClientRepository;
import com.br.exercicio.formalizacao.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Arrays;
import java.util.Optional;

import static com.br.exercicio.formalizacao.mock.MockTest.addressClientResponseEntity;
import static com.br.exercicio.formalizacao.mock.MockTest.customerEntity;
import static com.br.exercicio.formalizacao.mock.MockTest.produtoDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest extends AbstractTest {

    private AutoCloseable autoCloseable;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressClientRepository addressClientRepository;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Captor
    private ArgumentCaptor<CustomerEntity> customerEntityArgumentCaptor;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private CustomerRequestDTO customerRequestDTO;

    @BeforeEach
    public void init() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(
                customerRepository,
                addressClientRepository,
                applicationEventPublisher
        );

        ProdutoDTO produtoDTO = produtoDTO();
        produtoDTO.setTipoProduto(TipoProdutoEnum.CARTAO_DEBITO);
        customerRequestDTO = CustomerRequestDTO.builder()
                .cpf("12345678900")
                .nome("Teste Silva")
                .zipCode("01245789")
                .produtos(Arrays.asList(produtoDTO))
                .build();
    }

    @Nested
    class insertCustomerTest {
        @Test
        @DisplayName("should insert customer with successfully")
        public void shouldInsertCustomerWithSuccessfully() {
            when(addressClientRepository.find(anyString())).thenReturn(Optional.of(addressClientResponseEntity()));
            when(customerRepository.findById(anyString())).thenReturn(Optional.empty());

            customerService.insert(customerRequestDTO);

            verify(customerRepository, times(1)).save(customerEntityArgumentCaptor.capture());
            CustomerEntity pessoaCaptorValue = customerEntityArgumentCaptor.getValue();
            assertEquals(customerRequestDTO.getCpf(), pessoaCaptorValue.getCpf());
        }

        @Test
        @DisplayName("should increment list products when customer exist and insert customer with successfully")
        public void shouldIncrementListProductsWhenCustomerExistAndInsertCustomerWithSuccessfully() {
            when(addressClientRepository.find(anyString())).thenReturn(Optional.of(addressClientResponseEntity()));
            when(customerRepository.findById(anyString())).thenReturn(Optional.of(customerEntity()));

            customerService.insert(customerRequestDTO);

            verify(customerRepository, times(1)).save(customerEntityArgumentCaptor.capture());
            CustomerEntity pessoaCaptorValue = customerEntityArgumentCaptor.getValue();
            assertEquals(customerRequestDTO.getCpf(), pessoaCaptorValue.getCpf());
            assertTrue(customerRequestDTO.getProdutos().size() > 1);
        }
    }
}