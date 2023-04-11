package com.br.exercicio.formalizacao.events.listener;

import com.br.exercicio.formalizacao.domain.dto.CustomerResponseDTO;
import com.br.exercicio.formalizacao.events.listener.message.NotificationProcessarProdutoMessage;
import com.br.exercicio.formalizacao.events.listener.message.NotificationValidationDocumentMessage;
import com.br.exercicio.formalizacao.events.producer.SendCpfValidationProducer;
import com.br.exercicio.formalizacao.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificacaoUpdateStatusSolicitacaoProdutoListener {

    private final SendCpfValidationProducer cpfValidationProducer;
    private final ICustomerService customerService;


    @EventListener(NotificationProcessarProdutoMessage.class)
    public void processamentoProduto(NotificationProcessarProdutoMessage message) {
        message.getProdutos().forEach(p -> {
            p.getTipoProduto();
        });


        //cpfValidationProducer.send(message.getCpf());
    }
}
