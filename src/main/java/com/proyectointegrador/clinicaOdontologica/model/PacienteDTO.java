package com.proyectointegrador.clinicaOdontologica.model;

import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@Data
public class PacienteDTO implements Serializable {
    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDate fechaIngreso;
    private DomicilioDTO domicilio;

}
