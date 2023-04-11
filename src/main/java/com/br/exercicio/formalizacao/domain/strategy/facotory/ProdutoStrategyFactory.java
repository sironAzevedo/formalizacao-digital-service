package com.br.exercicio.formalizacao.domain.strategy.facotory;

import com.br.exercicio.formalizacao.domain.enums.StatusSolicitacaoEnum;
import com.br.exercicio.formalizacao.domain.enums.TipoProdutoEnum;
import com.br.exercicio.formalizacao.domain.strategy.TipoProdutoStrategy;
import com.br.exercicio.formalizacao.service.IProcessProductStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class ProdutoStrategyFactory {

    private Map<TipoProdutoEnum, IProcessProductStrategyService> strategies;

    @Autowired
    public ProdutoStrategyFactory(Set<IProcessProductStrategyService> strategySet) {
        createStrategy(strategySet);
    }

    public void execute(TipoProdutoEnum tipoProdutoEnum, String cpf, StatusSolicitacaoEnum statusSolicitacaoEnum) {
        try {
            this.strategies.get(tipoProdutoEnum).process(cpf, statusSolicitacaoEnum);
        } catch (Exception e) {
            log.warn("Classe para processamento do produto {} não foi encontrada", tipoProdutoEnum);
        }
    }

    private void createStrategy(Set<IProcessProductStrategyService> strategySet) {
        Map<TipoProdutoEnum, IProcessProductStrategyService> map = new HashMap<>();
        strategySet.stream().forEach(k -> map.put(k.getClass().getDeclaredAnnotation(TipoProdutoStrategy.class).value(), k));
        this.strategies = map;
    }
}
