package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.dto.DomicilioDTO;
import com.proyectointegrador.clinicaOdontologica.service.Impl.DomicilioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.log4j.Logger;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    final static Logger log = Logger.getLogger(DomicilioController.class);

    @Autowired
    DomicilioServiceImpl domicilioService;

    @PostMapping("/guardar")
    public ResponseEntity<DomicilioDTO> registrarDomicilio(@RequestBody DomicilioDTO d) {
        log.debug("Registrando el domicilio con id: " + d.getId());
        return ResponseEntity.ok(domicilioService.guardar(d));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<DomicilioDTO> buscarPorId(@PathVariable Integer id) {
        log.debug("Buscando el domicilio con id: " + id);
        ResponseEntity<DomicilioDTO> response = null;

        if (domicilioService.buscarPorId(id) != null) {
            response = ResponseEntity.ok(domicilioService.buscarPorId(id));
            log.info("Domicilio con id: " + id + " encontrado!");
        } else {
            response = ResponseEntity.notFound().build();
            log.info("Domicilio con id: " + id + " no encontrado!");
        }

        return response;
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<DomicilioDTO>> buscarTodos(){
        log.debug("Buscando todos los domicilios...");
        return ResponseEntity.ok(domicilioService.buscarTodos());
    }

    @PutMapping("/actualizar")
    public ResponseEntity<DomicilioDTO> actualizar(@RequestBody DomicilioDTO d) {
        log.debug("Actualizando el domicilio con id: " + d.getId());
        ResponseEntity<DomicilioDTO> response = null;
        if (domicilioService.actualizar(d) != null) {
            response = ResponseEntity.ok(domicilioService.actualizar(d));
            log.info("Domicilio con id: " + d.getId() + " actualizado!");
        } else {
            response = ResponseEntity.notFound().build();
            log.info("Domicilio con id: " + d.getId() + " no encontrado!");
        }

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        log.debug("Eliminando domicilio con id: " + id);
        ResponseEntity<String> response = null;

        if (domicilioService.buscarPorId(id) != null) {
            domicilioService.eliminar(id);
            response = ResponseEntity.ok("Domicilio con id " + id + " eliminado.");
            log.info("Domicilio con id: " + id + " eliminado!");
        } else {
            response = ResponseEntity.notFound().build();
            log.info("Domicilio con id: " + id + " no encontrado!");
        }

        return response;
    }
}
