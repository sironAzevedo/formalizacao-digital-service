package com.br.exercicio.formalizacao.domain.dto;

import com.br.exercicio.formalizacao.domain.enums.StatusSolicitacaoEnum;
import com.br.exercicio.formalizacao.domain.enums.TipoProdutoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    @NotNull
    private TipoProdutoEnum tipoProduto;

    @Builder.Default
    private StatusSolicitacaoEnum statusSolicitacao = StatusSolicitacaoEnum.SOLICITADO;
}
