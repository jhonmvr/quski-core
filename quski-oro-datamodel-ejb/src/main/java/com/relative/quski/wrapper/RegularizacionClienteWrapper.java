package com.relative.quski.wrapper;

import java.io.Serializable;


public class RegularizacionClienteWrapper implements Serializable {

	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
    private Integer id;
    private Integer idNegociacion;
    private String codigoOperacion;
    private String tipoExcepcion;
    private String usuarioSolicitante;
    private String usuarioAprobador;
    private String fechaSolicitud;	
    private String identificacionCliente;
    private String estadoRegularizacion;
    private String nombreCompleto;
    private String numeroOperacion;
	public RegularizacionClienteWrapper() {
		super();
	}
	public RegularizacionClienteWrapper(Integer id, Integer idNegociacion, String codigoOperacion, String tipoExcepcion,
			String usuarioSolicitante, String usuarioAprobador, String fechaSolicitud, String identificacionCliente,
			String estadoRegularizacion, String nombreCompleto, String numeroOperacion) {
		super();
		this.id = id;
		this.idNegociacion = idNegociacion;
		this.codigoOperacion = codigoOperacion;
		this.tipoExcepcion = tipoExcepcion;
		this.usuarioSolicitante = usuarioSolicitante;
		this.usuarioAprobador = usuarioAprobador;
		this.fechaSolicitud = fechaSolicitud;
		this.identificacionCliente = identificacionCliente;
		this.estadoRegularizacion = estadoRegularizacion;
		this.nombreCompleto = nombreCompleto;
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
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
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
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	
}
