package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.dto.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.service.Impl.PacienteServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    final static Logger log = Logger.getLogger(PacienteController.class);

    @Autowired
    PacienteServiceImpl pacienteService;

    @PostMapping("/guardar")
    public ResponseEntity<PacienteDTO> registrarPaciente(@RequestBody PacienteDTO p) {
        PacienteDTO response = pacienteService.guardar(p);
        log.debug("Registrando el domicilio con id: " + response.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Integer id) {
        log.debug("Iniciando método 'buscar por id' de paciente...");
        ResponseEntity<PacienteDTO> response = null;

        if (pacienteService.buscarPorId(id) != null) {
            response = ResponseEntity.ok(pacienteService.buscarPorId(id));
            log.info("Paciente con id: " + id + " encontrado!");
        } else {
            response = ResponseEntity.notFound().build();
            log.info("Paciente con id: " + id + " no encontrado!");
        }

        return response;
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<PacienteDTO>> buscarTodos(){
        log.debug("Buscando todos los pacientes...");
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDTO> actualizar(@RequestBody PacienteDTO p) {
        log.debug("Iniciando método 'actualizar' de paciente...");
        ResponseEntity<PacienteDTO> response = null;
        if (pacienteService.actualizar(p) != null) {
            response = ResponseEntity.ok(pacienteService.actualizar(p));
            log.info("Paciente con id: " + p.getId() + " actualizado!");
        } else {
            response = ResponseEntity.notFound().build();
            log.info("Paciente con id: " + p.getId() + " no encontrado!");
        }

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        log.debug("Iniciando método 'eliminar' de paciente...");
        ResponseEntity<String> response = null;

        if (pacienteService.buscarPorId(id) != null) {
            pacienteService.eliminar(id);
            response = ResponseEntity.ok("Paciente con id " + id + " eliminado.");
            log.info("Paciente con id: " + id + " eliminado!");
        } else {
            response = ResponseEntity.notFound().build();
            log.info("Paciente con id: " + id + " no encontrado!");
        }

        return response;
    }
}
