package com.br.exercicio.formalizacao.application.ports.outbound;

public interface SendCpfValidationOutputPort {

    void send(String cpf);
}
