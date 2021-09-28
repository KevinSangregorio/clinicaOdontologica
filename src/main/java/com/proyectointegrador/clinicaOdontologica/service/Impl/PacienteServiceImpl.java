package com.proyectointegrador.clinicaOdontologica.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
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
    private final ObjectMapper mapper;

    @Autowired
    public PacienteServiceImpl(IPacienteRepository pacienteRepository, ObjectMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }


    @Override
    public void guardar(PacienteDTO p) throws ResourceNotFoundException{
        if (p == null) {
            throw new ResourceNotFoundException("Error al querer ingresar un paciente null");
        }
        Paciente paciente = mapper.convertValue(p, Paciente.class);
        pacienteRepository.save(paciente);
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
    public void actualizar(PacienteDTO p) {
        Paciente paciente = mapper.convertValue(p, Paciente.class);
        if (pacienteRepository.findById(p.getId()).isPresent()) {
            pacienteRepository.save(paciente);
        }
    }

    @Override
    public void eliminar(Integer id) {
        pacienteRepository.deleteById(id);
    }
}
