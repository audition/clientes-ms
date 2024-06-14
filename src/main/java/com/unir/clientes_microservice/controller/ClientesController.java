package com.unir.clientes_microservice.controller;
import com.unir.clientes_microservice.model.request.CreateClienteRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.unir.clientes_microservice.model.pojo.Cliente;
import org.springframework.http.HttpStatus;
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




/*
* Obtener Información de un Cliente por ID
    Método: GET
    URL: /clientes/{id}
    Descripción: Obtiene la información de un cliente específico según su ID.
    Parámetros de la URL:
    {id}: ID único del cliente.
    Respuesta Exitosa:
    Código: 200 OK
    Contenido: Objeto JSON del cliente solicitado.
    *
* */

    @GetMapping("/clientes/{clienteId}")
    @Operation(
            operationId = "Obtener un cliente",
            description = "Operacion de lectura",
            summary = "Se devuelve un cliente a partir de su identificador.")
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


/*
    * Microservicio Operador de Calorías y Macros
    Endpoints
    Crear un Nuevo Cliente con Información Nutricional
    Método: POST
    URL: /clientes
    Descripción: Crea un nuevo cliente con toda la información nutricional.
    Cuerpo de la Petición: Objeto JSON con los detalles del cliente y su información nutricional.
    Respuesta Exitosa:
    Código: 201 Created
    Contenido: Objeto JSON del cliente creado.
    Modificar Información de un Cliente Existente
    * */


    @PostMapping("/clientes")
    @Operation(
            operationId = "Insertar un cliente",
            description = "Operacion de escritura",
            summary = "Se crea un cliente a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del cliente a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateClienteRequest.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el cliente con el identificador indicado.")
    public ResponseEntity<Cliente> addCliente(@RequestBody CreateClienteRequest request) {

        Cliente createdCliente = service.createCliente(request);

        if (createdCliente != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }




/*
* Método: PATCH
    URL: /clientes/{id}
    Descripción: Modifica parcialmente la información de un cliente existente según su ID.
    Parámetros de la URL:
    {id}: ID único del cliente a modificar.
    Cuerpo de la Petición: Objeto JSON con los campos a modificar.
    Respuesta Exitosa:
    Código: 200 OK
    Contenido: Objeto JSON del cliente modificado.

* */
@PatchMapping("/clientes/{cliented}")
@Operation(
        operationId = "Modificar parcialmente un cliente",
        description = "RFC 7386. Operacion de escritura",
        summary = "RFC 7386. Se modifica parcialmente un cliente.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos del cliente a crear.",
                required = true,
                content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = String.class))))
@ApiResponse(
        responseCode = "200",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
@ApiResponse(
        responseCode = "400",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
        description = "Cliente inválido o datos incorrectos introducidos.")
public ResponseEntity<Cliente> patchCLiente(@PathVariable String clienteId, @RequestBody String patchBody) {

    Cliente patched = service.updateCliente(clienteId, patchBody);
    if (patched != null) {
        return ResponseEntity.ok(patched);
    } else {
        return ResponseEntity.badRequest().build();
    }
}

}
