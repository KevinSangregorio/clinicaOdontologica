package com.proyectointegrador.clinicaOdontologica.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectointegrador.clinicaOdontologica.controller.OdontologoController;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.model.TurnoDTO;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Turno;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.ITurnoRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import com.proyectointegrador.clinicaOdontologica.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoServiceImpl implements ITurnoService<TurnoDTO> {

    final static Logger log = Logger.getLogger(OdontologoController.class);

    private OdontologoServiceImpl odontologoService;
    private PacienteServiceImpl pacienteService;
    private ITurnoRepository turnoRepository;
    private ObjectMapper mapper;

    @Autowired
    public TurnoServiceImpl(OdontologoServiceImpl odontologoService, PacienteServiceImpl pacienteService, ITurnoRepository turnoRepository, ObjectMapper mapper) {
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
        this.turnoRepository = turnoRepository;
        this.mapper = mapper;
    }


    @Override
    public TurnoDTO guardar(TurnoDTO t, Integer idPaciente, Integer idOdontologo) throws ResourceNotFoundException {
        log.debug("Ejecutando el guardado de un nuevo Turno");
        t.setPaciente(pacienteService.buscarPorId(idPaciente));
        t.setOdontologo(odontologoService.buscarPorId(idOdontologo));

        if (verificacionDeExistenciaDeTurno(t.getOdontologo().getId(),t.getPaciente().getId(),t.getFecha()).isEmpty()){
            Turno turno = mapper.convertValue(t, Turno.class);
            turnoRepository.save(turno);
            return t;
        }
        throw new ResourceNotFoundException("No se pudo guardar el turno.");
    }


    private List<TurnoDTO> verificacionDeExistenciaDeTurno(Integer idOdontologo, Integer idPaciente, LocalDateTime fecha){
        List<TurnoDTO> turnoDTOs = new ArrayList<>();
        for(Turno t: turnoRepository.findAll()) {
            if (t.getFecha().equals(fecha)) {
                if (t.getPaciente().getId().equals(idPaciente) || t.getOdontologo().getId().equals(idOdontologo)) {
                    TurnoDTO turnoDTO = mapper.convertValue(t, TurnoDTO.class);
                    turnoDTOs.add(turnoDTO);
                }
            }
        }
        return turnoDTOs;
    }

    @Override
    public TurnoDTO buscarPorId(Integer id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public List<TurnoDTO> buscarTodos() throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void actualizar(TurnoDTO turnoDTO) throws ResourceNotFoundException {

    }

    @Override
    public void eliminar(Integer id) throws ResourceNotFoundException {

    }
}
