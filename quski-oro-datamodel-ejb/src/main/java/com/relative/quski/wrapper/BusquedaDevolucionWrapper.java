package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;

import com.relative.quski.enums.ProcesoEnum;


public class BusquedaDevolucionWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codigoOperacion; 		// Proceso //
	private String identificacion;	
	private String agencia;// Proceso //
	private Date fechaAprobacionDesde; 	// Proceso //
	private Date fechaAprobacionHasta;
	private ProcesoEnum estado;
	private Integer numberPage;
	private Integer numberItems;
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public Date getFechaAprobacionDesde() {
		return fechaAprobacionDesde;
	}
	public void setFechaAprobacionDesde(Date fechaAprobacionDesde) {
		this.fechaAprobacionDesde = fechaAprobacionDesde;
	}
	public Date getFechaAprobacionHasta() {
		return fechaAprobacionHasta;
	}
	public void setFechaAprobacionHasta(Date fechaAprobacionHasta) {
		this.fechaAprobacionHasta = fechaAprobacionHasta;
	}
	public ProcesoEnum getEstado() {
		return estado;
	}
	public void setEstado(ProcesoEnum estado) {
		this.estado = estado;
	}
	public Integer getNumberPage() {
		return numberPage;
	}
	public void setNumberPage(Integer numberPage) {
		this.numberPage = numberPage;
	}
	public Integer getNumberItems() {
		return numberItems;
	}
	public void setNumberItems(Integer numberItems) {
		this.numberItems = numberItems;
	}
	
	
}
