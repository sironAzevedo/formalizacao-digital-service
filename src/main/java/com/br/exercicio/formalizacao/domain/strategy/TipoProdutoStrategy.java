package com.br.exercicio.formalizacao.domain.strategy;

import com.br.exercicio.formalizacao.domain.enums.TipoProdutoEnum;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface TipoProdutoStrategy {
    TipoProdutoEnum value();
}
