package com.br.exercicio.formalizacao.service;

import com.br.exercicio.formalizacao.domain.enums.StatusSolicitacaoEnum;

public interface IProcessProductStrategyService {

    void process(String cpf, StatusSolicitacaoEnum statusSolicitacaoEnum);
}
