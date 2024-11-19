package com.example.empresaspring.service;

import com.example.empresaspring.DTO.EmpleadoDTO;

import org.springframework.stereotype.Service;



import java.util.List;

@Service
public interface EmpleadoService {
    List<EmpleadoDTO> obtenerEmpleados();
    EmpleadoDTO crear(EmpleadoDTO empleadoDTO);
    EmpleadoDTO obtenerEmpleado(String dni);
    List<EmpleadoDTO> obtenerEmpleadosFiltrados(String criterio, String valor);
    EmpleadoDTO editar(String dni, EmpleadoDTO empleadoDTO);
    void eliminar(String dni);
}
