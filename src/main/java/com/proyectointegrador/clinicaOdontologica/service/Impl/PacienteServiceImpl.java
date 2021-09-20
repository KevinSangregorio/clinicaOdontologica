package com.proyectointegrador.clinicaOdontologica.service.Impl;

import com.proyectointegrador.clinicaOdontologica.dto.DomicilioDTO;
import com.proyectointegrador.clinicaOdontologica.dto.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IPacienteRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PacienteServiceImpl implements IService<PacienteDTO> {

    final static Logger log = Logger.getLogger(OdontologoServiceImpl.class);

    private final IPacienteRepository pacienteRepository;

    @Autowired
    public PacienteServiceImpl(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public PacienteDTO guardar(PacienteDTO p) {
        return new PacienteDTO(pacienteRepository.save(p.toEntity()));
    }

    @Override
    public PacienteDTO buscarPorId(Integer id) {
        return new PacienteDTO(pacienteRepository.getById(id));
    }

    @Override
    public List<PacienteDTO> buscarTodos() {
        List<PacienteDTO> pacientes = new ArrayList<>();

        for (Paciente p : pacienteRepository.findAll()) {
            pacientes.add(new PacienteDTO(p));
        }
        return pacientes;
    }

    @Override
    public PacienteDTO actualizar(PacienteDTO p) {
        Paciente actualizado = null;
        if (pacienteRepository.findById(p.getId()).isPresent()) {
            actualizado = pacienteRepository.save(p.toEntity());
        }
        return new PacienteDTO(actualizado);
    }

    @Override
    public void eliminar(Integer id) {
        pacienteRepository.deleteById(id);
    }
}
