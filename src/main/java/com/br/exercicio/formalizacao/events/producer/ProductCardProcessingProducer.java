package com.br.exercicio.formalizacao.events.producer;

import com.br.exercicio.formalizacao.events.producer.message.ProductCardProcessingMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductCardProcessingProducer {

    @Value(value = "${kafka.topic.card-processing}")
    private String topic;
    private final KafkaTemplate<String, ProductCardProcessingMessage> producerProductCardProcessing;

    public void send(ProductCardProcessingMessage message) {
        log.info("Enviando o produto {} do cliente {} para processamento no topico {}", message.getTipoProdutoEnum().getDescription(), message.getCpf(), topic);
        this.producerProductCardProcessing.send(topic, message);
    }
}
