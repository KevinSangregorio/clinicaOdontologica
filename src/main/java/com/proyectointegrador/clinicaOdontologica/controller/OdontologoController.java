package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.dto.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.service.Impl.OdontologoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoServiceImpl odontologoService;

    @PostMapping("/guardar")
    public ResponseEntity<OdontologoDTO> registrarOdontologo(@RequestBody OdontologoDTO o) {
        return ResponseEntity.ok(odontologoService.guardar(o));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<OdontologoDTO> buscarPorId(@PathVariable Integer id) {
        ResponseEntity<OdontologoDTO> response = null;

        if (odontologoService.buscarPorId(id) != null) {
            response = ResponseEntity.ok(odontologoService.buscarPorId(id));
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<OdontologoDTO>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDTO> actualizar(@RequestBody OdontologoDTO o) {
        ResponseEntity<OdontologoDTO> response = null;
        if (odontologoService.actualizar(o) != null) {
            response = ResponseEntity.ok(odontologoService.actualizar(o));
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;

        if (odontologoService.buscarPorId(id) != null) {
            odontologoService.eliminar(id);
            response = ResponseEntity.ok("Odontólogo con id " + id + " eliminado.");
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }




}
