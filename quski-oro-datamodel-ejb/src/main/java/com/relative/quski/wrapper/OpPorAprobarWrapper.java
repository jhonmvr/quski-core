package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class OpPorAprobarWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

    private BigDecimal  id;
    private String codigo;
    private String proceso;
    private Date fechaSolicitud;
    private String cedulaCliente;
    private String nombreCliente;
    private BigDecimal   idAgencia;
    private String asesor;
    private String aprobador;
    
	public OpPorAprobarWrapper(	BigDecimal id, String codigo, String proceso, 
								Date fechaSolicitud, String cedulaCliente,
								String nombreCliente, BigDecimal idAgencia, String asesor, String aprobador) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.proceso = proceso;
		this.fechaSolicitud = fechaSolicitud;
		this.cedulaCliente = cedulaCliente;
		this.nombreCliente = nombreCliente;
		this.idAgencia = idAgencia;
		this.asesor = asesor;
		this.aprobador = aprobador;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
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
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public BigDecimal getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(BigDecimal idAgencia) {
		this.idAgencia = idAgencia;
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
