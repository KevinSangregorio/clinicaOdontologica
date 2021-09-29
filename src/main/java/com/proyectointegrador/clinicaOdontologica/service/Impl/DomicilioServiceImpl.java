package com.proyectointegrador.clinicaOdontologica.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.model.DomicilioDTO;
import com.proyectointegrador.clinicaOdontologica.model.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Domicilio;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IDomicilioRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioServiceImpl implements IService<DomicilioDTO> {


    private final IDomicilioRepository domicilioRepository;
    private final ObjectMapper mapper;

    @Autowired
    public DomicilioServiceImpl(IDomicilioRepository domicilioRepository, ObjectMapper mapper) {
        this.domicilioRepository = domicilioRepository;
        this.mapper = mapper;
    }


    @Override
    public void guardar(DomicilioDTO d) throws ResourceNotFoundException {
        if (d == null) {
            throw new ResourceNotFoundException("Error al querer ingresar un domicilio null");
        }
        Domicilio domicilio = mapper.convertValue(d, Domicilio.class);
        domicilioRepository.save(domicilio);
    }

    @Override
    public DomicilioDTO buscarPorId(Integer id) throws ResourceNotFoundException {
        DomicilioDTO domicilioDTO = null;
        Optional<Domicilio> domicilio = domicilioRepository.findById(id);
        if (!domicilio.isPresent()) {
            throw new ResourceNotFoundException("No se ha encontrado a ningún paciente con id: " + id);
        } else {
            domicilioDTO = mapper.convertValue(domicilio, DomicilioDTO.class);
        }
        return domicilioDTO;
    }

    @Override
    public List<DomicilioDTO> buscarTodos() {
        List<Domicilio> domicilios = domicilioRepository.findAll();
        List<DomicilioDTO> domiciliosDTO = new ArrayList<>();

        for (Domicilio domicilio : domicilios) {
            DomicilioDTO domicilioDTO = mapper.convertValue(domicilio, DomicilioDTO.class);
            domiciliosDTO.add(domicilioDTO);
        }
        return domiciliosDTO;
    }

    @Override
    public void actualizar(DomicilioDTO d) throws ResourceNotFoundException {
        Domicilio domicilio = mapper.convertValue(d, Domicilio.class);
        if (!domicilioRepository.findById(d.getId()).isPresent()) {
            throw new ResourceNotFoundException("No se encontró dicho odontólogo.");
        } else {
            domicilioRepository.save(domicilio);
        }
    }

    @Override
    public void eliminar(Integer id) {
        domicilioRepository.deleteById(id);
    }
}
