package com.proyectointegrador.clinicaOdontologica.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.model.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IPacienteRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public PacienteDTO buscarPorId(Integer id) throws ResourceNotFoundException{
        PacienteDTO pacienteDTO = null;
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (!paciente.isPresent()) {
          throw new ResourceNotFoundException("No se ha encontrado a ningún paciente con id: " + id);
        } else {
            pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
        }
        return pacienteDTO;
    }

    @Override
    public List<PacienteDTO> buscarTodos() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteDTO> pacientesDTO = new ArrayList<>();

        for (Paciente paciente : pacientes) {
            PacienteDTO pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
            pacientesDTO.add(pacienteDTO);
        }
        return pacientesDTO;
    }

    @Override
    public void actualizar(PacienteDTO p) throws ResourceNotFoundException{
        Paciente paciente = mapper.convertValue(p, Paciente.class);
        if (p.getId() == null) {
            throw new ResourceNotFoundException("Se necesita un id distinto de nulo para poder modificar el paciente");
        } else if (!pacienteRepository.findById(p.getId()).isPresent()) {
            throw new ResourceNotFoundException("No se encontró dicho paciente.");
        } else if (pacienteRepository.findById(p.getId()).isPresent()) {
            pacienteRepository.save(paciente);
        }
    }

    @Override
    public void eliminar(Integer id) throws ResourceNotFoundException{
        if (!pacienteRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("No se encontró dicho paciente.");
        } else {
            pacienteRepository.deleteById(id);
        }
    }
}
