package com.unir.clientes_microservice.model.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString


public class Cliente {

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "genero")
    private String genero;

    @Column(name = "altura")
    private Integer altura;

    @Column(name = "peso")
    private Integer peso;

    @Column(name = "nivelActividad")
    private Integer nivelActividad;

    @Column(name = "objetivo")
    private String objetivo;

    public void update(ClienteDto clienteDto) {
        this.nombre = clienteDto.getNombre();
        this.edad = clienteDto.getEdad();
        this.genero = clienteDto.getGenero();
        this.altura = clienteDto.getAltura();
        this.peso = clienteDto.getPeso();
        this.nivelActividad = clienteDto.getNivelActividad();
        this.objetivo = clienteDto.getObjetivo();
        this.id = clienteDto.getId();
    }



}
