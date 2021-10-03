package com.proyectointegrador.clinicaOdontologica.service.Impl.auth;


import com.proyectointegrador.clinicaOdontologica.persistence.entities.auth.AppUser;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.auth.AppUserRole;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private IUserRepository userRepository;

    @Autowired
    public DataLoader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("admin");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("user");
        BCryptPasswordEncoder passwordEncoder3 = new BCryptPasswordEncoder();
        String hashedPassword3 = passwordEncoder2.encode("moderador");
        userRepository.save(new AppUser("Admin", "Admin", "admin@digital.com", hashedPassword, AppUserRole.ADMIN));
        userRepository.save(new AppUser("User", "User", "user@digital.com", hashedPassword2, AppUserRole.USER));
        userRepository.save(new AppUser("Moderador", "Moderador", "moderador@digital.com", hashedPassword3, AppUserRole.MODERADOR));
    }
}
