package com.example.empresaspring.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) para la clase {@code Empleado}.
 * Se utiliza para intercambiar datos entre la capa de la API REST y el cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {

    /**
     * DNI del empleado.
     */
    private String dni;

    /**
     * Nombre del empleado.
     */
    private String nombre;

    /**
     * Género del empleado ('M' para masculino, 'F' para femenino).
     */
    private String sexo;

    /**
     * Categoría del empleado (entre 1 y 10).
     */
    private int categoria;

    /**
     * Años de servicio del empleado.
     */
    private int anyos;
}
