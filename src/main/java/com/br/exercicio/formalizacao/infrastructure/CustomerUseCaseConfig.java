package com.br.exercicio.formalizacao.infrastructure;

import com.br.exercicio.formalizacao.adapters.outbound.AddressAdapter;
import com.br.exercicio.formalizacao.adapters.outbound.CustomerAdapter;
import com.br.exercicio.formalizacao.adapters.outbound.SendCpfValidationAdapter;
import com.br.exercicio.formalizacao.application.core.usecase.CustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerUseCaseConfig {

    @Bean
    public CustomerUseCase customerUseCase(
            AddressAdapter addressAdapter,
            CustomerAdapter customerAdapter,
            SendCpfValidationAdapter sendCpfValidationAdapter) {
        return new CustomerUseCase(addressAdapter, customerAdapter, sendCpfValidationAdapter);
    }
}
