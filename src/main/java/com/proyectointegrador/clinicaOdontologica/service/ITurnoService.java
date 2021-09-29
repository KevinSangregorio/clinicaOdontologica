package com.proyectointegrador.clinicaOdontologica.service;


import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService<T>{
    T guardar(T t, Integer idO, Integer idP) throws ResourceNotFoundException;
    T buscarPorId(Integer id) throws ResourceNotFoundException;
    List<T> buscarTodos() throws ResourceNotFoundException;
    void actualizar(T t) throws ResourceNotFoundException;
    void eliminar(Integer id) throws ResourceNotFoundException;
}
