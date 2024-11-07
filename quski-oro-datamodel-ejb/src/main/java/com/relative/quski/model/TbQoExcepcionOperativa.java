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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_qo_excepcion_operativa")
public class TbQoExcepcionOperativa implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbQoNegociacion getIdNegociacion() {
		return idNegociacion;
	}

	public void setIdNegociacion(TbQoNegociacion idNegociacion) {
		this.idNegociacion = idNegociacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public Integer getNivelAprobacion() {
		return nivelAprobacion;
	}

	public void setNivelAprobacion(Integer nivelAprobacion) {
		this.nivelAprobacion = nivelAprobacion;
	}

	public String getEstadoExcepcion() {
		return estadoExcepcion;
	}

	public void setEstadoExcepcion(String estadoExcepcion) {
		this.estadoExcepcion = estadoExcepcion;
	}

	public BigDecimal getMontoInvolucrado() {
		return montoInvolucrado;
	}

	public void setMontoInvolucrado(BigDecimal montoInvolucrado) {
		this.montoInvolucrado = montoInvolucrado;
	}

	public String getUsuarioSolicitante() {
		return usuarioSolicitante;
	}

	public void setUsuarioSolicitante(String usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
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

	public String getObservacionAsesor() {
		return observacionAsesor;
	}

	public void setObservacionAsesor(String observacionAsesor) {
		this.observacionAsesor = observacionAsesor;
	}

	public String getObservacionAprobador() {
		return observacionAprobador;
	}

	public void setObservacionAprobador(String observacionAprobador) {
		this.observacionAprobador = observacionAprobador;
	}

	public String getUsuarioAprobador() {
		return usuarioAprobador;
	}

	public void setUsuarioAprobador(String usuarioAprobador) {
		this.usuarioAprobador = usuarioAprobador;
	}
    
    

}