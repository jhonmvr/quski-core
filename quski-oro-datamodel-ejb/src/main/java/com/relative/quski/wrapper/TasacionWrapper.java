package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

public class TasacionWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public TasacionWrapper() {
		
	}
	
	private BigDecimal descuentoPesoPiedra;
	private BigDecimal descuentoSuelda;
	private String descripcion;
	private BigDecimal pesoNeto;
	private BigDecimal valorAvaluo;
	private Double valorComercial;
	private Double valorRealizacion;
	private Double valorOro;
	
	
	
	
	public BigDecimal getDescuentoPesoPiedra() {
		return descuentoPesoPiedra;
	}
	public void setDescuentoPesoPiedra(BigDecimal descuentoPesoPiedra) {
		this.descuentoPesoPiedra = descuentoPesoPiedra;
	}
	public BigDecimal getDescuentoSuelda() {
		return descuentoSuelda;
	}
	public void setDescuentoSuelda(BigDecimal descuentoSuelda) {
		this.descuentoSuelda = descuentoSuelda;
	}
	public BigDecimal getPesoNeto() {
		return pesoNeto;
	}
	public void setPesoNeto(BigDecimal pesoNeto) {
		this.pesoNeto = pesoNeto;
	}
	public BigDecimal getValorAvaluo() {
		return valorAvaluo;
	}
	public void setValorAvaluo(BigDecimal valorAvaluo) {
		this.valorAvaluo = valorAvaluo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
