package com.relative.quski.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_qo_excepcion_operativa")
public class TbQoExcepcionOperativa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_negociacion")
    private TbQoNegociacion idNegociacion;

    @Size(max = 100)
    @Column(name = "codigo", length = 100)
    private String codigo;

    @NotNull
    @Column(name = "codigo_operacion", nullable = false)
    private String codigoOperacion;

    @NotNull
    @Column(name = "tipo_excepcion", nullable = false)
    private String tipoExcepcion;

    @NotNull
    @Column(name = "nivel_aprobacion", nullable = false)
    private Integer nivelAprobacion;

    @NotNull
    @Column(name = "estado_excepcion", nullable = false)
    private String estadoExcepcion;

    @Column(name = "monto_involucrado", precision = 10, scale = 2)
    private BigDecimal montoInvolucrado;

    @Column(name = "usuario_solicitante")
    private String usuarioSolicitante;

    @Column(name = "fecha_solicitud")
    private Timestamp fechaSolicitud;

    @Column(name = "fecha_respuesta")
    private Timestamp fechaRespuesta;

    @Column(name = "observacion_asesor")
    private String observacionAsesor;

    @Column(name = "observacion_aprobador")
    private String observacionAprobador;

    @Column(name = "usuario_aprobador")
    private String usuarioAprobador;

}