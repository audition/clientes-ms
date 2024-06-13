package com.unir.clientes_microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.unir.clientes_microservice.data.ClienteRepository;
import com.unir.clientes_microservice.model.pojo.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class ClientesServiceImpl implements ClientesService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ObjectMapper objectMapper;


    public Cliente getById(String clienteId) {
        return repository.getById(Long.valueOf(clienteId));
    }




}
