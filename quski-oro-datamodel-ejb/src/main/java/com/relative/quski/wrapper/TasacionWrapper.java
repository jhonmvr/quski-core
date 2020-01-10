package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

public class TasacionWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public TasacionWrapper() {
		
	}
	
	private Double descuentoPesoPiedra;
	private Double descuentoSuelda;
	private String descripcion;
	private Double pesoNeto;
	private Double valorAvaluo;
	private Double valorComercial;
	private Double valorRealizacion;
	private Double valorOro;
	public Double getDescuentoPesoPiedra() {
		return descuentoPesoPiedra;
	}
	public void setDescuentoPesoPiedra(Double descuentoPesoPiedra) {
		this.descuentoPesoPiedra = descuentoPesoPiedra;
	}
	public Double getDescuentoSuelda() {
		return descuentoSuelda;
	}
	public void setDescuentoSuelda(Double descuentoSuelda) {
		this.descuentoSuelda = descuentoSuelda;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPesoNeto() {
		return pesoNeto;
	}
	public void setPesoNeto(Double pesoNeto) {
		this.pesoNeto = pesoNeto;
	}
	public Double getValorAvaluo() {
		return valorAvaluo;
	}
	public void setValorAvaluo(Double valorAvaluo) {
		this.valorAvaluo = valorAvaluo;
	}
	public Double getValorComercial() {
		return valorComercial;
	}
	public void setValorComercial(Double valorComercial) {
		this.valorComercial = valorComercial;
	}
	public Double getValorRealizacion() {
		return valorRealizacion;
	}
	public void setValorRealizacion(Double valorRealizacion) {
		this.valorRealizacion = valorRealizacion;
	}
	public Double getValorOro() {
		return valorOro;
	}
	public void setValorOro(Double valorOro) {
		this.valorOro = valorOro;
	}
	
	
}
