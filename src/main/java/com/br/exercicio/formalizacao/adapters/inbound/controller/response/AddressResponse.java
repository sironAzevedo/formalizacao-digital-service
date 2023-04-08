package com.br.exercicio.formalizacao.adapters.inbound.controller.response;

import lombok.Data;

@Data
public class AddressResponse {
    private String zipCode;
    private String street;
    private String city;
    private String state;
}
