package com.br.exercicio.formalizacao.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "customers")
public class CustomerEntity {

    @Id
    private String id;
    private String nome;
    private String cpf;
    private AddressEntity address;
    private List<ProdutoEntity> produtos;
    private Boolean isValidCpf;
}
