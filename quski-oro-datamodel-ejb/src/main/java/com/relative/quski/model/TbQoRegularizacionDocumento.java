package com.relative.quski.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_qo_regularizacion_documentos")
public class TbQoRegularizacionDocumento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "codigo", length = 100)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_negociacion")
    private TbQoNegociacion idNegociacion;

    @NotNull
    @Lob
    @Column(name = "codigo_operacion", nullable = false)
    private String codigoOperacion;

    @NotNull
    @Lob
    @Column(name = "tipo_excepcion", nullable = false)
    private String tipoExcepcion;

    @Column(name = "usuario_solicitante")
    private Integer usuarioSolicitante;

    @Lob
    @Column(name = "usuario_aprobador")
    private String usuarioAprobador;

    @Lob
    @Column(name = "url_docmento")
    private String urlDocmento;

    @Column(name = "fecha_solicitud")
    private Instant fechaSolicitud;

    @Column(name = "fecha_respuesta")
    private Instant fechaRespuesta;

}