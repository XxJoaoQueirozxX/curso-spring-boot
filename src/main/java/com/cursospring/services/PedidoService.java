package com.cursospring.services;

import com.cursospring.domain.dto.ItemPedidoDTO;
import com.cursospring.domain.dto.PedidoDTO;
import com.cursospring.domain.entities.Cliente;
import com.cursospring.domain.entities.ItemPedido;
import com.cursospring.domain.entities.Pedido;
import com.cursospring.repositories.ItemPedidoRepository;
import com.cursospring.repositories.PedidoRepository;
import com.cursospring.services.exceptions.NotFoundException;
import com.cursospring.services.exceptions.PedidoSemItemsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


    @Transactional
    public Pedido insert(PedidoDTO dto) {
        Cliente c = clienteService.findById(dto.getCliente());

        Pedido pedido = new Pedido();
        pedido.setCliente(c);
        pedido.setTotal(dto.getTotal());

        List<ItemPedido> items = converterItems(pedido, dto.getItems());
        pedido = repository.save(pedido);
        itemPedidoRepository.saveAll(items);
        pedido.getItems().addAll(items);
        return pedido;
    }

    public Pedido findById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("nenhum pedido encontrado para o ID: " + id));
    }


    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if (items.isEmpty()){
            throw new PedidoSemItemsException("Não é possivel cadastrar um pedido sem items");
        }

        return items.stream().map( dto -> {
            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produtoService.findById(dto.getProduto()));
            item.setQuantidade(dto.getQuantidade());
            return item;
        }).collect(Collectors.toList());
    }
}
