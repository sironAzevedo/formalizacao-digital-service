package com.br.exercicio.formalizacao.application.core.usecase;

import com.br.exercicio.formalizacao.application.core.domain.Address;
import com.br.exercicio.formalizacao.application.core.domain.Customer;
import com.br.exercicio.formalizacao.application.ports.outbound.AddressOutputPort;
import com.br.exercicio.formalizacao.application.ports.outbound.CustomerOutputPort;

public class CustomerUseCase {

    private final AddressOutputPort addressOutputPort;
    private final CustomerOutputPort customerOutputPort;

    public CustomerUseCase(AddressOutputPort addressOutputPort, CustomerOutputPort customerOutputPort) {
        this.addressOutputPort = addressOutputPort;
        this.customerOutputPort = customerOutputPort;
    }

    public void insert(Customer customer, String zipCode) {
        Address address = addressOutputPort.find(zipCode);
        customer.setAddress(address);
        customerOutputPort.insert(customer);
    }
}
