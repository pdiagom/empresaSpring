package com.example.empresaspring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Clase que representa a un empleado, extendiendo la clase base {@code Persona}.
 * Incluye atributos adicionales como categoría y años de servicio.
 */
@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Empleado extends Persona {

    /**
     * Categoría del empleado (debe estar entre 1 y 10).
     */
    @Column(nullable = false)
    private int categoria = 1;

    /**
     * Años de servicio del empleado.
     */
    @Column(name = "anyos_trabajados", nullable = false)
    private int anyos = 0;

    /**
     * Constructor que crea un nuevo empleado con un DNI, nombre y género,
     * asignando una categoría predeterminada de 1 y años de servicio en 0.
     *
     * @param dni    El DNI del empleado.
     * @param nombre El nombre del empleado.
     * @param sexo   El género del empleado.
     */
    public Empleado(String dni, String nombre, String sexo) {
        super(dni, nombre, sexo);
    }

    /**
     * Constructor que crea un nuevo empleado con un DNI, nombre, género,
     * categoría y años de servicio. Valida que los parámetros sean correctos.
     *
     * @param dni       El DNI del empleado.
     * @param nombre    El nombre del empleado.
     * @param sexo      El género del empleado.
     * @param categoria La categoría del empleado (debe estar entre 1 y 10).
     * @param anyos     Los años de servicio del empleado (debe ser >= 0).
     * @throws IllegalArgumentException Si la categoría o los años no son válidos.
     */
    public Empleado(String dni, String nombre, String sexo, int categoria, int anyos) {
        super(dni, nombre, sexo);
        setCategoria(categoria);
        setAnyos(anyos);
    }

    /**
     * Establece la categoría del empleado.
     *
     * @param categoria La nueva categoría (debe estar entre 1 y 10).
     * @throws IllegalArgumentException Si la categoría no es válida.
     */
    public void setCategoria(int categoria) {
        if (categoria < 1 || categoria > 10) {
            throw new IllegalArgumentException("La categoría debe estar entre 1 y 10.");
        }
        this.categoria = categoria;
    }

    /**
     * Establece los años de servicio del empleado.
     *
     * @param anyos Los años de servicio (debe ser >= 0).
     * @throws IllegalArgumentException Si los años no son válidos.
     */
    public void setAnyos(int anyos) {
        if (anyos < 0) {
            throw new IllegalArgumentException("Los años no pueden ser negativos.");
        }
        this.anyos = anyos;
    }

    /**
     * Incrementa en 1 el número de años de servicio del empleado.
     */
    public void incrAnyo() {
        this.anyos += 1;
    }

    /**
     * Imprime los detalles del empleado.
     */
    @Override
    public void imprime() {
        System.out.println(
                "Nombre: " + getNombre() + ", DNI: " + getDni() + ", Sexo: " + getSexo() +
                        ", Categoría: " + categoria + ", Años: " + anyos);
    }
}