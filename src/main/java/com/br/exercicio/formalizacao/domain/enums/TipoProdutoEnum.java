package com.br.exercicio.formalizacao.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TipoProdutoEnum {
    CARTAO_CREDITO("Cartão de Credito"),
    CARTAO_DEBITO("Cartão de Debito");

    private final String description;

    public static TipoProdutoEnum from(String description) {
        return Arrays.stream(TipoProdutoEnum.values())
                .filter(p -> StringUtils.isNotBlank(description) && p.description.equals(description))
                .findAny()
                .orElseThrow(() -> new RuntimeException());
    }
}
