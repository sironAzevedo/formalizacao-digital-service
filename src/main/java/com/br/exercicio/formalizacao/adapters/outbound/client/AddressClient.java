package com.br.exercicio.formalizacao.adapters.outbound.client;

import com.br.exercicio.formalizacao.adapters.outbound.client.response.AddressClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "addressClient", url = "${api.client.address.url}")
public interface AddressClient {

    @GetMapping("/{zipCode}")
    AddressClientResponse find(@PathVariable("zipCode") String zipCode);

}
