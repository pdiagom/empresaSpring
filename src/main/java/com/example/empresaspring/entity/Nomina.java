package com.example.empresaspring.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
 * La clase {@code Nomina} representa los datos de una nómina
 * generada para un empleado, incluyendo su sueldo y la fecha.
 */
@Entity
@Table(name = "nominas")
@Data
@NoArgsConstructor
public class Nomina implements Serializable {

    /**
     * Identificador único de la nómina, autogenerado.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nomina")
    private Long idNomina;

    /**
     * Relación con el empleado asociado a esta nómina (por DNI).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni", referencedColumnName = "dni", nullable = false)
    private Empleado empleado;

    @Column(name="dni", insertable = false, updatable = false)
    private String dni;
    /**
     * Sueldo total del empleado.
     */
    @Column(name = "sueldo", nullable = false)
    private int sueldo;

    /**
     * Constructor que crea una nueva nómina asociada a un empleado.
     *
     * @param empleado El empleado al que pertenece la nómina.
     * @param sueldo   El sueldo del empleado.
     */
    public Nomina(Empleado empleado, int sueldo) {
        this.empleado = empleado;
        this.dni=empleado.getDni();
        this.sueldo = sueldo;
    }
}