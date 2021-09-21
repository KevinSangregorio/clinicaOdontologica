package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.dto.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.service.Impl.OdontologoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.log4j.Logger;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    final static Logger log = Logger.getLogger(OdontologoController.class);

    @Autowired
    OdontologoServiceImpl odontologoService;

    @PostMapping("/guardar")
    public ResponseEntity<OdontologoDTO> registrarOdontologo(@RequestBody OdontologoDTO o) {
        OdontologoDTO response = odontologoService.guardar(o);
        log.debug("Registrando el odontólogo con id: " + response.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<OdontologoDTO> buscarPorId(@PathVariable Integer id) {
        log.debug("Iniciando método 'buscar por id' de odontólogo...");
        ResponseEntity<OdontologoDTO> response = null;

        if (odontologoService.buscarPorId(id) != null) {
            response = ResponseEntity.ok(odontologoService.buscarPorId(id));
            log.info("Odontólogo con id: " + id + " encontrado!");
        } else {
            response = ResponseEntity.notFound().build();
            log.info("Odontólogo con id: " + id + " no encontrado!");
        }

        return response;
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<OdontologoDTO>> buscarTodos(){
        log.debug("Buscando todos los odontólogos...");
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDTO> actualizar(@RequestBody OdontologoDTO o) {
        log.debug("Iniciando método 'actualizar' de odontólogo...");
        ResponseEntity<OdontologoDTO> response = null;
        if (odontologoService.actualizar(o) != null) {
            response = ResponseEntity.ok(odontologoService.actualizar(o));
            log.info("Odontólogo con id: " + o.getId() + " actualizado!");
        } else {
            response = ResponseEntity.notFound().build();
            log.info("Odontólogo con id: " + o.getId() + " no encontrado!");
        }

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        log.debug("Iniciando método 'eliminar' de odontólogo...");
        ResponseEntity<String> response = null;

        if (odontologoService.buscarPorId(id) != null) {
            odontologoService.eliminar(id);
            response = ResponseEntity.ok("Odontólogo con id " + id + " eliminado.");
            log.info("Odontólogo con id: " + id + " eliminado!");
        } else {
            response = ResponseEntity.notFound().build();
            log.info("Odontólogo con id: " + id + " no encontrado!");
        }

        return response;
    }
}
