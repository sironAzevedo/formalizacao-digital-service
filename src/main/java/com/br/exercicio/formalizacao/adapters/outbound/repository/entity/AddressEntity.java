package com.br.exercicio.formalizacao.adapters.outbound.repository.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "address")
public class AddressEntity {

    private String zipCode;
    private String street;
    private String city;
    private String state;
}
