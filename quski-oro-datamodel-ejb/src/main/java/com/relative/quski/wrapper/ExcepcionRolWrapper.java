/**
 * 
 */
package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
public class ExcepcionRolWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3524098047600544935L;
	/**
	 * 
	 */
	private BigDecimal id;
	private String tipoExcepcion ;
	private String nombreCliente;
	private String apellidoCliente;
	private BigDecimal idNegociacion;
	private String identificacion;
	private String nombreCompleto;
	private String observacionAsesor;
	private String estadoExcepcion;
	private String mensajeBre;
	private String asesor;
	private String numeroOperacion;
	private String codigoBpm;
	
	
	public String getObservacionAsesor() {
		return observacionAsesor;
	}
	public void setObservacionAsesor(String observacionAsesor) {
		this.observacionAsesor = observacionAsesor;
	}
	public String getEstadoExcepcion() {
		return estadoExcepcion;
	}
	public void setEstadoExcepcion(String estadoExcepcion) {
		this.estadoExcepcion = estadoExcepcion;
	}
	
	
	public ExcepcionRolWrapper(BigDecimal id, String tipoExcepcion, String nombreCliente, String apellidoCliente,
			BigDecimal idNegociacion, String identificacion, String nombreCompleto,  String observacionAsesor,
			String estadoExcepcion, String mensajeBre,String asesor, String numeroOperacion, String codigoBpm) {
		this.id = id;
		this.tipoExcepcion = tipoExcepcion;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
		this.idNegociacion = idNegociacion;
		this.identificacion = identificacion;
		this.nombreCompleto = nombreCompleto;
		this.observacionAsesor = observacionAsesor;
		this.estadoExcepcion = estadoExcepcion;
		this.mensajeBre = mensajeBre;
		this.asesor = asesor;
		this.numeroOperacion = numeroOperacion;
		this.codigoBpm = codigoBpm;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getTipoExcepcion() {
		return tipoExcepcion;
	}
	public void setTipoExcepcion(String tipoExcepcion) {
		this.tipoExcepcion = tipoExcepcion;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getApellidoCliente() {
		return apellidoCliente;
	}
	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}
	public BigDecimal getIdNegociacion() {
		return idNegociacion;
	}
	public void setIdNegociacion(BigDecimal idNegociacion) {
		this.idNegociacion = idNegociacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getMensajeBre() {
		return mensajeBre;
	}
	public void setMensajeBre(String mensajeBre) {
		this.mensajeBre = mensajeBre;
	}
	public String getAsesor() {
		return asesor;
	}
	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getCodigoBpm() {
		return codigoBpm;
	}
	public void setCodigoBpm(String codigoBpm) {
		this.codigoBpm = codigoBpm;
	}
	
	
	
	

}
