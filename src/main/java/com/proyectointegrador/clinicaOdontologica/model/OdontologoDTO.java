package com.proyectointegrador.clinicaOdontologica.model;

import com.proyectointegrador.clinicaOdontologica.persistence.entities.Odontologo;
import lombok.Data;
import java.io.Serializable;

@Data
public class OdontologoDTO implements Serializable {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer matricula;


    /*public OdontologoDTO(Odontologo o) {
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
    }*/
}
