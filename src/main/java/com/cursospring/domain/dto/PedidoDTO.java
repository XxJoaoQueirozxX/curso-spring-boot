package com.cursospring.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO implements Serializable {



    private Long id;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;



}
