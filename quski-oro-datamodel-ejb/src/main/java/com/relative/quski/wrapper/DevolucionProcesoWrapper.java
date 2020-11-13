package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.quski.enums.EstadoEnum;

public class DevolucionProcesoWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long   id;
    private Date  fechaSolicitud;
    private String nombreAgenciaSolicitud;
    private String codigoOperacionMadre;
    private String nombreCliente;
    private String codigoOperacion;
	private String cedulaCliente;
	private String fundaMadre;
	private String fundaActual;
	private String ciudadTevcol;
    private Date  fechaArriboAgencia;
	private String asesor;
	private Date  fechaAprobacion;
	private String codeDetalleCredito;
	private String valorAvaluo;
	private String pesoBruto;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getNombreAgenciaSolicitud() {
		return nombreAgenciaSolicitud;
	}
	public void setNombreAgenciaSolicitud(String nombreAgenciaSolicitud) {
		this.nombreAgenciaSolicitud = nombreAgenciaSolicitud;
	}
	public String getCodigoOperacionMadre() {
		return codigoOperacionMadre;
	}
	public void setCodigoOperacionMadre(String codigoOperacionMadre) {
		this.codigoOperacionMadre = codigoOperacionMadre;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	public String getFundaMadre() {
		return fundaMadre;
	}
	public void setFundaMadre(String fundaMadre) {
		this.fundaMadre = fundaMadre;
	}
	public String getFundaActual() {
		return fundaActual;
	}
	public void setFundaActual(String fundaActual) {
		this.fundaActual = fundaActual;
	}
	public String getCiudadTevcol() {
		return ciudadTevcol;
	}
	public void setCiudadTevcol(String ciudadTevcol) {
		this.ciudadTevcol = ciudadTevcol;
	}
	public Date getFechaArriboAgencia() {
		return fechaArriboAgencia;
	}
	public void setFechaArriboAgencia(Date fechaArriboAgencia) {
		this.fechaArriboAgencia = fechaArriboAgencia;
	}
	public String getAsesor() {
		return asesor;
	}
	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}
	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}
	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	public String getCodeDetalleCredito() {
		return codeDetalleCredito;
	}
	public void setCodeDetalleCredito(String codeDetalleCredito) {
		this.codeDetalleCredito = codeDetalleCredito;
	}
	public String getValorAvaluo() {
		return valorAvaluo;
	}
	public void setValorAvaluo(String valorAvaluo) {
		this.valorAvaluo = valorAvaluo;
	}
	public String getPesoBruto() {
		return pesoBruto;
	}
	public void setPesoBruto(String pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	


	

}
