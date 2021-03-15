package com.cursospring;


import com.cursospring.domain.entities.Cliente;
import com.cursospring.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository repository){
        return args -> {

            System.out.println("Salvado ....");
            Cliente c1 = new Cliente(null, "João");
            Cliente c2 = new Cliente(null, "Carlos");
            repository.saveAll(Arrays.asList(c1, c2));

            System.out.println("\nListando ...");
            List<Cliente> clientes = repository.findAll();
            clientes.forEach(System.out::println);


            System.out.println("\nAtualizando ...");
            clientes.forEach(c ->{
                c.setNome(c.getNome() + "(Atualizado)");
                repository.save(c);
            });

            System.out.println("\nListando atualizações ....");
            clientes = repository.findAll();
            clientes.forEach(System.out::println);

            System.out.println("Buscando por nome car ...");
            repository.findByNomeLike("%Car%").forEach(System.out::println);

            System.out.println("\nDeletando clientes ....");
            clientes.forEach(repository::delete);


            System.out.println("\nListando ...");
            clientes = repository.findAll();

            if(clientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado");
            }else{
                clientes.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
