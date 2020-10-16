package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class OperacionesWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

    private BigDecimal   id;
    private String codigoOperacion;
    private String nombreCliente;
    private String cedulaCliente;
    private BigDecimal montoPreaprobado;
    private Date fechaCreacion;
    private BigDecimal idAgencia;
    private String estadoProceso;
    private String proceso;
    private String asesor;
    private String usuarioEjecutor;
    private String actividad;
    
	public OperacionesWrapper(BigDecimal id, String codigoOperacion, String nombreCliente, String cedulaCliente,
			BigDecimal montoPreaprobado, Date fechaCreacion, BigDecimal idAgencia, String estadoProceso, String proceso,
			String asesor, String usuarioEjecutor, String actividad) {
		super();
		this.id = id;
		this.codigoOperacion = codigoOperacion;
		this.nombreCliente = nombreCliente;
		this.cedulaCliente = cedulaCliente;
		this.montoPreaprobado = montoPreaprobado;
		this.fechaCreacion = fechaCreacion;
		this.idAgencia = idAgencia;
		this.estadoProceso = estadoProceso;
		this.proceso = proceso;
		this.asesor = asesor;
		this.usuarioEjecutor = usuarioEjecutor;
		this.actividad = actividad;
	}
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	public BigDecimal getMontoPreaprobado() {
		return montoPreaprobado;
	}
	public void setMontoPreaprobado(BigDecimal montoPreaprobado) {
		this.montoPreaprobado = montoPreaprobado;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public BigDecimal getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(BigDecimal idAgencia) {
		this.idAgencia = idAgencia;
	}
	public String getEstadoProceso() {
		return estadoProceso;
	}
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public String getAsesor() {
		return asesor;
	}
	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}
	public String getUsuarioEjecutor() {
		return usuarioEjecutor;
	}
	public void setUsuarioEjecutor(String usuarioEjecutor) {
		this.usuarioEjecutor = usuarioEjecutor;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

}
