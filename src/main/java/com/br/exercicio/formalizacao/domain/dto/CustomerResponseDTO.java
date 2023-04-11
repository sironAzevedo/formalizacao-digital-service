package com.br.exercicio.formalizacao.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerResponseDTO {
    private String cpf;
    private String nome;
    private AddressResponseDTO address;
    private Boolean isValidCpf;
    private List<ProdutoDTO> produtos;
}
