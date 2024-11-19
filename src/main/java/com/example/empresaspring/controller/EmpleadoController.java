package com.example.empresaspring.controller;

import com.example.empresaspring.DTO.EmpleadoDTO;
import com.example.empresaspring.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    /**
     * Obtener todos los empleados.
     *
     * @return Lista de empleados.
     */
    @GetMapping("/listar")
    public ResponseEntity<List<EmpleadoDTO>> listarEmpleados() {
        List<EmpleadoDTO> empleados = empleadoService.obtenerEmpleados();
        return ResponseEntity.ok(empleados);
    }

    /**
     * Crear un nuevo empleado.
     *
     * @param empleadoDTO Datos del empleado.
     * @return Empleado creado.
     */
    @PostMapping("/crear")
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO nuevoEmpleado = empleadoService.crear(empleadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }

    /**
     * Buscar empleados filtrados por un criterio.
     *
     * @param criterio Criterio de búsqueda.
     * @param valor    Valor a buscar.
     * @return Lista de empleados filtrados.
     */
    @GetMapping("/filtrar")
    public ResponseEntity<List<EmpleadoDTO>> buscarEmpleados(
            @RequestParam String criterio,
            @RequestParam String valor) {
        List<EmpleadoDTO> empleadosFiltrados = empleadoService.obtenerEmpleadosFiltrados(criterio, valor);
        return ResponseEntity.ok(empleadosFiltrados);
    }

    /**
     * Obtener un empleado por su DNI.
     *
     * @param dni DNI del empleado.
     * @return Empleado encontrado.
     */
    @GetMapping("/obtenerEmpleado/{dni}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleado(@PathVariable String dni) {
        EmpleadoDTO empleado = empleadoService.obtenerEmpleado(dni);
        return ResponseEntity.ok(empleado);
    }

    /**
     * Editar un empleado existente.
     *
     * @param dni         DNI del empleado a editar.
     * @param empleadoDTO Datos del empleado editado.
     * @return Empleado actualizado.
     */
    @PutMapping("/editar/{dni}")
    public ResponseEntity<EmpleadoDTO> editar(
            @PathVariable String dni,
            @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoActualizado = empleadoService.editar(dni, empleadoDTO);
        return ResponseEntity.ok(empleadoActualizado);
    }

    /**
     * Eliminar un empleado por su DNI.
     *
     * @param dni DNI del empleado a eliminar.
     * @return Respuesta de éxito.
     */
    @DeleteMapping("/eliminar/{dni}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable String dni) {
        empleadoService.eliminar(dni);
        return ResponseEntity.noContent().build();
    }
}
