package com.proyectointegrador.clinicaOdontologica.service.Impl;

import com.proyectointegrador.clinicaOdontologica.dto.DomicilioDTO;
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

    @Autowired
    public DomicilioServiceImpl(IDomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }


    @Override
    public DomicilioDTO guardar(DomicilioDTO d) {
        return new DomicilioDTO(domicilioRepository.save(d.toEntity()));
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
