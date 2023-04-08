package com.br.exercicio.formalizacao.adapters.inbound.controller.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CustomerRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    @NotBlank
    private String zipCode;
}
