package com.example.empresaspring.entity;
import com.example.empresaspring.exception.DatosNoCorrectosException;
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
    private int anyos;

    /**
     * Constructor que crea un nuevo empleado con un DNI, nombre y sexo,
     * asignando una categoría predeterminada de 1 y años en 0.
     *
     * @param dni El DNI del empleado.
     * @param nombre El nombre del empleado.
     * @param sexo El sexo del empleado.
     */
    public Empleado(String dni, String nombre, String sexo) {
        super(dni, nombre, sexo);
        this.categoria = 1;
        this.anyos = 0;
    }

    /**
     * Constructor que crea un nuevo empleado con un DNI, nombre, sexo,
     * categoría y años. Lanza una excepción si la categoría
     * o los años no son válidos.
     *
     * @param dni El DNI del empleado.
     * @param nombre El nombre del empleado.
     * @param sexo El sexo del empleado.
     * @param categoria La categoría del empleado (debe estar entre 1 y 10).
     * @param anyos Los años de servicio del empleado (debe ser 0 o mayor).
     * @throws DatosNoCorrectosException Si la categoría o los años no son válidos.
     */
    public Empleado(String dni, String nombre, String sexo, int categoria, int anyos) throws DatosNoCorrectosException {
        super(dni, nombre, sexo);
        if (categoria < 1 || categoria > 10) {
            throw new DatosNoCorrectosException("Datos no correctos");
        } else {
            this.categoria = categoria;
        }
        if (anyos < 0) {
            throw new DatosNoCorrectosException("Datos no correctos");
        } else {
            this.anyos = anyos;
        }
    }

    public void setCategoria(int categoria) throws DatosNoCorrectosException {
        if (categoria < 1 || categoria > 10) {
            throw new DatosNoCorrectosException("Datos no correctos");
        } else {
            this.categoria = categoria;
        }
    }

    public int getCategoria() {
        return categoria;
    }

    public int getAnyos() {
        return anyos;
    }

    public void incrAnyo() {
        anyos += 1;
    }

    @Override
    public void imprime() {
        System.out.println("Nombre: " + getNombre() + " DNI: " + getDni() + " Sexo: " + getSexo() +
                " Categoria: " + categoria + " Años: " + anyos);
    }

    public void setAnyos(int anyos) {
        this.anyos = anyos;
    }
}
