package com.proyectointegrador.clinicaOdontologica.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.model.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Odontologo;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IOdontologoRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServiceImpl implements IService<OdontologoDTO> {

    private final IOdontologoRepository odontologoRepository;
    private final ObjectMapper mapper;

    @Autowired
    public OdontologoServiceImpl(IOdontologoRepository odontologoRepository, ObjectMapper mapper) {
        this.odontologoRepository = odontologoRepository;
        this.mapper = mapper;
    }


    @Override
    public void guardar(OdontologoDTO o) throws ResourceNotFoundException {
        if (o == null) {
            throw new ResourceNotFoundException("Error al querer ingresar un odontologo null");
        }
        Odontologo odontologo = mapper.convertValue(o, Odontologo.class);
        odontologoRepository.save(odontologo);
    }

    @Override
    public OdontologoDTO buscarPorId(Integer id) throws ResourceNotFoundException{
        OdontologoDTO odontologoDTO = null;
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if (!odontologo.isPresent()) {
            throw new ResourceNotFoundException("No se ha encontrado a ningún odontólogo con id: " + id);
        } else {
            odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
        }
        return odontologoDTO;
    }

    @Override
    public List<OdontologoDTO> buscarTodos() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        List<OdontologoDTO> odontologosDTO = new ArrayList<>();

        for(Odontologo odontologo : odontologos){
            OdontologoDTO odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
            odontologosDTO.add(odontologoDTO);
        }
        return odontologosDTO;
    }

    @Override
    public void actualizar(OdontologoDTO o) {
        Odontologo odontologo = mapper.convertValue(o, Odontologo.class);
        if (odontologoRepository.findById(o.getId()).isPresent()) {
          odontologoRepository.save(odontologo);
        }
    }

    @Override
    public void eliminar(Integer id) {
        odontologoRepository.deleteById(id);
    }
}
