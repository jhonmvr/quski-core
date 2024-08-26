package com.relative.quski.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "tb_qo_comprobante")
public class TbQoComprobante implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_negociacion")
    private TbQoNegociacion idNegociacion;

    @Size(max = 50)
    @NotNull
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Size(max = 100)
    @Column(name = "banco", length = 100)
    private String banco;

    @Size(max = 50)
    @Column(name = "numero_cuenta", length = 50)
    private String numeroCuenta;

    @Size(max = 50)
    @Column(name = "tipo_cuenta", length = 50)
    private String tipoCuenta;

//    @Size(max = 50)
//    @Column(name = "identificacion", length = 50)
//    private String identificacion;
//
//    @Size(max = 100)
//    @Column(name = "nombre", length = 100)
//    private String nombre;

    @NotNull
    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "fecha")
    private Timestamp fecha;

}