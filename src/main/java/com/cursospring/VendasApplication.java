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

            System.out.println("Salvado ....");
            Cliente cliente = new Cliente();
            cliente.setNome("João");
            repository.save(cliente);

            cliente = new Cliente();
            cliente.setNome("Carlos");
            repository.save(cliente);


            System.out.println("Listando ...");
            List<Cliente> clientes = repository.findAll();
            clientes.forEach(System.out::println);


            System.out.println("Atualizando ...");
            clientes.forEach(c ->{
                c.setNome(c.getNome() + "(Atualizado)");
                repository.update(c);
            });


            clientes = repository.findAll();
            clientes.forEach(System.out::println);


            System.out.println("Buscando por nome Car ...");
            repository.findByName("Car").forEach(System.out::println);

            System.out.println("Deletando clientes ....");
            clientes.forEach(repository::delete);


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
