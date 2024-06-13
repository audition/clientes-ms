package com.unir.clientes_microservice.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.unir.clientes_microservice.model.pojo.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unir.clientes_microservice.service.ClientesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Cliente Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre clientes alojados en una base de datos en memoria.")

public class ClientesController {

    private final ClientesService service;

    @GetMapping("/clientes/{clienteId}")
    @Operation(
            operationId = "Obtener un cliente",
            description = "Operacion de lectura",
            summary = "Se devuelve un producto a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))


    public ResponseEntity<Cliente> getClientes(@PathVariable String clienteId) {

        log.info("Request received for cliente {}", clienteId);
        Cliente cliente = service.getById(clienteId);

        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }


}
