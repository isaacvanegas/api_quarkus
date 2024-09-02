package org.api.entity.catalogo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "producto", schema = "catalogo")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_id_gen")
    @SequenceGenerator(name = "produto_id_gen", sequenceName = "produto_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = Integer.MAX_VALUE)
    private String nombre;

    @Column(name = "precio", precision = 16, scale = 2)
    private Double precio;

}