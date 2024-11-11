package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExcepcionOperativaClienteWrapper implements Serializable {

	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer idNegociacion;
	private String codigo;
    private String codigoOperacion;
    private String tipoExcepcion;
    private Integer nivelAprobacion;
    private BigDecimal montoInvolucrado;
    private String usuarioSolicitante;
    private String fechaSolicitud;
    private String observacionAsesor;
    private String observacionAprobador;
    private String usuarioAprobador;
    private String nombreCompleto;
    private String cedulaCliente;
    private String numeroOperacion;

    
	public ExcepcionOperativaClienteWrapper() {
		super();
	}
	
	public ExcepcionOperativaClienteWrapper(Integer id, Integer idNegociacion, String codigo, String codigoOperacion,
			String tipoExcepcion, Integer nivelAprobacion, BigDecimal montoInvolucrado, String usuarioSolicitante,
			String fechaSolicitud, String observacionAsesor, String observacionAprobador, String usuarioAprobador,
			String nombreCompleto, String cedulaCliente, String numeroOperacion) {
		super();
		this.id = id;
		this.idNegociacion = idNegociacion;
		this.codigo = codigo;
		this.codigoOperacion = codigoOperacion;
		this.tipoExcepcion = tipoExcepcion;
		this.nivelAprobacion = nivelAprobacion;
		this.montoInvolucrado = montoInvolucrado;
		this.usuarioSolicitante = usuarioSolicitante;
		this.fechaSolicitud = fechaSolicitud;
		this.observacionAsesor = observacionAsesor;
		this.observacionAprobador = observacionAprobador;
		this.usuarioAprobador = usuarioAprobador;
		this.nombreCompleto = nombreCompleto;
		this.cedulaCliente = cedulaCliente;
		this.numeroOperacion = numeroOperacion;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdNegociacion() {
		return idNegociacion;
	}
	public void setIdNegociacion(Integer idNegociacion) {
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
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
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

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
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
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	
    
	
}
