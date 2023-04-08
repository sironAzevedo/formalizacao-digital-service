package com.br.exercicio.formalizacao.application.core.usecase;

import com.br.exercicio.formalizacao.application.core.domain.Address;
import com.br.exercicio.formalizacao.application.core.domain.Customer;
import com.br.exercicio.formalizacao.application.ports.inbound.CustomerInputPort;
import com.br.exercicio.formalizacao.application.ports.outbound.AddressOutputPort;
import com.br.exercicio.formalizacao.application.ports.outbound.CustomerOutputPort;

public class CustomerUseCase implements CustomerInputPort {

    private final AddressOutputPort addressOutputPort;
    private final CustomerOutputPort customerOutputPort;

    public CustomerUseCase(AddressOutputPort addressOutputPort, CustomerOutputPort customerOutputPort) {
        this.addressOutputPort = addressOutputPort;
        this.customerOutputPort = customerOutputPort;
    }

    @Override
    public void insert(Customer customer, String zipCode) {
        Address address = addressOutputPort.find(zipCode);
        customer.setAddress(address);
        customerOutputPort.insert(customer);
    }

    @Override
    public Customer find(String id) {
        return customerOutputPort.find(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public void update(Customer customer, String zipCode) {
        var pessoa = this.find(customer.getId());
        var address = addressOutputPort.find(zipCode);
        pessoa.setAddress(address);
        customerOutputPort.update(pessoa);
    }

    @Override
    public void delete(String id) {
        var pessoa = this.find(id);
        customerOutputPort.delete(pessoa);
    }
}
