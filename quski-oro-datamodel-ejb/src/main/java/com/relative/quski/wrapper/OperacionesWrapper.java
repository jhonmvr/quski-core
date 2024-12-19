package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class OperacionesWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

    private BigDecimal   id;
    private String codigoOperacion;
    private String codigoOperacionant;
    private String codigoBpm;
    private String nombreCliente;
    private String cedulaCliente;

	private BigDecimal montoFinanciado;
    private Timestamp fechaCreacion;
    private BigDecimal idAgencia;
    private String estadoProceso;
    private String proceso;
    private String asesor;
    private String usuarioEjecutor;
    private String actividad;
    private String motivo;
    
	public OperacionesWrapper(BigDecimal id, String codigoBpm, String codigoOperacion,String codigoOperacionant, String nombreCliente, String cedulaCliente,
			BigDecimal montoFinanciado, Timestamp fechaCreacion, BigDecimal idAgencia, String estadoProceso, String proceso,
			String asesor, String usuarioEjecutor, String actividad) {
		super();
		this.id = id;
		this.codigoBpm = codigoBpm;
		this.codigoOperacion = codigoOperacion;
		this.nombreCliente = nombreCliente;
		this.cedulaCliente = cedulaCliente;
		this.montoFinanciado = montoFinanciado;
		this.fechaCreacion = fechaCreacion;
		this.idAgencia = idAgencia;
		this.estadoProceso = estadoProceso;
		this.proceso = proceso;
		this.asesor = asesor;
		this.usuarioEjecutor = usuarioEjecutor;
		this.actividad = actividad;
		this.codigoOperacionant= codigoOperacionant;
	}
	
	public OperacionesWrapper(BigDecimal id, String codigoBpm, String codigoOperacion,String codigoOperacionant, String nombreCliente, String cedulaCliente,
			BigDecimal montoFinanciado, Timestamp fechaCreacion, BigDecimal idAgencia, String estadoProceso, String proceso,
			String asesor, String usuarioEjecutor, String actividad, String motivo) {
		super();
		this.id = id;
		this.codigoBpm = codigoBpm;
		this.codigoOperacion = codigoOperacion;
		this.nombreCliente = nombreCliente;
		this.cedulaCliente = cedulaCliente;
		this.montoFinanciado = montoFinanciado;
		this.fechaCreacion = fechaCreacion;
		this.idAgencia = idAgencia;
		this.estadoProceso = estadoProceso;
		this.proceso = proceso;
		this.asesor = asesor;
		this.usuarioEjecutor = usuarioEjecutor;
		this.actividad = actividad;
		this.codigoOperacionant= codigoOperacionant;
		this.motivo = motivo;
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
	public BigDecimal getMontoFinanciado() {
		return montoFinanciado;
	}
	public void setMontoFinanciado(BigDecimal montoFinanciado) {
		this.montoFinanciado = montoFinanciado;
	}
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Timestamp fechaCreacion) {
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
    public String getCodigoBpm() {
		return codigoBpm;
	}

	public void setCodigoBpm(String codigoBpm) {
		this.codigoBpm = codigoBpm;
	}

	public String getCodigoOperacionant() {
		return codigoOperacionant;
	}

	public void setCodigoOperacionant(String codigoOperacionant) {
		this.codigoOperacionant = codigoOperacionant;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}
