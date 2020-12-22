package com.relative.quski.wrapper;

import java.io.Serializable;

public class ConsultaGlobalWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String migracion;
	private String numeroOperacionMupi;
	private String numeroOperacionMadre;
	private String numeroOperacion;
	private Long idAgencia;
	private String codigoUsuarioAsesor;
	private String identificacion;
	private Long plazo;
	private String fechaInicioSolicitud;
	private String fechaFinSolicitud;
	private String fechaInicioAprobacion;
	private String fechaFinAprobacion;
	private String fechaInicioVencimiento;
	private String fechaFinVencimiento;
	private String codigoEstadoOperacion;
	private String nombreCliente;
	private Boolean esCuotas;
	private Boolean impago;
	private Boolean retanqueo;
	private Long numeroPagina;
	private Long tamanioPagina;
	
	public String getMigracion() {
		return migracion;
	}
	public void setMigracion(String migracion) {
		this.migracion = migracion;
	}
	public String getNumeroOperacionMupi() {
		return numeroOperacionMupi;
	}
	public void setNumeroOperacionMupi(String numeroOperacionMupi) {
		this.numeroOperacionMupi = numeroOperacionMupi;
	}
	public String getNumeroOperacionMadre() {
		return numeroOperacionMadre;
	}
	public void setNumeroOperacionMadre(String numeroOperacionMadre) {
		this.numeroOperacionMadre = numeroOperacionMadre;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public Long getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}
	public String getCodigoUsuarioAsesor() {
		return codigoUsuarioAsesor;
	}
	public void setCodigoUsuarioAsesor(String codigoUsuarioAsesor) {
		this.codigoUsuarioAsesor = codigoUsuarioAsesor;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public Long getPlazo() {
		return plazo;
	}
	public void setPlazo(Long plazo) {
		this.plazo = plazo;
	}
	public String getCodigoEstadoOperacion() {
		return codigoEstadoOperacion;
	}
	public void setCodigoEstadoOperacion(String codigoEstadoOperacion) {
		this.codigoEstadoOperacion = codigoEstadoOperacion;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public Boolean getEsCuotas() {
		return esCuotas;
	}
	public void setEsCuotas(Boolean esCuotas) {
		this.esCuotas = esCuotas;
	}
	public Boolean getImpago() {
		return impago;
	}
	public void setImpago(Boolean impago) {
		this.impago = impago;
	}
	public Boolean getRetanqueo() {
		return retanqueo;
	}
	public void setRetanqueo(Boolean retanqueo) {
		this.retanqueo = retanqueo;
	}
	public Long getNumeroPagina() {
		return numeroPagina;
	}
	public void setNumeroPagina(Long numeroPagina) {
		this.numeroPagina = numeroPagina;
	}
	public Long getTamanioPagina() {
		return tamanioPagina;
	}
	public String getFechaInicioSolicitud() {
		return fechaInicioSolicitud;
	}
	public void setFechaInicioSolicitud(String fechaInicioSolicitud) {
		this.fechaInicioSolicitud = fechaInicioSolicitud;
	}
	public String getFechaFinSolicitud() {
		return fechaFinSolicitud;
	}
	public void setFechaFinSolicitud(String fechaFinSolicitud) {
		this.fechaFinSolicitud = fechaFinSolicitud;
	}
	public String getFechaInicioAprobacion() {
		return fechaInicioAprobacion;
	}
	public void setFechaInicioAprobacion(String fechaInicioAprobacion) {
		this.fechaInicioAprobacion = fechaInicioAprobacion;
	}
	public String getFechaFinAprobacion() {
		return fechaFinAprobacion;
	}
	public void setFechaFinAprobacion(String fechaFinAprobacion) {
		this.fechaFinAprobacion = fechaFinAprobacion;
	}
	public String getFechaInicioVencimiento() {
		return fechaInicioVencimiento;
	}
	public void setFechaInicioVencimiento(String fechaInicioVencimiento) {
		this.fechaInicioVencimiento = fechaInicioVencimiento;
	}
	public String getFechaFinVencimiento() {
		return fechaFinVencimiento;
	}
	public void setFechaFinVencimiento(String fechaFinVencimiento) {
		this.fechaFinVencimiento = fechaFinVencimiento;
	}
	public void setTamanioPagina(Long tamanioPagina) {
		this.tamanioPagina = tamanioPagina;
	}

}
