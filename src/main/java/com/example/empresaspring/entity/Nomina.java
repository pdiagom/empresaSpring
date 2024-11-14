package com.example.empresaspring.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * La clase {@code Nomina} se encarga de calcular el sueldo de un empleado
 * en función de su categoría y años de servicio.
 * Contiene una lista de sueldos base predefinidos para cada categoría.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nominas")
public class Nomina {

    @Id
    private Long id;  // ID único de la nómina

    @ManyToOne
    private Empleado empleado;  // Relación con la entidad Empleado

    private int categoria;  // Categoría del empleado
    private int anyos;  // Años de servicio del empleado

    /**
     * Sueldo base para cada categoría de empleado.
     */
    private static final int[] SUELDO_BASE = {
            50000, 70000, 90000, 110000, 130000,
            150000, 170000, 190000, 210000, 230000
    };

    /**
     * Calcula el sueldo total de un empleado basado en su categoría
     * y sus años de servicio.
     *
     * @param empleado El empleado del cual se quiere calcular el sueldo.
     * @return El sueldo total del empleado, que incluye el sueldo base
     *         y un incremento por cada año de servicio.
     */
    public int calcularSueldo() {
        return SUELDO_BASE[categoria - 1] + 5000 * anyos;
    }
}
