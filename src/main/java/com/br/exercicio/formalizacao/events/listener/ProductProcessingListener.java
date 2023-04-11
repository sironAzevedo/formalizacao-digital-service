package com.br.exercicio.formalizacao.events.listener;

import com.br.exercicio.formalizacao.domain.enums.StatusSolicitacaoEnum;
import com.br.exercicio.formalizacao.domain.strategy.facotory.ProdutoStrategyFactory;
import com.br.exercicio.formalizacao.events.listener.event.NotificationDocumentValidatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductProcessingListener {
    private final ProdutoStrategyFactory produtoStrategyFactory;

    @EventListener(NotificationDocumentValidatedEvent.class)
    public void processamentoProduto(NotificationDocumentValidatedEvent message) {
        message.getProdutos().forEach(p -> {
            if(StatusSolicitacaoEnum.SOLICITADO.equals(p.getStatusSolicitacao())) {
                produtoStrategyFactory.execute(p.getTipoProduto(), message.getCpf(), StatusSolicitacaoEnum.EM_PROCESSAMENTO);
            }
        });
    }
}
