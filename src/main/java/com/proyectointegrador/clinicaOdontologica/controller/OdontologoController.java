package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.model.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.service.Impl.OdontologoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import org.apache.log4j.Logger;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    final static Logger log = Logger.getLogger(OdontologoController.class);

    @Autowired
    OdontologoServiceImpl odontologoService;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody OdontologoDTO o) throws ResourceNotFoundException {
        log.debug("Iniciando método 'registrar()' de odontólogo...");
        odontologoService.guardar(o);

        log.debug("Finalizando método 'registrar()' de odontólogo...");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        log.debug("Iniciando método 'buscarPorId()' de odontólogo...");
        OdontologoDTO respuesta = odontologoService.buscarPorId(id);
        log.debug("Finalizando método 'buscarPorId()' de odontólogo...");
        return ResponseEntity.ok().body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Collection<OdontologoDTO>> buscarTodos(){
        log.debug("Buscando todos los odontólogos...");
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody OdontologoDTO o) {
        log.debug("Iniciando método 'actualizar()' de odontólogo...");
        odontologoService.actualizar(o);
        log.debug("Finalizando método 'actualizar()' de odontólogo...");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        log.debug("Iniciando método 'eliminar' de odontólogo...");
        odontologoService.eliminar(id);
        return ResponseEntity.ok("Odontólogo con id " + id + " eliminado.");
    }
}
