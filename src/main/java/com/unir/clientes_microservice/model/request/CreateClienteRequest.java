package com.unir.clientes_microservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateClienteRequest {

    private Long id;
    private String nombre;
    private Integer edad;
    private String genero;
    private Integer altura;
    private Integer peso;
    private Integer nivelActividad;
    private String objetivo;

}
