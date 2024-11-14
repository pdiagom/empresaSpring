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
    EmpleadoService empleadoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Empleado>> obtenerEmpleados(@RequestParam(required = false) String criterio,
                                                           @RequestParam(required = false) String valor) throws SQLException {
        try {
            List<Empleado> empleados;

            // Si se reciben parámetros de filtro, se aplica el filtrado
            if (criterio != null && valor != null) {
                empleados = empleadoService.obtenerEmpleadosFiltrados(criterio, valor);
            } else {
                // Si no se reciben parámetros, se devuelven todos los empleados
                empleados = empleadoService.obtenerEmpleados();
            }

            return ResponseEntity.status(HttpStatus.OK).body(empleados);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/crear")
    public ResponseEntity<String> guardarEmpleado(@RequestBody Empleado empleado) {
        try {
            boolean resultado = empleadoService.guardar(empleado);
            if (resultado) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Empleado guardado correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo guardar el empleado.");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el empleado.");
        }
    }
    @PutMapping("/editar/{dni}")
    public ResponseEntity<String> editarEmpleado(@PathVariable String dni, @RequestBody Empleado empleado) {
        try {
            if (empleadoService.editar(empleado)) {
                return ResponseEntity.status(HttpStatus.OK).body("Empleado editado correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado.");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar el empleado.");
        }
    }
    @DeleteMapping("/eliminar/{dni}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable String dni) {
        try {
            if (empleadoService.eliminar(dni)) {
                return ResponseEntity.status(HttpStatus.OK).body("Empleado eliminado correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado.");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el empleado.");
        }
    }


}
