package com.cursospring.config;

import com.cursospring.domain.entities.Cliente;
import com.cursospring.domain.entities.ItemPedido;
import com.cursospring.domain.entities.Pedido;
import com.cursospring.domain.entities.Produto;
import com.cursospring.domain.repositories.ClienteRepository;
import com.cursospring.domain.repositories.ItemPedidoRepository;
import com.cursospring.domain.repositories.PedidoRepository;
import com.cursospring.domain.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
@Profile("test")
public class TestConfiguration implements CommandLineRunner {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


    @Override
    public void run(String... args) throws Exception {
//      Clientes

        Cliente c1 = new Cliente(null, "João");
        Cliente c2 = new Cliente(null, "Carlos");
        clienteRepository.saveAll(Arrays.asList(c1, c2));


//      Pedidos
        Pedido p1 = new Pedido(null, LocalDate.now(), BigDecimal.valueOf(0L), c1);
        Pedido p2 = new Pedido(null, LocalDate.now(), BigDecimal.valueOf(0L), c1);

        pedidoRepository.saveAll(Arrays.asList(p1, p2));
        c1.getPedidos().addAll(Arrays.asList(p1, p2));
        clienteRepository.save(c1);


//      Produtos
        Produto prod1 = new Produto(null, "Fone Redmi Air Dots", BigDecimal.valueOf(129.90));
        Produto prod2 = new Produto(null, "Mi Band 4", BigDecimal.valueOf(179.99));
        Produto prod3 = new Produto(null, "Acer Nitro 5", BigDecimal.valueOf(5499.99));
        produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

//       Items do pedido
        ItemPedido item1 = new ItemPedido(null, 2, p1, prod1);
        ItemPedido item2 = new ItemPedido(null, 1, p1, prod3);
        ItemPedido item3 = new ItemPedido(null, 5, p2, prod2);
        ItemPedido item4 = new ItemPedido(null, 3, p2, prod1);
        itemPedidoRepository.saveAll(Arrays.asList(item1, item2, item3, item4));

        p1.getItems().addAll(Arrays.asList(item1, item2));
        p2.getItems().addAll(Arrays.asList(item3, item4));

        p1.setTotal(
                p1.getItems()
                .stream()
                .map(i -> i.getProduto().getPreco().multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        p2.setTotal(
            p2.getItems()
            .stream()
            .map(i -> i.getProduto().getPreco().multiply(BigDecimal.valueOf(i.getQuantidade())))
            .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        pedidoRepository.saveAll(Arrays.asList(p1, p2));


        System.out.println("\n\n");


        Cliente cli = clienteRepository.findClienteFetchPedidos(1L);
        System.out.println(cli);


        List<Pedido> pedidos = pedidoRepository.findByCliente(c1);
        System.out.println(pedidos);

    }
}
