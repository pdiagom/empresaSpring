package com.example.empresaspring.repository;

import com.example.empresaspring.entity.Empleado;
import org.springframework.data.jpa.domain.Specification;

public abstract class EmpleadoSpecification implements EmpleadoRepository {

    public static Specification<Empleado> filtroPorCriterio(String criterio, String valor) {
        return (root, query, builder) -> {
            if ("dni".equals(criterio)) {
                return builder.equal(root.get("dni"), valor);
            } else if ("nombre".equals(criterio)) {
                return builder.like(root.get("nombre"), "%" + valor + "%");
            } else if ("sexo".equals(criterio)) {
                return builder.equal(root.get("sexo"), valor);
            } else if ("categoria".equals(criterio)) {
                return builder.equal(root.get("categoria"), Integer.parseInt(valor));
            } else if ("anyos_trabajados".equals(criterio)) {
                return builder.equal(root.get("anyos"), Integer.parseInt(valor));
            }
            return null;
        };
    }
}
