package com.example.empresaspring.service;

import com.example.empresaspring.entity.Empleado;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public interface EmpleadoService {
    boolean guardar(Empleado empleado) throws SQLException;
    boolean editar(Empleado empleado) throws SQLException;
    boolean eliminar(String dni) throws SQLException;
    List<Empleado> obtenerEmpleados() throws SQLException;
    Optional<Empleado> obtenerEmpleado(String dni) throws SQLException;
    List<Empleado> obtenerEmpleadosFiltrados(String criterio, String valor) throws SQLException;
}
