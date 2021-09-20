package com.proyectointegrador.clinicaOdontologica.dto;

import com.proyectointegrador.clinicaOdontologica.persistence.entities.Odontologo;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;

public class PacienteDTO implements Serializable {
    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDate fechaIngreso;

    public PacienteDTO() {
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public PacienteDTO(Paciente p) {
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
    }
}
