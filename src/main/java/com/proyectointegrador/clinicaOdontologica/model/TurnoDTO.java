package com.proyectointegrador.clinicaOdontologica.model;

import com.proyectointegrador.clinicaOdontologica.persistence.entities.Turno;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class TurnoDTO {
    private Integer id;
    private LocalDateTime fecha;
    private LocalTime hora;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;

    /*public TurnoDTO(Turno t) {
        id = t.getId();
        fecha = t.getFecha();
        hora = t.getHora();
        paciente = new PacienteDTO(t.getPaciente());
        odontologo = new OdontologoDTO(t.getOdontologo());
    }*/
}
