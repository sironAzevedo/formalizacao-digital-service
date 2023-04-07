package com.br.exercicio.formalizacao.application.ports.outbound;

import com.br.exercicio.formalizacao.application.core.domain.Address;

public interface AddressOutputPort {

    Address find(String zipCode);
}
