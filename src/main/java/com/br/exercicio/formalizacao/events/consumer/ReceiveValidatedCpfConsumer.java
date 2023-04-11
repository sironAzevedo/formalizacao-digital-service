package com.br.exercicio.formalizacao.events.consumer;

import com.br.exercicio.formalizacao.domain.dto.CustomerRequestDTO;
import com.br.exercicio.formalizacao.domain.dto.CustomerResponseDTO;
import com.br.exercicio.formalizacao.events.consumer.mapper.CustomerMessageMapper;
import com.br.exercicio.formalizacao.events.consumer.message.CustomerConsumerMessage;
import com.br.exercicio.formalizacao.events.listener.event.NotificationDocumentValidatedEvent;
import com.br.exercicio.formalizacao.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiveValidatedCpfConsumer {

    private final ICustomerService customerService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @KafkaListener(topics = "${kafka.topic.cpf-validated}", groupId = "${kafka.grupo-id}", containerFactory = "kafkaListenerContainerFactory")
    public void receive(CustomerConsumerMessage message) {
        log.info(String.format("Consuming message tp-cpf-validated, message: %s", message.toString()));
        CustomerRequestDTO requestDTO = CustomerMessageMapper.INSTANCE.toCustomer(message);
        CustomerResponseDTO pessoa = customerService.find(message.getCpf());
        requestDTO.setProdutos(pessoa.getProdutos());
        customerService.update(requestDTO, pessoa.getCpf());
        applicationEventPublisher.publishEvent(new NotificationDocumentValidatedEvent(pessoa.getCpf(), pessoa.getProdutos()));
    }
}
