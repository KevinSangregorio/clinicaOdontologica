package com.proyectointegrador.clinicaOdontologica.service;

import java.util.List;

public interface IService<T> {
    T guardar(T t);
    T buscarPorId(Integer id);
    List<T> buscarTodos();
    T actualizar(T t);
    void eliminar(Integer id);
}
