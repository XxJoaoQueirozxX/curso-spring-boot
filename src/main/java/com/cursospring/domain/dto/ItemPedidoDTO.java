package com.cursospring.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ItemPedidoDTO {
    private Long produto;
    private Integer quantidade;

    public ItemPedidoDTO() {
    }

    public ItemPedidoDTO(Long produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getProduto() {
        return produto;
    }

    public void setProduto(Long produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
