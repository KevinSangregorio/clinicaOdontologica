package com.proyectointegrador.clinicaOdontologica.model;

import com.proyectointegrador.clinicaOdontologica.persistence.entities.Turno;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class TurnoDTO {
    private Integer id;
    private LocalDateTime fecha;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;

}
