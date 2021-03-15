package com.cursospring.config;

import com.cursospring.config.annotations.Development;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@Development
public class TestConfiguration {

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("Rodando a config de desenvolvimento");
        };
    }
}
