package com.br.exercicio.formalizacao.config;

import com.br.exercicio.formalizacao.domain.dto.CustomerRequestDTO;
import com.br.exercicio.formalizacao.domain.dto.ProdutoDTO;
import com.br.exercicio.formalizacao.domain.enums.TipoProdutoEnum;
import com.br.exercicio.formalizacao.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataLoad {

    private final ICustomerService customerService;

    @PostConstruct
    public void load() {
        ProdutoDTO produto = ProdutoDTO.builder()
                .tipoProduto(TipoProdutoEnum.CARTAO_CREDITO)
                .build();
        CustomerRequestDTO request = CustomerRequestDTO.builder()
                .cpf("90317549006")
                .nome("Teste data load")
                .zipCode("01234567")
                .produtos(Arrays.asList(produto))
                .build();

        customerService.insert(request);
    }
}
