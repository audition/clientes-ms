package com.unir.clientes_microservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.clientes_microservice.data.ClienteRepository;
import com.unir.clientes_microservice.model.pojo.Cliente;
import com.unir.clientes_microservice.model.request.CreateClienteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public Cliente updateCliente(String clienteId, String patchBody) {
        //PATCH se implementa en este caso mediante Merge Patch: https://datatracker.ietf.org/doc/html/rfc7386
        Cliente cliente = repository.getById(Long.valueOf(clienteId));
        if (cliente != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(patchBody));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(cliente)));
                Cliente patched = objectMapper.treeToValue(target, Cliente.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating product {}", clienteId, e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Cliente createCliente(CreateClienteRequest request) {
        /**
         *     private String nombre;
         *     private Integer edad;
         *     private String genero;
         *     private Integer altura;
         *     private Integer peso;
         *     private Integer nivelActividad;
         *     private String objetivo;
         *
         */

        if (request != null && StringUtils.hasLength(request.getNombre().trim())
                && request.getEdad()!=null
                && StringUtils.hasLength(request.getGenero().trim())
                && request.getAltura()!=null
                && request.getPeso()!=null
                && request.getNivelactividad()!=null
                && StringUtils.hasLength(request.getObjetivo().trim())

        ) {

            Cliente cliente = Cliente.builder().nombre(request.getNombre())
                    .edad(request.getEdad())
                    .genero(request.getGenero())
                    .altura(request.getAltura())
                    .peso(request.getPeso())
                    .nivelactividad(request.getNivelactividad())
                    .objetivo(request.getObjetivo())
                    .build();

            return repository.save(cliente);
        } else {
            return null;
        }
    }


}
