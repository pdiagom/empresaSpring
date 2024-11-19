package com.example.empresaspring.service;

import com.example.empresaspring.DTO.NominaDTO;
import com.example.empresaspring.entity.Nomina;
import com.example.empresaspring.repository.NominaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NominaServiceImpl implements NominaService{
    @Autowired
    private NominaRepository nominaRepository;

    @Override
    public double obtenerSueldo(String dni) {
        NominaDTO nominaDTO=convertirANominaDTO(nominaRepository.findByDni(dni));
        return nominaDTO.getSueldo();
    }
    /**
     * Método auxiliar para convertir de Nomina a NominaDTO.
     */
    private NominaDTO convertirANominaDTO(Nomina nomina) {
        return new NominaDTO(
                nomina.getIdNomina(),
                nomina.getDni(),
                nomina.getSueldo()
        );
    }

    /**
     * Método auxiliar para convertir de NominaDTO a Nomina.
     */
    private Nomina convertirADominio(NominaDTO nominaDTO) {
        Nomina nomina = new Nomina();
        nomina.setIdNomina(nominaDTO.getIdNomina());
        nomina.setDni(nominaDTO.getDni());
        nomina.setSueldo(nominaDTO.getSueldo());
        return nomina;
    }


}

