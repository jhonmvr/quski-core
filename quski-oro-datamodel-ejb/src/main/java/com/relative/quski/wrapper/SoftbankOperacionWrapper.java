package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SoftbankOperacionWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String referencia;
	private String numeroOperacionMupi;
	private String numeroOperacion;
	private String numeroOperacionMadre;
	private String codigoCarteraQuski;
	private String tipoOperacion;
	private Date fechaEfectiva;
	private Date fechaVencimiento;
	private Long plazo;
	private BigDecimal montoFinanciado;
	private BigDecimal interesMora;
	private BigDecimal saldo;
	private BigDecimal valorAlDia;
	private BigDecimal valorAlDiaMasCuotaActual;
	private BigDecimal valorCancelaPrestamo;
	private BigDecimal valorProyectadoCuotaActual;
	private BigDecimal valorTotalPrestamoVencimiento;
	private BigDecimal diasMoraActual;
	private BigDecimal numeroCuotasTotales;
	private String nombreProducto;
	private BigDecimal numeroCuotasFaltantes;
	private BigDecimal primeraCuotaVigente;
	private String estadoPrimeraCuotaVigente;
	private BigDecimal numeroGarantiasReales;
	private String estadoOperacion;
	private BigDecimal idMoneda;
	private Boolean esDemandada;
	private Long idPrestamoOrigen;
	private BigDecimal coberturaInicial;
	private BigDecimal coberturaActual;
	private String bloqueo;
	private BigDecimal capital;
	private BigDecimal interes;
	private BigDecimal mora;
	private BigDecimal gastosCobranza;
	private BigDecimal custodia;
	
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getCodigoCarteraQuski() {
		return codigoCarteraQuski;
	}
	public void setCodigoCarteraQuski(String codigoCarteraQuski) {
		this.codigoCarteraQuski = codigoCarteraQuski;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public Date getFechaEfectiva() {
		return fechaEfectiva;
	}
	public void setFechaEfectiva(Date fechaEfectiva) {
		this.fechaEfectiva = fechaEfectiva;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public BigDecimal getInteresMora() {
		return interesMora;
	}
	public void setInteresMora(BigDecimal interesMora) {
		this.interesMora = interesMora;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public BigDecimal getValorAlDia() {
		return valorAlDia;
	}
	public void setValorAlDia(BigDecimal valorAlDia) {
		this.valorAlDia = valorAlDia;
	}
	public BigDecimal getValorAlDiaMasCuotaActual() {
		return valorAlDiaMasCuotaActual;
	}
	public void setValorAlDiaMasCuotaActual(BigDecimal valorAlDiaMasCuotaActual) {
		this.valorAlDiaMasCuotaActual = valorAlDiaMasCuotaActual;
	}
	public BigDecimal getValorCancelaPrestamo() {
		return valorCancelaPrestamo;
	}
	public void setValorCancelaPrestamo(BigDecimal valorCancelaPrestamo) {
		this.valorCancelaPrestamo = valorCancelaPrestamo;
	}
	public BigDecimal getValorProyectadoCuotaActual() {
		return valorProyectadoCuotaActual;
	}
	public void setValorProyectadoCuotaActual(BigDecimal valorProyectadoCuotaActual) {
		this.valorProyectadoCuotaActual = valorProyectadoCuotaActual;
	}
	public BigDecimal getDiasMoraActual() {
		return diasMoraActual;
	}
	public void setDiasMoraActual(BigDecimal diasMoraActual) {
		this.diasMoraActual = diasMoraActual;
	}
	public BigDecimal getNumeroCuotasTotales() {
		return numeroCuotasTotales;
	}
	public void setNumeroCuotasTotales(BigDecimal numeroCuotasTotales) {
		this.numeroCuotasTotales = numeroCuotasTotales;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public BigDecimal getNumeroCuotasFaltantes() {
		return numeroCuotasFaltantes;
	}
	public void setNumeroCuotasFaltantes(BigDecimal numeroCuotasFaltantes) {
		this.numeroCuotasFaltantes = numeroCuotasFaltantes;
	}
	public BigDecimal getPrimeraCuotaVigente() {
		return primeraCuotaVigente;
	}
	public void setPrimeraCuotaVigente(BigDecimal primeraCuotaVigente) {
		this.primeraCuotaVigente = primeraCuotaVigente;
	}
	public String getEstadoPrimeraCuotaVigente() {
		return estadoPrimeraCuotaVigente;
	}
	public void setEstadoPrimeraCuotaVigente(String estadoPrimeraCuotaVigente) {
		this.estadoPrimeraCuotaVigente = estadoPrimeraCuotaVigente;
	}
	public BigDecimal getNumeroGarantiasReales() {
		return numeroGarantiasReales;
	}
	public void setNumeroGarantiasReales(BigDecimal numeroGarantiasReales) {
		this.numeroGarantiasReales = numeroGarantiasReales;
	}
	public String getEstadoOperacion() {
		return estadoOperacion;
	}
	public void setEstadoOperacion(String estadoOperacion) {
		this.estadoOperacion = estadoOperacion;
	}
	public BigDecimal getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(BigDecimal idMoneda) {
		this.idMoneda = idMoneda;
	}
	public Boolean getEsDemandada() {
		return esDemandada;
	}
	public void setEsDemandada(Boolean esDemandada) {
		this.esDemandada = esDemandada;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroOperacionMadre() {
		return numeroOperacionMadre;
	}
	public void setNumeroOperacionMadre(String numeroOperacionMadre) {
		this.numeroOperacionMadre = numeroOperacionMadre;
	}
	public Long getPlazo() {
		return plazo;
	}
	public void setPlazo(Long plazo) {
		this.plazo = plazo;
	}
	public BigDecimal getMontoFinanciado() {
		return montoFinanciado;
	}
	public void setMontoFinanciado(BigDecimal montoFinanciado) {
		this.montoFinanciado = montoFinanciado;
	}
	public String getNumeroOperacionMupi() {
		return numeroOperacionMupi;
	}
	public void setNumeroOperacionMupi(String numeroOperacionMupi) {
		this.numeroOperacionMupi = numeroOperacionMupi;
	}
	public Long getIdPrestamoOrigen() {
		return idPrestamoOrigen;
	}
	public void setIdPrestamoOrigen(Long idPrestamoOrigen) {
		this.idPrestamoOrigen = idPrestamoOrigen;
	}
	public BigDecimal getCoberturaInicial() {
		return coberturaInicial;
	}
	public void setCoberturaInicial(BigDecimal coberturaInicial) {
		this.coberturaInicial = coberturaInicial;
	}
	public BigDecimal getCoberturaActual() {
		return coberturaActual;
	}
	public void setCoberturaActual(BigDecimal coberturaActual) {
		this.coberturaActual = coberturaActual;
	}
	public String getBloqueo() {
		return bloqueo;
	}
	public void setBloqueo(String bloqueo) {
		this.bloqueo = bloqueo;
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
	public BigDecimal getMora() {
		return mora;
	}
	public void setMora(BigDecimal mora) {
		this.mora = mora;
	}
	public BigDecimal getGastosCobranza() {
		return gastosCobranza;
	}
	public void setGastosCobranza(BigDecimal gastosCobranza) {
		this.gastosCobranza = gastosCobranza;
	}
	public BigDecimal getCustodia() {
		return custodia;
	}
	public void setCustodia(BigDecimal custodia) {
		this.custodia = custodia;
	}
	public BigDecimal getValorTotalPrestamoVencimiento() {
		return valorTotalPrestamoVencimiento;
	}
	public void setValorTotalPrestamoVencimiento(BigDecimal valorTotalPrestamoVencimiento) {
		this.valorTotalPrestamoVencimiento = valorTotalPrestamoVencimiento;
	}
	
	

}
