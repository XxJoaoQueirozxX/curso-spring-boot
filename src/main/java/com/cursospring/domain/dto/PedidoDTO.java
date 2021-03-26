package com.cursospring.domain.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO implements Serializable {

    private Long id;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;



}
