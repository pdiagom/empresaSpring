package com.example.empresaspring.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.empresaspring.service.NominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/nominas")
public class NominaController {

    @Autowired
    private NominaService nominaService;

    @GetMapping("/obtenerSueldo/{dni}")
    public ResponseEntity<String> obtenerSueldo(@PathVariable String dni) {
        try {
            boolean existe = nominaService.empleadoExiste(dni);
            if(existe) {
                double sueldo = nominaService.obtenerSueldo(dni);
                return ResponseEntity.status(HttpStatus.OK).body("El sueldo del empleado es: " + sueldo);
            }else{
                return null;
            }
            } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado o error al obtener el sueldo.");
        }
    }
}
