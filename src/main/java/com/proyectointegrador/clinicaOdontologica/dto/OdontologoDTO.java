package com.proyectointegrador.clinicaOdontologica.dto;

import com.proyectointegrador.clinicaOdontologica.persistence.entities.Odontologo;

import java.io.Serializable;

public class OdontologoDTO implements Serializable {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer matricula;

    public OdontologoDTO() {
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

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public OdontologoDTO(Odontologo o) {
        this.id = o.getId();
        this.nombre = o.getNombre();
        this.apellido = o.getApellido();
        this.matricula = o.getMatricula();
    }

    public Odontologo toEntity(){
        Odontologo entity = new Odontologo();

        entity.setId(id);
        entity.setApellido(apellido);
        entity.setNombre(nombre);
        entity.setMatricula(matricula);

        return entity;
    }
}
