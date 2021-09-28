package com.proyectointegrador.clinicaOdontologica.service.Impl;

import com.proyectointegrador.clinicaOdontologica.model.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IPacienteRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteServiceImpl implements IService<PacienteDTO> {

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
        return new PacienteDTO(pacienteRepository.findById(id));
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
