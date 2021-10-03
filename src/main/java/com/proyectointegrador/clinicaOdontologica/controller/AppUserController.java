package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.auth.AppUser;
import com.proyectointegrador.clinicaOdontologica.service.Impl.auth.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AppUserController {

    private final AppUserService userService;

    @Autowired
    public AppUserController(AppUserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<AppUser> crear(@RequestBody AppUser user) throws ResourceNotFoundException {
            AppUser usuario = userService.crear(user);
            return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<List<?>> buscarTodos() {
        return ResponseEntity.ok(userService.buscarTodos());
    }

}
