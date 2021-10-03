package com.proyectointegrador.clinicaOdontologica.service.Impl;

import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.model.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IOdontologoRepository;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
class OdontologoServiceImplTest {
    @Autowired
    private OdontologoServiceImpl odontologoService;

    @Autowired
    private IOdontologoRepository repository;

    private OdontologoDTO o = new OdontologoDTO();

    public void cargarDataSet () throws ResourceNotFoundException {
        o.setNombre("Simon");
        o.setApellido("Simonian");
        o.setMatricula(1234);
        odontologoService.guardar(o);
    }

    @Test
    public void borrarOdontologo() throws Exception {
        //Dado
        cargarDataSet();

        //Cuando
        odontologoService.eliminar(1);

        //Entonces
        List<OdontologoDTO> respuestaEsperada =  odontologoService.buscarTodos();
        assertTrue(respuestaEsperada.size() == 0);

    }

    @Test
    public void crearOdontologo() throws ResourceNotFoundException {
        //Dado
        Integer totalOdontologosAntesCreacion = repository.findAll().size();
        Integer totalOdontologosEsperado = totalOdontologosAntesCreacion+1;


        //Cuando
        cargarDataSet();
        Integer totalOdontologosDespuesCreacion = repository.findAll().size();


        //Entonces
        assertEquals(totalOdontologosEsperado, totalOdontologosDespuesCreacion);
    }

    @Test
    public void actualizarOdontologo() throws ResourceNotFoundException {
        //Dado
        cargarDataSet();
        OdontologoDTO primerOdontologo = odontologoService.buscarPorId(1);

        //Cuando
        OdontologoDTO o = new OdontologoDTO();
        o.setId(1);
        o.setMatricula(0000);
        odontologoService.actualizar(o);
        OdontologoDTO segundoOdontologo = odontologoService.buscarPorId(1);

        //Entonces
        assertFalse(primerOdontologo.equals(segundoOdontologo));
    }

    @Test
    public void traerTodos() throws ResourceNotFoundException {
        //Dado
        cargarDataSet();
        cargarDataSet();
        cargarDataSet();
        int respuestaEsperada = 3;

        //Cuando
        List<OdontologoDTO> odontologoDTOS = odontologoService.buscarTodos();

        //Entonces
        assertEquals(respuestaEsperada, odontologoDTOS.size());
    }
}