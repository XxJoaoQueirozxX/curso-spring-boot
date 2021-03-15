package com.cursospring.config;

import com.cursospring.domain.entities.Cliente;
import com.cursospring.domain.entities.Pedido;
import com.cursospring.domain.repositories.ClienteRepository;
import com.cursospring.domain.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfiguration implements CommandLineRunner {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        Cliente c1 = new Cliente(null, "João");
        Cliente c2 = new Cliente(null, "Carlos");
        clienteRepository.saveAll(Arrays.asList(c1, c2));


        Pedido p = new Pedido(null, LocalDate.now(), BigDecimal.valueOf(0L), c1);
        pedidoRepository.save(p);

        c1.getPedidos().add(p);
        clienteRepository.save(c1);
    }
}
