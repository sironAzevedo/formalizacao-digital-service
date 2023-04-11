package com.br.exercicio.formalizacao.events.listener.event;

import com.br.exercicio.formalizacao.domain.dto.ProdutoDTO;

import java.util.List;

public class NotificacaoNovaFormalizacaoEvent  extends DocumentoEvent {
    public NotificacaoNovaFormalizacaoEvent(String cpf) {
        super(cpf);
    }
}
