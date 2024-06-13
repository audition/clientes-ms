package com.unir.clientes_microservice.model.pojo;



import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString



public class ClienteDto {

    private Long id;
    private String nombre;
    private Integer edad;
    private String genero;
    private Integer altura;
    private Integer peso;
    private Integer nivelActividad;
    private String objetivo;


}
