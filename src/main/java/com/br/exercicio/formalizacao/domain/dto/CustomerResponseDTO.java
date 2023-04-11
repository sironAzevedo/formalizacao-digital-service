package com.br.exercicio.formalizacao.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerResponseDTO {
    private String id;
    private String nome;
    private String cpf;
    private AddressResponseDTO address;
    private Boolean isValidCpf;
    private List<ProdutoDTO> produtos;
}
