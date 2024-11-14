package com.example.empresaspring.repository;

import com.example.empresaspring.entity.Empleado;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface EmpleadoRepository extends JpaRepository<Empleado,String>, JpaSpecificationExecutor<Empleado> {
}
