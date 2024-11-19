package com.example.empresaspring.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) para la clase {@code Nomina}.
 * Se utiliza para intercambiar datos entre la capa de la API REST y el cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NominaDTO {

    /**
     * Identificador único de la nómina.
     */
    private Long idNomina;

    /**
     * DNI del empleado asociado a la nómina.
     */
    private String dni;

    /**
     * Sueldo total del empleado en la nómina.
     */
    private int sueldo;
}
