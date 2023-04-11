package com.br.exercicio.formalizacao.adapters.inbound.consumer;

import com.br.exercicio.formalizacao.adapters.inbound.consumer.mapper.CustomerMessageMapper;
import com.br.exercicio.formalizacao.adapters.inbound.consumer.message.CustomerMessage;
import com.br.exercicio.formalizacao.application.core.domain.Customer;
import com.br.exercicio.formalizacao.application.ports.inbound.CustomerInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@RequiredArgsConstructor
public class ReceiveValidatedCpfConsumer {

    @Autowired
    private CustomerInputPort customerInputPort;

    @KafkaListener(topics = "tp-cpf-validated", groupId = "formalizacao", containerFactory = "kafkaListenerContainerFactory")
    //@KafkaListener(topics = "${kafka.topic.cpf-validated}", groupId = "${kafka.topic.grupo-id}", containerFactory = "kafkaListenerContainerFactory")
    public void receive(CustomerMessage message) {
        log.info(String.format("Consuming netflix_movie, movie: %s", message.toString()));
        Customer customer = CustomerMessageMapper.INSTANCE.toCustomer(message);
        customerInputPort.update(customer, message.getZipCode());
    }
}
