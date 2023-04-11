package com.br.exercicio.formalizacao.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String zipCode;

    @Builder.Default
    private Boolean isValidCpf = Boolean.FALSE;

    @Valid
    private List<ProdutoDTO> produtos;
}
