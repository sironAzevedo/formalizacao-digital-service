package com.br.exercicio.formalizacao.events.listener;

import com.br.exercicio.formalizacao.domain.enums.StatusSolicitacaoEnum;
import com.br.exercicio.formalizacao.events.listener.event.NotificationDocumentValidatedEvent;
import com.br.exercicio.formalizacao.events.producer.SendCpfValidationProducer;
import com.br.exercicio.formalizacao.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductProcessingListener {
    private final SendCpfValidationProducer cpfValidationProducer;
    private final ICustomerService customerService;


    @EventListener(NotificationDocumentValidatedEvent.class)
    public void processamentoProduto(NotificationDocumentValidatedEvent message) {
        message.getProdutos().forEach(p -> {
            if(StatusSolicitacaoEnum.SOLICITADO.equals(p.getStatusSolicitacao())){
                p.getTipoProduto();
            }
        });


        //cpfValidationProducer.send(message.getCpf());
    }
}
