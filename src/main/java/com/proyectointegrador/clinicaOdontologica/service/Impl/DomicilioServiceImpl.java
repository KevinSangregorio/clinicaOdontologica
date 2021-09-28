package com.proyectointegrador.clinicaOdontologica.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.model.DomicilioDTO;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Domicilio;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IDomicilioRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public DomicilioDTO buscarPorId(Integer id) {
        return new DomicilioDTO(domicilioRepository.getById(id));
    }

    @Override
    public List<DomicilioDTO> buscarTodos() {
        List<DomicilioDTO> domicilios = new ArrayList<>();

        for (Domicilio d : domicilioRepository.findAll()) {
            domicilios.add(new DomicilioDTO(d));
        }
        return domicilios;
    }

    @Override
    public DomicilioDTO actualizar(DomicilioDTO d) {
        Domicilio actualizado = null;
        if (domicilioRepository.findById(d.getId()).isPresent()) {
            actualizado = domicilioRepository.save(d.toEntity());
        }
        return new DomicilioDTO(actualizado);
    }

    @Override
    public void eliminar(Integer id) {
        domicilioRepository.deleteById(id);
    }
}
