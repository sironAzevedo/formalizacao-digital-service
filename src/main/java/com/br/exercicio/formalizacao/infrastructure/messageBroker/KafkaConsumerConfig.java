package com.br.exercicio.formalizacao.infrastructure.messageBroker;

import com.br.exercicio.formalizacao.adapters.inbound.consumer.message.CustomerMessage;
import com.br.exercicio.formalizacao.infrastructure.messageBroker.deserializer.CustomJsonDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.grupo-id}")
    private String grupo_id;

    @Value(value = "${kafka.topic.offset.reset}")
    private String offset_reset;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CustomerMessage> kafkaListenerContainerFactory(
            /*@Value("${kafka.bootstrapAddress}") final String bootstrapAddress,
            @Value("${kafka.grupo-id}") final String grupo_id,
            @Value("${kafka.topic.offset.reset}") final String offset_reset*/
    ) {
        ConcurrentKafkaListenerContainerFactory<String, CustomerMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    public ConsumerFactory<String, CustomerMessage> consumerFactory(
            /*final String bootstrapAddress,
            final String grupo_id,
            final String offset_reset*/
    ) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, grupo_id);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offset_reset);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new CustomJsonDeserializer<>(CustomerMessage.class));
    }
}
