package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CuotasAmortizacionWrapper  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long cuota;    
	private Date fechaPago;
	private BigDecimal saldoCapital;
	private BigDecimal capital;
	private BigDecimal interes;
	private BigDecimal seguro;
	private BigDecimal otros;
	private BigDecimal total;
	
	
	public Long getCuota() {
		return cuota;
	}
	public void setCuota(Long cuota) {
		this.cuota = cuota;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public BigDecimal getSaldoCapital() {
		return saldoCapital;
	}
	public void setSaldoCapital(BigDecimal saldoCapital) {
		this.saldoCapital = saldoCapital;
	}
	public BigDecimal getCapital() {
		return capital;
	}
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}
	public BigDecimal getInteres() {
		return interes;
	}
	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}
	public BigDecimal getSeguro() {
		return seguro;
	}
	public void setSeguro(BigDecimal seguro) {
		this.seguro = seguro;
	}
	public BigDecimal getOtros() {
		return otros;
	}
	public void setOtros(BigDecimal otros) {
		this.otros = otros;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
