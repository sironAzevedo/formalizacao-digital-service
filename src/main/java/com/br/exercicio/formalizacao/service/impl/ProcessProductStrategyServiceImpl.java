package com.br.exercicio.formalizacao.service.impl;

import com.br.exercicio.formalizacao.domain.enums.TipoProdutoEnum;
import com.br.exercicio.formalizacao.domain.strategy.TipoProdutoStrategy;
import com.br.exercicio.formalizacao.service.IProcessProductStrategyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@TipoProdutoStrategy(TipoProdutoEnum.CARTAO_CREDITO)
public class ProcessProductStrategyServiceImpl implements IProcessProductStrategyService {


    @Override
    public void process(String cpf) {

    }
}
