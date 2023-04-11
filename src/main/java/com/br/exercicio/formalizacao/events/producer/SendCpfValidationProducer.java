package com.br.exercicio.formalizacao.events.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendCpfValidationProducer {

    @Value(value = "${kafka.topic.cpf-validation}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String cpf) {
        log.info(String.format("Enviando o cpf (%s) para validação no topic: %s", cpf, topic));
        this.kafkaTemplate.send(topic, cpf);
    }
}
