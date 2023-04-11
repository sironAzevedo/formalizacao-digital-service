package com.br.exercicio.formalizacao.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerConsumerMessage {
    private String name;
    private String cpf;
    private Boolean isValidCpf;
}
