package com.br.exercicio.formalizacao.infrastructure;

import com.br.exercicio.formalizacao.adapters.AddressAdapter;
import com.br.exercicio.formalizacao.adapters.CustomerAdapter;
import com.br.exercicio.formalizacao.application.core.usecase.CustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    public CustomerUseCase customerUseCase(
            AddressAdapter addressAdapter,
            CustomerAdapter customerAdapter) {
        return new CustomerUseCase(addressAdapter, customerAdapter);
    }
}
