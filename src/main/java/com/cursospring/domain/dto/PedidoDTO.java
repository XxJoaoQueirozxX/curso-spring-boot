package com.cursospring.domain.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;



public class PedidoDTO{

    private Long cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;

    public PedidoDTO() {
    }

    public PedidoDTO(Long cliente, BigDecimal total, List<ItemPedidoDTO> items) {
        this.cliente = cliente;
        this.total = total;
        this.items = items;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ItemPedidoDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoDTO> items) {
        this.items = items;
    }
}
