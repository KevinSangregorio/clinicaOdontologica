package com.proyectointegrador.clinicaOdontologica.service.Impl;

import com.proyectointegrador.clinicaOdontologica.dto.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Odontologo;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IOdontologoRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoServiceImpl implements IService<OdontologoDTO> {

    final static Logger log = Logger.getLogger(OdontologoServiceImpl.class);

    private final IOdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoServiceImpl(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }


    @Override
    public OdontologoDTO guardar(OdontologoDTO o) {
        Odontologo oD = odontologoRepository.save(o.toEntity());
        log.debug(oD);
        //return new OdontologoDTO(odontologoRepository.save(o.toEntity()));
        return new OdontologoDTO(oD);
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

    public static void main(String[] args) {
        OdontologoDTO oDTO = new OdontologoDTO(new Odontologo("Kevin","Sangregorio",12737));



    }
}
