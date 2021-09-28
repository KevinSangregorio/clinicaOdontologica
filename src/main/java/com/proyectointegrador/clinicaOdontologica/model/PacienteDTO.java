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

    /*public PacienteDTO(Paciente p) {
        this.id = p.getId();
        this.nombre = p.getNombre();
        this.apellido = p.getApellido();
        this.fechaIngreso = p.getFechaIngreso();
    }

    public Paciente toEntity(){
        Paciente entity = new Paciente();

        entity.setId(id);
        entity.setNombre(nombre);
        entity.setApellido(apellido);
        entity.setFechaIngreso(fechaIngreso);

        return entity;
    }*/
}
