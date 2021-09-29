package com.proyectointegrador.clinicaOdontologica.service;


import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.model.TurnoDTO;

import java.util.List;

public interface ITurnoService<T>{
    T guardar(T t) throws ResourceNotFoundException;
    //TurnoDTO guardar(TurnoDTO t) throws ResourceNotFoundException;
    T buscarPorId(Integer id) throws ResourceNotFoundException;
    List<T> buscarTodos() throws ResourceNotFoundException;
    void actualizar(T t) throws ResourceNotFoundException;
    void eliminar(Integer id) throws ResourceNotFoundException;
}
