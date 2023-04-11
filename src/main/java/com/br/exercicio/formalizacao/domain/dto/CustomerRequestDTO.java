package com.br.exercicio.formalizacao.domain.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CustomerRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String zipCode;

    private Boolean isValidCpf = Boolean.FALSE;

    @Valid
    private List<ProdutoDTO> produtos;
}
