package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;

public class CompromisoReporteWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2681801495603772954L;
	
	private String codigo;
	private String codigoOperacion;
	private String tipoCompromiso;
	private String estadoCompromiso;
	private String fechaCompromisoPago;
	private String usuarioSolicitud;
	private String usuarioAprobador;
	private String observacionSolicitud;
	private String observacionAprobador;
	private String fechaSolicitud;
	private String fechaAprobador;
	private String numeroOperacion;
	private String fechaCompromisoPagoAnterior;
	private String nombreCliente;
	private String identificacionCliente;
	private String proceso;
	public CompromisoReporteWrapper(String codigo, String codigoOperacion, String tipoCompromiso,
			String estadoCompromiso, String fechaCompromisoPago, String usuarioSolicitud, String usuarioAprobador,
			String observacionSolicitud, String observacionAprobador, String fechaSolicitud, String fechaAprobador,
			String numeroOperacion, String fechaCompromisoPagoAnterior, String nombreCliente, String identificacionCliente, String proceso) {
		super();
		this.codigo = codigo;
		this.codigoOperacion = codigoOperacion;
		this.tipoCompromiso = tipoCompromiso;
		this.estadoCompromiso = estadoCompromiso;
		this.fechaCompromisoPago = fechaCompromisoPago;
		this.usuarioSolicitud = usuarioSolicitud;
		this.usuarioAprobador = usuarioAprobador;
		this.observacionSolicitud = observacionSolicitud;
		this.observacionAprobador = observacionAprobador;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaAprobador = fechaAprobador;
		this.numeroOperacion = numeroOperacion;
		this.fechaCompromisoPagoAnterior = fechaCompromisoPagoAnterior;
		this.nombreCliente = nombreCliente;
		this.identificacionCliente = identificacionCliente;
		this.proceso = proceso;
	}
	
	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public CompromisoReporteWrapper() {
		super();
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
	public String getTipoCompromiso() {
		return tipoCompromiso;
	}
	public void setTipoCompromiso(String tipoCompromiso) {
		this.tipoCompromiso = tipoCompromiso;
	}
	public String getEstadoCompromiso() {
		return estadoCompromiso;
	}
	public void setEstadoCompromiso(String estadoCompromiso) {
		this.estadoCompromiso = estadoCompromiso;
	}
	public String getFechaCompromisoPago() {
		return fechaCompromisoPago;
	}
	public void setFechaCompromisoPago(String fechaCompromisoPago) {
		this.fechaCompromisoPago = fechaCompromisoPago;
	}
	public String getUsuarioSolicitud() {
		return usuarioSolicitud;
	}
	public void setUsuarioSolicitud(String usuarioSolicitud) {
		this.usuarioSolicitud = usuarioSolicitud;
	}
	public String getUsuarioAprobador() {
		return usuarioAprobador;
	}
	public void setUsuarioAprobador(String usuarioAprobador) {
		this.usuarioAprobador = usuarioAprobador;
	}
	public String getObservacionSolicitud() {
		return observacionSolicitud;
	}
	public void setObservacionSolicitud(String observacionSolicitud) {
		this.observacionSolicitud = observacionSolicitud;
	}
	public String getObservacionAprobador() {
		return observacionAprobador;
	}
	public void setObservacionAprobador(String observacionAprobador) {
		this.observacionAprobador = observacionAprobador;
	}
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getFechaAprobador() {
		return fechaAprobador;
	}
	public void setFechaAprobador(String fechaAprobador) {
		this.fechaAprobador = fechaAprobador;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getFechaCompromisoPagoAnterior() {
		return fechaCompromisoPagoAnterior;
	}
	public void setFechaCompromisoPagoAnterior(String fechaCompromisoPagoAnterior) {
		this.fechaCompromisoPagoAnterior = fechaCompromisoPagoAnterior;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}
	
	

}
