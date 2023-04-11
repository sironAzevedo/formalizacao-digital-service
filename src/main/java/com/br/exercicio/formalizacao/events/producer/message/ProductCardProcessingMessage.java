package com.br.exercicio.formalizacao.events.producer.message;

import com.br.exercicio.formalizacao.domain.enums.TipoProdutoEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCardProcessingMessage {

    private String cpf;
    private TipoProdutoEnum tipoProdutoEnum;
}
