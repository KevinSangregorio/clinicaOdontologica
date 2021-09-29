package com.proyectointegrador.clinicaOdontologica.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", nullable = false)
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();
}
