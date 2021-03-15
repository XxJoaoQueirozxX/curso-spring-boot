package com.cursospring;


import com.cursospring.domain.entities.Cliente;
import com.cursospring.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository repository){
        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("João");
            repository.save(cliente);

            cliente = new Cliente();
            cliente.setNome("Carlos");
            repository.save(cliente);


            List<Cliente> clientes = repository.findAll();
            clientes.forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
