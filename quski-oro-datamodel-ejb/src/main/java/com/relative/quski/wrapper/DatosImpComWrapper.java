package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class DatosImpComWrapper implements Serializable{

	private static final long serialVersionUID = 1L;


	public DatosImpComWrapper() {
		
	}
	
	public DatosImpComWrapper(String codigo, String codigoFormaPagoQuski, BigDecimal valor) {
		super();
		this.codigo = codigo;
		this.codigoFormaPagoQuski = codigoFormaPagoQuski;
		this.valor = valor;
	}
	
	private String codigo;
    private String codigoFormaPagoQuski;
    private BigDecimal valor;
    private BigDecimal tasa;


	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigoFormaPagoQuski() {
		return codigoFormaPagoQuski;
	}
	public void setCodigoFormaPagoQuski(String codigoFormaPagoQuski) {
		this.codigoFormaPagoQuski = codigoFormaPagoQuski;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTasa() {
		return tasa;
	}

	public void setTasa(BigDecimal tasa) {
		this.tasa = tasa;
	}
	
	
	
	

	
}
