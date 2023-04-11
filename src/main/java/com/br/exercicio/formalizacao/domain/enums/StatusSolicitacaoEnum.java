package com.br.exercicio.formalizacao.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StatusSolicitacaoEnum {

    SOLICITADO("Solicitado"),
    EM_PROCESSAMENTO("Em Processamento"),
    SOLICITACAO_EFETUADA("Solicitacao Efetuada"),
    SOLICITACAO_RECUSDA("Solicitacao Recusada");

    private final String description;

    public static StatusSolicitacaoEnum from(String description) {
        return Arrays.stream(StatusSolicitacaoEnum.values())
                .filter(p -> StringUtils.isNotBlank(description) && p.description.equals(description))
                .findAny()
                .orElseThrow(() -> new RuntimeException());
    }
}
