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


    /*public DomicilioDTO(Domicilio d) {
        id = d.getId();
        calle = d.getCalle();
        numero = d.getNumero();
        localidad = d.getLocalidad();
        provincia = d.getProvincia();
    }

    public Domicilio toEntity() {
        Domicilio entity = new Domicilio();

        entity.setId(id);
        entity.setCalle(calle);
        entity.setLocalidad(localidad);
        entity.setProvincia(provincia);
        entity.setNumero(numero);

        return entity;
    }*/
}
