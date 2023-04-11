package com.br.exercicio.formalizacao.controller.client;

import com.br.exercicio.formalizacao.domain.entity.AddressClientResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "addressClient", url = "${api.client.address.url}")
public interface AddressClient {

    @GetMapping("/{zipCode}")
    AddressClientResponseEntity find(@PathVariable("zipCode") String zipCode);

}
