package com.example.empresaspring.service;

import com.example.empresaspring.DTO.EmpleadoDTO;
import com.example.empresaspring.entity.Empleado;
import com.example.empresaspring.exception.NotFoundException;
import com.example.empresaspring.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    /**
     * Obtiene la lista de todos los empleados como DTOs.
     *
     * @return Lista de empleados.
     */
    public List<EmpleadoDTO> obtenerEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(this::convertirAEmpleadoDTO)
                .collect(Collectors.toList());
    }

    /**
     * Crea un nuevo empleado a partir de un DTO.
     *
     * @param empleadoDTO Datos del nuevo empleado.
     * @return El DTO del empleado creado.
     */
    public EmpleadoDTO crear(EmpleadoDTO empleadoDTO) {
        Empleado empleado = convertirAEmpleadoEntidad(empleadoDTO);
        Empleado empleadoGuardado = empleadoRepository.save(empleado);
        return convertirAEmpleadoDTO(empleadoGuardado);
    }

    /**
     * Obtiene un empleado por DNI.
     *
     * @param dni El DNI del empleado.
     * @return El DTO del empleado.
     * @throws NotFoundException Si el empleado no existe.
     */
    public EmpleadoDTO obtenerEmpleado(String dni) {
        Empleado empleado = empleadoRepository.findById(dni)
                .orElseThrow(() -> new NotFoundException("Empleado no encontrado con DNI: " + dni));
        return convertirAEmpleadoDTO(empleado);
    }

    /**
     * Obtiene empleados filtrados según un criterio.
     *
     * @param criterio El criterio de filtrado.
     * @param valor    El valor a buscar.
     * @return Lista de empleados que cumplen el criterio.
     */
    public List<EmpleadoDTO> obtenerEmpleadosFiltrados(String criterio, String valor) {
        List<Empleado> empleados;
        switch (criterio) {
            case "dni":
                empleados = empleadoRepository.findByDniContainingIgnoreCase(valor);
                break;
            case "nombre":
                empleados = empleadoRepository.findByNombreContaining(valor);
                break;
            case "sexo":
                empleados = empleadoRepository.findBySexo(valor);
                break;
            case "categoria":
                empleados = empleadoRepository.findByCategoria(Integer.parseInt(valor));
                break;
            case "anyos_trabajados":
                empleados = empleadoRepository.findByAnyos(Integer.parseInt(valor));
                break;
            default:
                throw new IllegalArgumentException("Criterio de búsqueda inválido: " + criterio);
        }
        return empleados.stream().map(this::convertirAEmpleadoDTO).collect(Collectors.toList());
    }

    /**
     * Edita los datos de un empleado existente.
     *
     * @param empleadoDTO Los nuevos datos del empleado.
     * @return El DTO del empleado actualizado.
     * @throws NotFoundException Si el empleado no existe.
     */
    public EmpleadoDTO editar(String dni, EmpleadoDTO empleadoDTO) {
        Empleado empleado = empleadoRepository.findById(dni)
                .orElseThrow(() -> new NotFoundException("Empleado no encontrado con DNI: " + empleadoDTO.getDni()));

        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setSexo(empleadoDTO.getSexo());
        empleado.setCategoria(empleadoDTO.getCategoria());
        empleado.setAnyos(empleadoDTO.getAnyos());

        Empleado empleadoActualizado = empleadoRepository.save(empleado);
        return convertirAEmpleadoDTO(empleadoActualizado);
    }

    /**
     * Elimina un empleado por su DNI.
     *
     * @param dni El DNI del empleado a eliminar.
     * @throws NotFoundException Si el empleado no existe.
     */
    public void eliminar(String dni) {
        if (!empleadoRepository.existsById(dni)) {
            throw new NotFoundException("Empleado no encontrado con DNI: " + dni);
        }
        empleadoRepository.deleteById(dni);
    }

    // Métodos auxiliares para convertir entre Empleado y EmpleadoDTO
    private EmpleadoDTO convertirAEmpleadoDTO(Empleado empleado) {
        return new EmpleadoDTO(
                empleado.getDni(),
                empleado.getNombre(),
                empleado.getSexo(),
                empleado.getCategoria(),
                empleado.getAnyos()
        );
    }

    private Empleado convertirAEmpleadoEntidad(EmpleadoDTO empleadoDTO) {
        return new Empleado(
                empleadoDTO.getDni(),
                empleadoDTO.getNombre(),
                empleadoDTO.getSexo(),
                empleadoDTO.getCategoria(),
                empleadoDTO.getAnyos()
        );
    }
}
