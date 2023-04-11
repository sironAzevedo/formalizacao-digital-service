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

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@TipoProdutoStrategy(TipoProdutoEnum.CARTAO_DEBITO)
public class ProcessProductCartaoDebitoStrategyServiceImpl implements IProcessProductStrategyService {

    private final ProductCardProcessingProducer productCardProcessingProducer;
    private final ICustomerService customerService;

    @Override
    public void process(String cpf, StatusSolicitacaoEnum statusSolicitacaoEnum) {
        CustomerResponseDTO responseDTO = customerService.find(cpf);
        if(responseDTO.getIsValidCpf()) {
            log.info("INCIO - Processando a formalização do produto {}", TipoProdutoEnum.CARTAO_DEBITO);
            processamento(responseDTO, statusSolicitacaoEnum);
            log.info("FIM - Processando a formalização do produto {}", TipoProdutoEnum.CARTAO_DEBITO);
        }
    }

    private void processamento(CustomerResponseDTO responseDTO, StatusSolicitacaoEnum statusSolicitacaoEnum) {
        CustomerRequestDTO requestDTO = getCustomerRequestDTO(responseDTO);
        List<ProdutoDTO> produtosaux = responseDTO.getProdutos().stream()
                .filter(c -> !TipoProdutoEnum.CARTAO_DEBITO.equals(c.getTipoProduto()))
                .collect(Collectors.toList());

        responseDTO.getProdutos().stream()
                .filter(c -> TipoProdutoEnum.CARTAO_DEBITO.equals(c.getTipoProduto()))
                .collect(Collectors.toList())
                .stream()
                .forEach(l -> {
                    l.setStatusSolicitacao(statusSolicitacaoEnum);
                    produtosaux.add(l);

                    ProductCardProcessingMessage message = ProductCardProcessingMessage
                            .builder()
                            .cpf(responseDTO.getCpf())
                            .tipoProdutoEnum(l.getTipoProduto())
                            .build();
                    productCardProcessingProducer.send(message);
                });

        requestDTO.setProdutos(produtosaux);
        customerService.update(requestDTO, responseDTO.getCpf());
    }

    private static CustomerRequestDTO getCustomerRequestDTO(CustomerResponseDTO responseDTO) {
        CustomerRequestDTO requestDTO = CustomerRequestDTO
                .builder()
                .nome(responseDTO.getNome())
                .cpf(responseDTO.getCpf())
                .zipCode(responseDTO.getAddress().getZipCode())
                .isValidCpf(responseDTO.getIsValidCpf())
                .build();
        return requestDTO;
    }
}
