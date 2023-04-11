package com.br.exercicio.formalizacao.adapters.inbound.consumer;

import com.br.exercicio.formalizacao.adapters.inbound.consumer.message.CustomerMessage;
import com.br.exercicio.formalizacao.application.ports.inbound.CustomerInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiveValidatedCpfConsumer {

    private final CustomerInputPort customerInputPort;


    @KafkaListener(topics = "tp-cpf-validated", groupId = "formalizacao", containerFactory = "kafkaListenerContainerFactory")
    public void receive(CustomerMessage message) {
        log.info(String.format("Consuming message tp-cpf-validated, message: %s", message.toString()));

//        customerInputPort.update(requestDTO, pessoa.getId());
    }
}
