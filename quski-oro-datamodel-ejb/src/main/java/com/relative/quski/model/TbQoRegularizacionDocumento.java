package com.relative.quski.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "tb_qo_regularizacion_documentos")
public class TbQoRegularizacionDocumento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "codigo", length = 100)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_negociacion")
    private TbQoNegociacion idNegociacion;

    @NotNull
    @Column(name = "codigo_operacion", nullable = false)
    private String codigoOperacion;

    @NotNull
    @Column(name = "tipo_excepcion", nullable = false)
    private String tipoExcepcion;

    @Column(name = "usuario_solicitante")
    private String usuarioSolicitante;

    @Column(name = "usuario_aprobador")
    private String usuarioAprobador;

    @Column(name = "url_docmento")
    private String urlDocmento;

    @Column(name = "fecha_solicitud")
    private Timestamp fechaSolicitud;

    @Column(name = "fecha_respuesta")
    private Timestamp fechaRespuesta;

    @Size(max = 10)
    @Column(name = "identificacion_cliente", length = 10)
    private String identificacionCliente;

    @NotNull
    @Column(name = "estado_regularizacion", nullable = false)
    private String estadoRegularizacion;

}