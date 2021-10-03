package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.model.TurnoDTO;
import com.proyectointegrador.clinicaOdontologica.service.Impl.TurnoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    final static Logger log = Logger.getLogger(PacienteController.class);

    @Autowired
    TurnoServiceImpl turnoService;

    @PostMapping
    public ResponseEntity<?> registrar (@RequestBody TurnoDTO t) throws ResourceNotFoundException {
        log.debug("Iniciando método 'registrar()' de turno...");
        turnoService.guardar(t);

        log.debug("Finalizando método 'registrar()' de turno...");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        log.debug("Iniciando método 'buscarPorId()' de turno...");
        TurnoDTO respuesta = turnoService.buscarPorId(id);
        log.debug("Finalizando método 'buscarPorId()' de turno...");
        return ResponseEntity.ok().body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodos() throws ResourceNotFoundException {
        log.debug("Buscando todos los turnos...");
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @GetMapping("/proximaSemana")
    public ResponseEntity<List<?>> buscarProximaSemana() throws ResourceNotFoundException {
        log.debug("Buscando todos los turnos de la próxima semana...");
        return ResponseEntity.ok(turnoService.buscarTurnosProxSemana());
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody TurnoDTO p) throws ResourceNotFoundException {
        log.debug("Iniciando método 'actualizar()' de turno...");
        turnoService.actualizar(p);
        log.debug("Finalizando método 'actualizar()' de turno...");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) throws ResourceNotFoundException{
        log.debug("Iniciando método 'eliminar()' de turno...");
        turnoService.eliminar(id);
        return ResponseEntity.ok("Turno con id " + id + " eliminado.");
    }

}
