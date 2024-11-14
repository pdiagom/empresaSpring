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
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    @Transactional
    public boolean guardar(Empleado empleado) {
        empleadoRepository.save(empleado);
        return true;
    }

    @Override
    @Transactional
    public boolean editar(Empleado empleado) {
        Optional<Empleado> empleadoExistente = empleadoRepository.findById(empleado.getDni());
        if (empleadoExistente.isPresent()) {
            Empleado existente = empleadoExistente.get();
            existente.setNombre(empleado.getNombre());
            existente.setGenero(empleado.getGenero());
            existente.setCategoria(empleado.getCategoria());
            existente.setAnyos(empleado.getAnyos());
            empleadoRepository.save(existente);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean eliminar(String dni) {
        Optional<Empleado> empleado = empleadoRepository.findById(dni);
        if (empleado.isPresent()) {
            empleadoRepository.delete(empleado.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Empleado> obtenerEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> obtenerEmpleado(String dni) throws SQLException {return empleadoRepository.findById(dni);
    }

    @Override
    public List<Empleado> obtenerEmpleadosFiltrados(String criterio, String valor) {
        return empleadoRepository.findAll(EmpleadoSpecification.filtroPorCriterio(criterio, valor));
    }
}
