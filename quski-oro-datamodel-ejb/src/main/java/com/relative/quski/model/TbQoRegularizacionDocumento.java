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
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public TbQoNegociacion getIdNegociacion() {
		return idNegociacion;
	}

	public void setIdNegociacion(TbQoNegociacion idNegociacion) {
		this.idNegociacion = idNegociacion;
	}

	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public String getTipoExcepcion() {
		return tipoExcepcion;
	}

	public void setTipoExcepcion(String tipoExcepcion) {
		this.tipoExcepcion = tipoExcepcion;
	}

	public String getUsuarioSolicitante() {
		return usuarioSolicitante;
	}

	public void setUsuarioSolicitante(String usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}

	public String getUsuarioAprobador() {
		return usuarioAprobador;
	}

	public void setUsuarioAprobador(String usuarioAprobador) {
		this.usuarioAprobador = usuarioAprobador;
	}

	public String getUrlDocmento() {
		return urlDocmento;
	}

	public void setUrlDocmento(String urlDocmento) {
		this.urlDocmento = urlDocmento;
	}

	public Timestamp getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Timestamp fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Timestamp getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(Timestamp fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	public String getEstadoRegularizacion() {
		return estadoRegularizacion;
	}

	public void setEstadoRegularizacion(String estadoRegularizacion) {
		this.estadoRegularizacion = estadoRegularizacion;
	}
    
    

}