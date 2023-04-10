package com.br.exercicio.formalizacao.adapters.outbound;

import com.br.exercicio.formalizacao.application.ports.outbound.SendCpfValidationOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@RequiredArgsConstructor
public class SendCpfValidationAdapter implements SendCpfValidationOutputPort {

    @Value(value = "${kafka.topic.cpf-validation}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(String cpf) {
        log.info(String.format("Enviando o cpf (%s) para validação no topic: %s", cpf, topic));
        this.kafkaTemplate.send(topic, cpf);
    }
}
