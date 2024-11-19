package com.example.empresaspring.service;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public interface NominaService {
    double obtenerSueldo(String dni);

}
