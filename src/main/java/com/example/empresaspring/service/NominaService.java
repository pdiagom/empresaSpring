package com.example.empresaspring.service;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public interface NominaService {
    boolean empleadoExiste(String dni) throws SQLException;
    double obtenerSueldo(String dni) throws SQLException;

}
