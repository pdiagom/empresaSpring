package com.example.empresaspring.entity;

import com.example.empresaspring.exception.DatosNoCorrectosException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empleados")
public class Empleado extends Persona {

    private int categoria;

    @Column(name="anyos_trabajados")
    private int anyos;

    public Empleado(String dni, String nombre, String genero, int categoria, int anyos) throws DatosNoCorrectosException {
        super(dni, nombre, genero);
        setCategoria(categoria);
        setAnyos(anyos);
    }

    public void setCategoria(int categoria) throws DatosNoCorrectosException {
        if (categoria < 1 || categoria > 10) {
            throw new DatosNoCorrectosException("Categoría debe estar entre 1 y 10.");
        }
        this.categoria = categoria;
    }

    public void setAnyos(int anyos) throws DatosNoCorrectosException {
        if (anyos < 0) {
            throw new DatosNoCorrectosException("Años no pueden ser negativos.");
        }
        this.anyos = anyos;
    }

    public void incrAnyo() {
        this.anyos += 1;
    }

    @Override
    public void imprime() {
        System.out.println("Nombre: " + getNombre() + " DNI: " + getDni() +
                " Género: " + getGenero() + " Categoría: " + categoria + " Años: " + anyos);
    }
}
