package com.br.exercicio.formalizacao.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressEntity {

    private String zipCode;
    private String street;
    private String city;
    private String state;
}
