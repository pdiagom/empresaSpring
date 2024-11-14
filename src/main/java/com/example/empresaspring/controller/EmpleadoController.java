package com.example.empresaspring.controller;

import com.example.empresaspring.entity.Empleado;
import com.example.empresaspring.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Empleado>> obtenerEmpleados(@RequestParam(required = false) String criterio,
                                                           @RequestParam(required = false) String valor) {
        try {
            List<Empleado> empleados = (criterio != null && valor != null)
                    ? empleadoService.obtenerEmpleadosFiltrados(criterio, valor)
                    : empleadoService.obtenerEmpleados();
            return ResponseEntity.status(HttpStatus.OK).body(empleados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<String> guardarEmpleado(@RequestBody Empleado empleado) {
        try {
            boolean resultado = empleadoService.guardar(empleado);
            return resultado
                    ? ResponseEntity.status(HttpStatus.CREATED).body("Empleado guardado correctamente.")
                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo guardar el empleado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el empleado.");
        }
    }

    @PutMapping("/editar/{dni}")
    public ResponseEntity<String> editarEmpleado(@PathVariable String dni, @RequestBody Empleado empleado) {
        try {
            empleado.setDni(dni);  // Aseguramos que el dni no cambie
            boolean resultado = empleadoService.editar(empleado);
            return resultado
                    ? ResponseEntity.status(HttpStatus.OK).body("Empleado editado correctamente.")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar el empleado.");
        }
    }

    @DeleteMapping("/eliminar/{dni}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable String dni) {
        try {
            boolean resultado = empleadoService.eliminar(dni);
            return resultado
                    ? ResponseEntity.status(HttpStatus.OK).body("Empleado eliminado correctamente.")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el empleado.");
        }
    }
}
