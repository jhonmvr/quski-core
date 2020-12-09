package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class DevolucionPendienteArribosWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long   id;
    private Date   fechaSolicitud;
    private String codigoOperacionMadre;
    private String codigoOperacion;
    private String nombreCliente;
	private String cedulaCliente;
	private String fundaMadre;
	private String fundaActual;	
	private String ciudadTevcol;
    private String fechaArriboAgencia;
	private String fechaAprobacion;
	private BigDecimal valorAvaluao;
	private BigDecimal pesoBruto;
	

	
	
	
	public DevolucionPendienteArribosWrapper(BigDecimal id, Date fechaSolicitud, String codigoOperacionMadre,
			String codigoOperacion, String nombreCliente, String cedulaCliente, String fundaMadre, String fundaActual,
			String ciudadTevcol, String fechaArriboAgencia, String fechaAprobacion, BigDecimal valorAvaluao,
			BigDecimal pesoBruto) {
		super();
		this.id = id.longValue();
		this.fechaSolicitud = fechaSolicitud;
		this.codigoOperacionMadre = codigoOperacionMadre;
		this.codigoOperacion = codigoOperacion;
		this.nombreCliente = nombreCliente;
		this.cedulaCliente = cedulaCliente;
		this.fundaMadre = fundaMadre;
		this.fundaActual = fundaActual;
		this.ciudadTevcol = ciudadTevcol;
		this.fechaArriboAgencia = fechaArriboAgencia;
		this.fechaAprobacion = fechaAprobacion;
		this.valorAvaluao = valorAvaluao;
		this.pesoBruto = pesoBruto;
	}
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

	public String getCodigoOperacionMadre() {
		return codigoOperacionMadre;
	}
	public void setCodigoOperacionMadre(String codigoOperacionMadre) {
		this.codigoOperacionMadre = codigoOperacionMadre;
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
	public String getFechaArriboAgencia() {
		return fechaArriboAgencia;
	}
	public void setFechaArriboAgencia(String fechaArriboAgencia) {
		this.fechaArriboAgencia = fechaArriboAgencia;
	}

	public String getFechaAprobacion() {
		return fechaAprobacion;
	}
	public void setFechaAprobacion(String fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	public BigDecimal getValorAvaluao() {
		return valorAvaluao;
	}
	public void setValorAvaluao(BigDecimal valorAvaluao) {
		this.valorAvaluao = valorAvaluao;
	}
	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}
	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}


	
	
	

}
