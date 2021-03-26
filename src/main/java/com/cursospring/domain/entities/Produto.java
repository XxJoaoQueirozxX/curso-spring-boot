package com.cursospring.domain.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao", nullable = false, length = 200)
    @NotEmpty(message = "O campo descricao é obrigatótio")
    private String descricao;

    @Column(name = "preco_unitario", nullable = false)
    @NotNull(message = "O campo preco é obrigatório")
    private BigDecimal preco;

}
