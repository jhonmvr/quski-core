package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class OpPorAprobarWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal id;
	private BigDecimal idReferencia;
	private String codigo;
	private String operacion;
	private String nombreCompleto;
	private String cedulaCliente;
	private BigDecimal monto;
	private Date fechaCreacion;
	private BigDecimal idAgencia;
	private String estadoProceso;
	private String proceso;
	private String asesor;
	private String usuario;
	private String actividad;
	private String aprobador;
	private Date fechaActualizacion;
	private String aciertos;
	private Integer orden;
	public OpPorAprobarWrapper(BigDecimal id, BigDecimal idReferencia, String codigo, String operacion,
			String nombreCompleto, String cedulaCliente, BigDecimal monto, Date fechaCreacion, BigDecimal idAgencia,
			String estadoProceso, String proceso, String asesor, String usuario, String actividad, String aprobador,
			Date fechaActualizacion,String aciertos, Integer orden) {
		super();
		this.id = id;
		this.idReferencia = idReferencia;
		this.codigo = codigo;
		this.operacion = operacion;
		this.nombreCompleto = nombreCompleto;
		this.cedulaCliente = cedulaCliente;
		this.monto = monto;
		this.fechaCreacion = fechaCreacion;
		this.idAgencia = idAgencia;
		this.estadoProceso = estadoProceso;
		this.proceso = proceso;
		this.asesor = asesor;
		this.usuario = usuario;
		this.actividad = actividad;
		this.aprobador = aprobador;
		this.fechaActualizacion = fechaActualizacion;
		this.aciertos = aciertos;
		this.orden = orden;
	}
	public OpPorAprobarWrapper(BigDecimal id, BigDecimal idReferencia, String codigo, String operacion,
			String nombreCompleto, String cedulaCliente, BigDecimal monto, Date fechaCreacion, BigDecimal idAgencia,
			String estadoProceso, String proceso, String asesor, String usuario, String actividad, String aprobador,
			Date fechaActualizacion, Integer orden) {
		super();
		this.id = id;
		this.idReferencia = idReferencia;
		this.codigo = codigo;
		this.operacion = operacion;
		this.nombreCompleto = nombreCompleto;
		this.cedulaCliente = cedulaCliente;
		this.monto = monto;
		this.fechaCreacion = fechaCreacion;
		this.idAgencia = idAgencia;
		this.estadoProceso = estadoProceso;
		this.proceso = proceso;
		this.asesor = asesor;
		this.usuario = usuario;
		this.actividad = actividad;
		this.aprobador = aprobador;
		this.fechaActualizacion = fechaActualizacion;
		this.orden = orden;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getIdReferencia() {
		return idReferencia;
	}
	public void setIdReferencia(BigDecimal idReferencia) {
		this.idReferencia = idReferencia;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public String getAprobador() {
		return aprobador;
	}
	public void setAprobador(String aprobador) {
		this.aprobador = aprobador;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getAciertos() {
		return aciertos;
	}
	public void setAciertos(String aciertos) {
		this.aciertos = aciertos;
	}
	
	
	

}
