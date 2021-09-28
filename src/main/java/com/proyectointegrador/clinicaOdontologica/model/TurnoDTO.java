package com.proyectointegrador.clinicaOdontologica.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Turno;

import java.time.LocalDateTime;

public class TurnoDTO {
    private Integer id;
    private LocalDateTime fecha;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;

}
