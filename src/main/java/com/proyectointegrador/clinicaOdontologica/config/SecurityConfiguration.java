package com.proyectointegrador.clinicaOdontologica.config;

import com.proyectointegrador.clinicaOdontologica.persistence.entities.auth.AppUserRole;
import com.proyectointegrador.clinicaOdontologica.service.Impl.auth.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfiguration(AppUserService appUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserService = appUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public SecurityConfiguration(boolean disableDefaults, BCryptPasswordEncoder bCryptPasswordEncoder, AppUserService appUserService) {
        super(disableDefaults);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.appUserService = appUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/turnos/**").hasAnyAuthority(AppUserRole.USER.name(), AppUserRole.ADMIN.name())
                .antMatchers("/odontologos/**", "/pacientes/**").hasAuthority(AppUserRole.ADMIN.name())
                .antMatchers("/user").hasAuthority(AppUserRole.MODERADOR.name())
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }

}
