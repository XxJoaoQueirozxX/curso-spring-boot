package com.cursospring.domain.entities;

import com.cursospring.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "data", updatable = false)
    private LocalDate dataPedido;

    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusPedido status;


    @ManyToOne(targetEntity = Cliente.class)
    @JoinColumn(name = "client_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> items = new ArrayList<>();


    public Pedido() {
    }

    public Pedido(Long id, LocalDate dataPedido, BigDecimal total, Cliente cliente, StatusPedido status) {
        this.id = id;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.total = total;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", total=" + total +
                ", status=" + status +
                '}';
    }
}
