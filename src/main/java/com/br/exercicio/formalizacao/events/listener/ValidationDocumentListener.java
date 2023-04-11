package com.br.exercicio.formalizacao.events.listener;

import com.br.exercicio.formalizacao.events.listener.event.NotificacaoNovaFormalizacaoEvent;
import com.br.exercicio.formalizacao.events.producer.SendCpfValidationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidationDocumentListener {

    private final SendCpfValidationProducer cpfValidationProducer;

    @EventListener(NotificacaoNovaFormalizacaoEvent.class)
    public void validarCPF(NotificacaoNovaFormalizacaoEvent message) {
        cpfValidationProducer.send(message.getCpf());
    }
}
