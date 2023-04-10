package com.br.exercicio.formalizacao.adapters.outbound;

import com.br.exercicio.formalizacao.adapters.outbound.client.AddressClient;
import com.br.exercicio.formalizacao.adapters.outbound.client.response.AddressClientResponse;
import com.br.exercicio.formalizacao.adapters.outbound.client.mapper.AddressMapper;
import com.br.exercicio.formalizacao.application.core.domain.Address;
import com.br.exercicio.formalizacao.application.ports.outbound.AddressOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class AddressAdapter implements AddressOutputPort {

    @Autowired
    private AddressClient addressClient;

    @Override
    public Address find(String zipCode) {
        AddressClientResponse addressClientResponse = addressClient.find(zipCode);
        return AddressMapper.INSTANCE.to(addressClientResponse);
    }
}
