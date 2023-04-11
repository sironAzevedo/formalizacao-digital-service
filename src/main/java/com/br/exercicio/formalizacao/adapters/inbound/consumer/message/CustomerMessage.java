package com.br.exercicio.formalizacao.adapters.inbound.consumer.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerMessage {

    private String id;
    private String nome;
    private String cpf;
    private String zipCode;
    private Boolean isValidCpf;
}
