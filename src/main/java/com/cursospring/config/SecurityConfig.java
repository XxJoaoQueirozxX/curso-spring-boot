package com.cursospring.config;


import com.cursospring.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
            .userDetailsService(usuarioService)
            .passwordEncoder(passwordEncoder());

    }

    // Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/clientes/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/pedidos/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/produtos/**")
                    .hasRole("ADMIN")
                .antMatchers("/h2-console/**")
                    .permitAll()
            .and()
                .httpBasic();
    }
}
