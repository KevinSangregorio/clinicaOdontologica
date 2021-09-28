package com.proyectointegrador.clinicaOdontologica.model;

import com.proyectointegrador.clinicaOdontologica.persistence.entities.Domicilio;
import lombok.Data;
import java.io.Serializable;

@Data
public class DomicilioDTO implements Serializable {
    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

}
