package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.model.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.service.Impl.PacienteServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    final static Logger log = Logger.getLogger(PacienteController.class);

    @Autowired
    PacienteServiceImpl pacienteService;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody PacienteDTO p) throws ResourceNotFoundException {
        log.debug("Iniciando método 'registrar()' de paciente...");
        pacienteService.guardar(p);

        log.debug("Finalizando método 'registrar()' de paciente...");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Integer id) {
        log.debug("Iniciando método 'buscarPorId()' de paciente...");
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

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> buscarTodos(){
        log.debug("Buscando todos los pacientes...");
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody PacienteDTO p) {
        log.debug("Iniciando método 'actualizar' de paciente...");

        pacienteService.actualizar(p);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
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
