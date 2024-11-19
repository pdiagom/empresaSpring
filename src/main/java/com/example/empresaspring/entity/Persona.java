package com.example.empresaspring.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase {@code Persona} representa una entidad base con atributos comunes
 * como DNI, nombre y género. Se utiliza como clase padre en un modelo de datos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Persona {

    /**
     * El DNI de la persona, utilizado como clave primaria en la base de datos.
     */
    @Id
    private String dni;

    /**
     * El nombre de la persona.
     */
    private String nombre;

    /**
     * El género de la persona ('M' para masculino, 'F' para femenino).
     */
    @Column(name = "genero")
    private String sexo;

    /**
     * Constructor que crea una nueva instancia de {@code Persona} con un nombre y género,
     * dejando el DNI como nulo.
     *
     * @param nombre El nombre de la persona.
     * @param sexo   El género de la persona ('M' para masculino, 'F' para femenino).
     */
    public Persona(String nombre, String sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    /**
     * Método para imprimir el nombre y DNI de la persona.
     */
    public void imprime() {
        System.out.println(nombre + " " + dni);
    }
}