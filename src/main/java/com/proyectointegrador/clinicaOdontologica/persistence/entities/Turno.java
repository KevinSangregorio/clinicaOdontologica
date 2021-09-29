package com.proyectointegrador.clinicaOdontologica.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    private Integer id;

    private LocalDateTime fecha;
    private LocalTime hora;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;

}
