package com.proyectointegrador.clinicaOdontologica.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
    private Integer id;

    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaIngreso;

    @ManyToOne(cascade = CascadeType.ALL, optional = false) //La propiedad optional queda en false porque un paciente siempre debe tener un domicilio
    @JoinColumn(name = "domicilio_id", nullable = false)
    private Domicilio domicilio;
}
