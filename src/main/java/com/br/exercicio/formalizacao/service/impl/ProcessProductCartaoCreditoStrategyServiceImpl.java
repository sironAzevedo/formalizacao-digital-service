package com.br.exercicio.formalizacao.service.impl;

import com.br.exercicio.formalizacao.domain.dto.CustomerRequestDTO;
import com.br.exercicio.formalizacao.domain.dto.CustomerResponseDTO;
import com.br.exercicio.formalizacao.domain.dto.ProdutoDTO;
import com.br.exercicio.formalizacao.domain.enums.StatusSolicitacaoEnum;
import com.br.exercicio.formalizacao.domain.enums.TipoProdutoEnum;
import com.br.exercicio.formalizacao.domain.strategy.TipoProdutoStrategy;
import com.br.exercicio.formalizacao.events.producer.ProductCardProcessingProducer;
import com.br.exercicio.formalizacao.events.producer.message.ProductCardProcessingMessage;
import com.br.exercicio.formalizacao.service.ICustomerService;
import com.br.exercicio.formalizacao.service.IProcessProductStrategyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@TipoProdutoStrategy(TipoProdutoEnum.CARTAO_CREDITO)
public class ProcessProductCartaoCreditoStrategyServiceImpl implements IProcessProductStrategyService {

    private final ProductCardProcessingProducer productCardProcessingProducer;
    private final ICustomerService customerService;

    @Override
    public void process(String cpf) {
        CustomerResponseDTO responseDTO = customerService.findByCpf(cpf);
        CustomerRequestDTO requestDTO = new CustomerRequestDTO();
        requestDTO.setNome(responseDTO.getNome());
        requestDTO.setCpf(responseDTO.getCpf());
        requestDTO.setZipCode(responseDTO.getAddress().getZipCode());
        requestDTO.setIsValidCpf(requestDTO.getIsValidCpf());

        List<ProdutoDTO> produtosaux = responseDTO.getProdutos().stream()
                .filter(c -> !TipoProdutoEnum.CARTAO_CREDITO.equals(c.getTipoProduto()))
                .collect(Collectors.toList());

        if(responseDTO.getIsValidCpf()) {
            responseDTO.getProdutos().stream()
                    .filter(c -> TipoProdutoEnum.CARTAO_CREDITO.equals(c.getTipoProduto()))
                    .collect(Collectors.toList())
                    .stream()
                    .forEach(l -> {
                        l.setStatusSolicitacao(StatusSolicitacaoEnum.EM_PROCESSAMENTO);
                        produtosaux.add(l);
                        ProductCardProcessingMessage message = ProductCardProcessingMessage
                                .builder()
                                .cpf(cpf)
                                .tipoProdutoEnum(l.getTipoProduto())
                                .build();
                        productCardProcessingProducer.send(message);
                    });

            requestDTO.setProdutos(produtosaux);
            customerService.update(requestDTO, responseDTO.getId());
        }
    }
}
