package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

public class ListadoOperacionDevueltaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public ListadoOperacionDevueltaWrapper() {
		
	}
	private String codigo;
	private String proceso;
	private String fechaCreacion;
	private String fechaFin;
	private String situacionCodigo;
	private String estadoCodigo;
	private String nombreCliente;
	private String cedula;
	private String usuarioOrganizador;
	private String agencia;
	private String actividadActual;
	private String usuarioEjecutor;
	private String motivoDevolucion;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getSituacionCodigo() {
		return situacionCodigo;
	}
	public void setSituacionCodigo(String situacionCodigo) {
		this.situacionCodigo = situacionCodigo;
	}
	public String getEstadoCodigo() {
		return estadoCodigo;
	}
	public void setEstadoCodigo(String estadoCodigo) {
		this.estadoCodigo = estadoCodigo;
	}
	public String getCliente() {
		return nombreCliente;
	}
	public void setCliente(String cliente) {
		this.nombreCliente = cliente;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getUsuarioOrganizador() {
		return usuarioOrganizador;
	}
	public void setUsuarioOrganizador(String usuarioOrganizador) {
		this.usuarioOrganizador = usuarioOrganizador;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getActividadActual() {
		return actividadActual;
	}
	public void setActividadActual(String actividadActual) {
		this.actividadActual = actividadActual;
	}
	public String getUsuarioEjecutor() {
		return usuarioEjecutor;
	}
	public void setUsuarioEjecutor(String usuarioEjecutor) {
		this.usuarioEjecutor = usuarioEjecutor;
	}
	public String getMotivoDevolucion() {
		return motivoDevolucion;
	}
	public void setMotivoDevolucion(String motivoDevolucion) {
		this.motivoDevolucion = motivoDevolucion;
	}
	
	
	

}
