package com.proyectointegrador.clinicaOdontologica.persistence.repositories;

import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
}
