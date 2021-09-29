package com.proyectointegrador.clinicaOdontologica.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectointegrador.clinicaOdontologica.controller.OdontologoController;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.model.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.model.TurnoDTO;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Turno;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.ITurnoRepository;
import com.proyectointegrador.clinicaOdontologica.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public TurnoDTO guardar(TurnoDTO t) throws ResourceNotFoundException {
        log.debug("Ejecutando el guardado de un nuevo Turno");
        t.setPaciente(pacienteService.buscarPorId(t.getPaciente().getId()));
        t.setOdontologo(odontologoService.buscarPorId(t.getOdontologo().getId()));

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
        TurnoDTO turnoDTO = null;
        Optional<Turno> turno = turnoRepository.findById(id);
        if (!turno.isPresent()) {
            throw new ResourceNotFoundException("No se ha encontrado a ningún turno con id: " + id);
        } else {
            turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
        }
        return turnoDTO;
    }

    @Override
    public List<TurnoDTO> buscarTodos() throws ResourceNotFoundException {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDTO> turnosDTO = new ArrayList<>();

        for (Turno turno : turnos) {
            TurnoDTO turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
            turnosDTO.add(turnoDTO);
        }
        return turnosDTO;
    }

    @Override
    public void actualizar(TurnoDTO t) throws ResourceNotFoundException {
        Turno turno = mapper.convertValue(t, Turno.class);
        if (t.getId() == null) {
            throw new ResourceNotFoundException("Se necesita un id distinto de nulo para poder modificar el turno");
        } else if (!turnoRepository.findById(t.getId()).isPresent()) {
            throw new ResourceNotFoundException("No se encontró dicho turno.");
        } else if (turnoRepository.findById(t.getId()).isPresent()) {
            turnoRepository.save(turno);
        }
    }

    @Override
    public void eliminar(Integer id) throws ResourceNotFoundException {
        if (!turnoRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("No se encontró dicho turno.");
        } else {
            turnoRepository.deleteById(id);
        }
    }
}
