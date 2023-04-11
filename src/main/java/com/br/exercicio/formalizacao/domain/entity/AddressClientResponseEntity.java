package com.br.exercicio.formalizacao.domain.entity;

import lombok.Data;

@Data
public class AddressClientResponseEntity {

    private String zipCode;
    private String street;
    private String city;
    private String state;
}
