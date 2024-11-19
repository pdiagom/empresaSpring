package com.example.empresaspring.repository;

import com.example.empresaspring.entity.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,String>{
    List<Empleado> findByNombreContaining(String nombre);
    List<Empleado> findBySexo(String sexo);

    List<Empleado> findByCategoria(Integer categoria);

    List<Empleado> findByAnyos(int anyos);

    List<Empleado> findByDniContainingIgnoreCase(String dni);

    void deleteByDni(String dni);
}
