package com.br.exercicio.formalizacao.events.listener.event;

import com.br.exercicio.formalizacao.domain.dto.ProdutoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class NotificationDocumentValidatedEvent extends DocumentoEvent {
    List<ProdutoDTO> produtos;

    public NotificationDocumentValidatedEvent(String cpf, List<ProdutoDTO> produtos) {
        super(cpf);
        this.produtos = produtos;
    }
}
