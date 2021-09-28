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

}
