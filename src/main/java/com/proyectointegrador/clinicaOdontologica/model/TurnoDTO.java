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

    /*public TurnoDTO(Turno t) {
        this.id = t.getId();
        this.fecha = t.getFecha();
        this.paciente = t.getPaciente();
        this.odontologo = (OdontologoDTO)t.getOdontologo();
    }

    public Turno toEntity(){
        Turno entity = new Turno();

        entity.setId(id);
        entity.setFecha(fecha);
        entity.setPaciente(paciente);
        entity.setOdontologo(odontologo);

        return entity;
    }*/

}
