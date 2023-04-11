package com.br.exercicio.formalizacao.events.consumer.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerConsumerMessage {
    private String nome;
    private String cpf;
    private Boolean isValidCpf;
}
