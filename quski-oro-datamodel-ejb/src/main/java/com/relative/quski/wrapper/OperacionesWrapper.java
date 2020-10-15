package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OperacionesWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long   id;
    private String codigoOperacion;
    private String nombreCliente;
    private String identificacion;
    private BigDecimal montoPreAprobado;
    private Date fechaCreacion;
    private String situacion;
    private String agencia;
    private String estadoProceso;
    private String proceso;
    private String asesor;
    private String aprobador;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public BigDecimal getMontoPreAprobado() {
		return montoPreAprobado;
	}
	public void setMontoPreAprobado(BigDecimal montoPreAprobado) {
		this.montoPreAprobado = montoPreAprobado;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getSituacion() {
		return situacion;
	}
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
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
	public String getAprobador() {
		return aprobador;
	}
	public void setAprobador(String aprobador) {
		this.aprobador = aprobador;
	}
}
