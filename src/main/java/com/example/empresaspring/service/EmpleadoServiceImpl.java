package com.example.empresaspring.service;

import com.example.empresaspring.entity.Empleado;
import com.example.empresaspring.repository.EmpleadoRepository;
import com.example.empresaspring.repository.EmpleadoSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    @Transactional
    public boolean guardar(Empleado empleado) throws SQLException {
        try {
            empleadoRepository.save(empleado);
            return true;
        } catch (Exception e) {
            throw new SQLException("Error al guardar el empleado", e);
        }
    }

    @Override
    @Transactional
    public boolean editar(Empleado empleado) throws SQLException {
        try {
            // Verifica si el empleado existe
            Empleado empleadoExistente = empleadoRepository.findById(empleado.getDni()).orElse(null);
            if (empleadoExistente == null) {
                return false; // No se encuentra el empleado con ese DNI
            }

            // Actualiza la información del empleado
            empleadoExistente.setNombre(empleado.getNombre());
            empleadoExistente.setSexo(empleado.getSexo());
            empleadoExistente.setCategoria(empleado.getCategoria());
            empleadoExistente.setAnyos(empleado.getAnyos());

            // Guarda el empleado actualizado
            empleadoRepository.save(empleadoExistente);

            return true; // Éxito
        } catch (Exception e) {
            // Si ocurre un error, se realizará automáticamente un rollback gracias a @Transactional
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean eliminar(String dni) throws SQLException {
        try {
            Optional<Empleado> empleado = empleadoRepository.findById(dni);
            if (empleado.isPresent()) {
                empleadoRepository.delete(empleado.get());
                return true;
            } else {
                throw new SQLException("Empleado no encontrado");
            }
        } catch (Exception e) {
            throw new SQLException("Error al eliminar el empleado", e);
        }
    }

    @Override
    public List<Empleado> obtenerEmpleados() throws SQLException {
        try {
            return empleadoRepository.findAll();
        } catch (Exception e) {
            throw new SQLException("Error al obtener los empleados", e);
        }
    }

    @Override
    public Empleado obtenerEmpleado(String dni) throws SQLException {
        try {
            return empleadoRepository.findById(dni)
                    .orElseThrow(() -> new SQLException("Empleado no encontrado"));
        } catch (Exception e) {
            throw new SQLException("Error al obtener el empleado", e);
        }
    }

    @Override
    public List<Empleado> obtenerEmpleadosFiltrados(String criterio, String valor) throws SQLException {
        return empleadoRepository.findAll(EmpleadoSpecification.filtroPorCriterio(criterio, valor));
    }
}
