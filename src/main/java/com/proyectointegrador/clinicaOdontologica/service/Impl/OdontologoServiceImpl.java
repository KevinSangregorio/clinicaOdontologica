package com.proyectointegrador.clinicaOdontologica.service.Impl;

import com.proyectointegrador.clinicaOdontologica.model.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Odontologo;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IOdontologoRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoServiceImpl implements IService<OdontologoDTO> {

    private final IOdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoServiceImpl(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }


    @Override
    public OdontologoDTO guardar(OdontologoDTO o) {
        return new OdontologoDTO(odontologoRepository.save(o.toEntity()));
    }

    @Override
    public OdontologoDTO buscarPorId(Integer id) {
        return new OdontologoDTO(odontologoRepository.getById(id));
    }

    @Override
    public List<OdontologoDTO> buscarTodos() {
        List<OdontologoDTO> odontologos = new ArrayList<>();

        for(Odontologo o : odontologoRepository.findAll()){
            odontologos.add(new OdontologoDTO(o));
        }
        return odontologos;
    }

    @Override
    public OdontologoDTO actualizar(OdontologoDTO o) {
        Odontologo actualizado = null;
        if (odontologoRepository.findById(o.getId()).isPresent()) {
            actualizado = odontologoRepository.save(o.toEntity());
        }
        return new OdontologoDTO(actualizado);
    }

    @Override
    public void eliminar(Integer id) {
        odontologoRepository.deleteById(id);
    }
}
