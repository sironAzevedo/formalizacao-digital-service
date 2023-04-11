package com.br.exercicio.formalizacao.events.listener;

import com.br.exercicio.formalizacao.events.listener.message.NotificationValidationDocumentMessage;
import com.br.exercicio.formalizacao.events.producer.SendCpfValidationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificacaoNovaFormalizacaoListener {

    private final SendCpfValidationProducer cpfValidationProducer;

    @EventListener(NotificationValidationDocumentMessage.class)
    public void validarCPF(NotificationValidationDocumentMessage message) {
        cpfValidationProducer.send(message.getCpf());
    }
}
