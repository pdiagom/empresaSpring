package com.example.empresaspring.service;

import com.example.empresaspring.entity.Nomina;
import com.example.empresaspring.repository.EmpleadoRepository;
import com.example.empresaspring.repository.NominaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class NominaServiceImpl implements NominaService{
    @Autowired
    private NominaRepository nominaRepository;

    @Override
    public boolean empleadoExiste(String dni) throws SQLException {
        try {
            return nominaRepository.existsById(dni);  // EmpleadoRepository extiende JpaRepository, por lo que ya tiene el método existsById
        } catch (Exception e) {
            throw new SQLException("Error al verificar si el empleado existe", e);
        }    }

    @Override
    public double obtenerSueldo(String dni) throws SQLException {
        Optional<Nomina> nomina = nominaRepository.findById(dni);
        if (nomina.isPresent()) {
            return nomina.get().calcularSueldo(); // Retorna el sueldo de la nomina
        } else {
            throw new SQLException("Empleado no encontrado con el DNI: " + dni);
        }
    }
}
