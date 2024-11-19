package com.example.empresaspring.controller;

import com.example.empresaspring.service.NominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar las nóminas.
 */
@RestController
@RequestMapping("/nominas")
public class NominaController {

    @Autowired
    private NominaService nominaService;

    /**
     * Obtener el sueldo de un empleado por su DNI.
     *
     * @param dni DNI del empleado.
     * @return Sueldo del empleado.
     */
    @GetMapping("/obtenerSueldo/{dni}")
    public ResponseEntity<Double> obtenerSueldo(@PathVariable String dni) {
        Double sueldo = nominaService.obtenerSueldo(dni);
        return ResponseEntity.ok(sueldo);
    }
}
