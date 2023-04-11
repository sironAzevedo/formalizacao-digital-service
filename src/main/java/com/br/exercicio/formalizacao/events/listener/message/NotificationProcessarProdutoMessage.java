package com.br.exercicio.formalizacao.events.listener.message;

import com.br.exercicio.formalizacao.domain.dto.ProdutoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationProcessarProdutoMessage {
    private String cpf;

    List<ProdutoDTO> produtos;
}
