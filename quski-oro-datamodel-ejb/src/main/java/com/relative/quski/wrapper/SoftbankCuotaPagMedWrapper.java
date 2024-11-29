package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;


public class SoftbankCuotaPagMedWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long numeroDividendo;
	private String fechaFinDividendo;
	private BigDecimal valorAPagar;
	private BigDecimal valorPagado;
    private String fechaUltimoPago;
	public SoftbankCuotaPagMedWrapper(Long numeroDividendo, String fechaFinDividendo, BigDecimal valorAPagar,
			BigDecimal valorPagado, String fechaUltimoPago) {
		super();
		this.numeroDividendo = numeroDividendo;
		this.fechaFinDividendo = fechaFinDividendo;
		this.valorAPagar = valorAPagar;
		this.valorPagado = valorPagado;
		this.fechaUltimoPago = fechaUltimoPago;
	}
	public SoftbankCuotaPagMedWrapper() {
		super();
	}
	public Long getNumeroDividendo() {
		return numeroDividendo;
	}
	public void setNumeroDividendo(Long numeroDividendo) {
		this.numeroDividendo = numeroDividendo;
	}
	public String getFechaFinDividendo() {
		return fechaFinDividendo;
	}
	public void setFechaFinDividendo(String fechaFinDividendo) {
		this.fechaFinDividendo = fechaFinDividendo;
	}
	public BigDecimal getValorAPagar() {
		return valorAPagar;
	}
	public void setValorAPagar(BigDecimal valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
	public BigDecimal getValorPagado() {
		return valorPagado;
	}
	public void setValorPagado(BigDecimal valorPagado) {
		this.valorPagado = valorPagado;
	}
	public String getFechaUltimoPago() {
		return fechaUltimoPago;
	}
	public void setFechaUltimoPago(String fechaUltimoPago) {
		this.fechaUltimoPago = fechaUltimoPago;
	}

	

}