package com.br.exercicio.formalizacao.domain.dto;

import lombok.Data;

@Data
public class AddressResponseDTO {

    private String zipCode;
    private String street;
    private String city;
    private String state;
}
