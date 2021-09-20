package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.dto.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.dto.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.service.Impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteServiceImpl pacienteService;

    @PostMapping("/guardar")
    public ResponseEntity<PacienteDTO> registrarPaciente(@RequestBody PacienteDTO p) {
        return ResponseEntity.ok(pacienteService.guardar(p));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Integer id) {
        ResponseEntity<PacienteDTO> response = null;

        if (pacienteService.buscarPorId(id) != null) {
            response = ResponseEntity.ok(pacienteService.buscarPorId(id));
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<PacienteDTO>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDTO> actualizar(@RequestBody PacienteDTO p) {
        ResponseEntity<PacienteDTO> response = null;
        if (pacienteService.actualizar(p) != null) {
            response = ResponseEntity.ok(pacienteService.actualizar(p));
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;

        if (pacienteService.buscarPorId(id) != null) {
            pacienteService.eliminar(id);
            response = ResponseEntity.ok("Paciente con id " + id + " eliminado.");
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }
}
