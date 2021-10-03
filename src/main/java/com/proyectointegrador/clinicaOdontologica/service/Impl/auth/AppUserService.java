package com.proyectointegrador.clinicaOdontologica.service.Impl.auth;


import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.auth.AppUser;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    public AppUser crear(AppUser appUser) throws ResourceNotFoundException {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
                appUser.setPassword(hashedPassword);
                return userRepository.save(appUser);
    }

    public List<AppUser> buscarTodos() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).get();
    }
}
