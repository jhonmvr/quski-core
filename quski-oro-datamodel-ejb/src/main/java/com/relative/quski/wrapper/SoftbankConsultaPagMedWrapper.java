package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SoftbankConsultaPagMedWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private String numeroPrestamo;
	private Long plazo;
	private String tipoCredito;
	private BigDecimal saldoCapital;
	private Long cuotaActiva;
	private Long diasMora;
	private Date fechaMediacion;
	private Date fechaCompromisoPago;
	private String nipMediacion;
	private List<SoftbankCuotaPagMedWrapper> cuotasAtrasadas;
	public SoftbankConsultaPagMedWrapper(String numeroPrestamo, Long plazo, String tipoCredito, BigDecimal saldoCapital,
			Long cuotaActiva, Long diasMora, Date fechaMediacion, Date fechaCompromisoPago, String nipMediacion,
			List<SoftbankCuotaPagMedWrapper> cuotasAtrasadas) {
		super();
		this.numeroPrestamo = numeroPrestamo;
		this.plazo = plazo;
		this.tipoCredito = tipoCredito;
		this.saldoCapital = saldoCapital;
		this.cuotaActiva = cuotaActiva;
		this.diasMora = diasMora;
		this.fechaMediacion = fechaMediacion;
		this.fechaCompromisoPago = fechaCompromisoPago;
		this.nipMediacion = nipMediacion;
		this.cuotasAtrasadas = cuotasAtrasadas;
	}
	public SoftbankConsultaPagMedWrapper() {
		super();
	}
	public String getNumeroPrestamo() {
		return numeroPrestamo;
	}
	public void setNumeroPrestamo(String numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}
	public Long getPlazo() {
		return plazo;
	}
	public void setPlazo(Long plazo) {
		this.plazo = plazo;
	}
	public String getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	public BigDecimal getSaldoCapital() {
		return saldoCapital;
	}
	public void setSaldoCapital(BigDecimal saldoCapital) {
		this.saldoCapital = saldoCapital;
	}
	public Long getCuotaActiva() {
		return cuotaActiva;
	}
	public void setCuotaActiva(Long cuotaActiva) {
		this.cuotaActiva = cuotaActiva;
	}
	public Long getDiasMora() {
		return diasMora;
	}
	public void setDiasMora(Long diasMora) {
		this.diasMora = diasMora;
	}
	public Date getFechaMediacion() {
		return fechaMediacion;
	}
	public void setFechaMediacion(Date fechaMediacion) {
		this.fechaMediacion = fechaMediacion;
	}
	public Date getFechaCompromisoPago() {
		return fechaCompromisoPago;
	}
	public void setFechaCompromisoPago(Date fechaCompromisoPago) {
		this.fechaCompromisoPago = fechaCompromisoPago;
	}
	public String getNipMediacion() {
		return nipMediacion;
	}
	public void setNipMediacion(String nipMediacion) {
		this.nipMediacion = nipMediacion;
	}
	public List<SoftbankCuotaPagMedWrapper> getCuotasAtrasadas() {
		return cuotasAtrasadas;
	}
	public void setCuotasAtrasadas(List<SoftbankCuotaPagMedWrapper> cuotasAtrasadas) {
		this.cuotasAtrasadas = cuotasAtrasadas;
	}

}