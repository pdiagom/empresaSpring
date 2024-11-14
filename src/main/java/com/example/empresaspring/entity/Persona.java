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
    private String genero;

    public Persona(String nombre, String genero) {
        this.nombre = nombre;
        this.genero = genero;
    }

    public void imprime() {
        System.out.println(nombre + " " + dni);
    }
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
