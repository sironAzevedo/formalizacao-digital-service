package com.br.exercicio.formalizacao.adapters.inbound.controller;

import com.br.exercicio.formalizacao.adapters.inbound.controller.mapper.CustomerMapper;
import com.br.exercicio.formalizacao.adapters.inbound.controller.request.CustomerRequest;
import com.br.exercicio.formalizacao.adapters.inbound.controller.response.CustomerResponse;
import com.br.exercicio.formalizacao.application.core.domain.Customer;
import com.br.exercicio.formalizacao.application.ports.inbound.CustomerInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/customers")
public class CustomerController {

    private final CustomerInputPort input;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(
            summary = "Realiza o cadastro da pessoa",
            tags = "Customer"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação de campo"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void inserir(@Valid @RequestBody CustomerRequest request) {
        log.info("INICIO - [inserir pessoas: {}]", request.getCpf());
        Customer customer = CustomerMapper.INSTANCE.to(request);
        input.insert(customer, request.getZipCode());
        log.info("FIM - [inserir pessoas: {}]", request.getCpf());
    }

    @ResponseBody
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(
            summary = "Realiza a consulta de pessoa por código",
            tags = "Customer"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "204", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public CustomerResponse findById(@PathVariable(name = "id") String codigo) {
        log.info("INICIO - [consultarPessoaPorId: {}]", codigo);
        Customer customer = input.find(codigo);;
        log.info("FIM - [consultarPessoaPorId: {}]", codigo);
        return CustomerMapper.INSTANCE.toCustomerResponse(customer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(
            summary = "Realiza atualização da pessoa",
            tags = "Customer"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação de campo"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void update(@PathVariable(name = "id") String codigo,
                       @Valid @RequestBody CustomerRequest request) {
        log.info("INICIO - [atualizar pessoas: {}]", request.getCpf());
        Customer customer = CustomerMapper.INSTANCE.to(request);
        customer.setId(codigo);
        input.update(customer, request.getZipCode());
        log.info("FIM - [atualizar pessoas: {}]", request.getCpf());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Realiza a exclusão da pessoa",
            tags = "Customer"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação de campo"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void delete(@PathVariable(name = "id") String codigo) {
        log.info("INICIO - [deletar pessoa: {}]", codigo);
        input.delete(codigo);
        log.info("FIM - [deletar pessoa: {}]", codigo);
    }
}
