package com.example.empresaspring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Persona {

    @Id
    private String dni;

    private String nombre;
    @Column(name="genero")
    private String sexo;

    public Persona(String nombre, String sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public void imprime() {
        System.out.println(nombre + " " + dni);
    }
}
