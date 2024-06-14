package com.unir.clientes_microservice.service;


import com.unir.clientes_microservice.model.pojo.Cliente;
import com.unir.clientes_microservice.model.request.CreateClienteRequest;

public interface ClientesService {


    Cliente getById(String clienteId);


    Cliente updateCliente(String clienteId, String patchBody);

    Cliente createCliente(CreateClienteRequest request);
}
