package com.br.exercicio.formalizacao.adapters.inbound.controller.response;

import lombok.Data;

@Data
public class CustomerResponse {

    private String nome;
    private String cpf;
    private AddressResponse address;
    private Boolean isValidCpf;
}
