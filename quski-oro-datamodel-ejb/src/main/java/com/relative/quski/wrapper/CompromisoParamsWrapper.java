package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;

public class CompromisoParamsWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9130666936479613446L;
	

	private String  codigo;
	private String  codigoOperacion;
	private String  tipoCompromiso;
	private String  estadoCompromiso;
	private Date  fechaCompromisoDesde;
	private Date  fechaCompromisoHasta;
	private String  usuarioSolicitud;
	private String  usuarioAprobador;
	private Date  fechaSolicitudDesde;
	private Date  fechaSolicitudHasta;
	private String  numeroOperacion;
	private Date  fechaCompromisoAnteriorDesde;
	private Date  fechaCompromisoAnteriorHasta;
	private String  nombreCliente;
	private String  identificacionCliente;
	private String  proceso;
	
	
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
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
	public Date getFechaCompromisoDesde() {
		return fechaCompromisoDesde;
	}
	public void setFechaCompromisoDesde(Date fechaCompromisoDesde) {
		this.fechaCompromisoDesde = fechaCompromisoDesde;
	}
	public Date getFechaCompromisoHasta() {
		return fechaCompromisoHasta;
	}
	public void setFechaCompromisoHasta(Date fechaCompromisoHasta) {
		this.fechaCompromisoHasta = fechaCompromisoHasta;
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
	public Date getFechaSolicitudDesde() {
		return fechaSolicitudDesde;
	}
	public void setFechaSolicitudDesde(Date fechaSolicitudDesde) {
		this.fechaSolicitudDesde = fechaSolicitudDesde;
	}
	public Date getFechaSolicitudHasta() {
		return fechaSolicitudHasta;
	}
	public void setFechaSolicitudHasta(Date fechaSolicitudHasta) {
		this.fechaSolicitudHasta = fechaSolicitudHasta;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public Date getFechaCompromisoAnteriorDesde() {
		return fechaCompromisoAnteriorDesde;
	}
	public void setFechaCompromisoAnteriorDesde(Date fechaCompromisoAnteriorDesde) {
		this.fechaCompromisoAnteriorDesde = fechaCompromisoAnteriorDesde;
	}
	public Date getFechaCompromisoAnteriorHasta() {
		return fechaCompromisoAnteriorHasta;
	}
	public void setFechaCompromisoAnteriorHasta(Date fechaCompromisoAnteriorHasta) {
		this.fechaCompromisoAnteriorHasta = fechaCompromisoAnteriorHasta;
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
