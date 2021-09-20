package com.proyectointegrador.clinicaOdontologica.persistence.repositories;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo,Integer> {
}
