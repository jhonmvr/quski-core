package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class CalculadoraOpcionWrapper implements Serializable {

	/**
	 * 
	 */
	 static final long serialVersionUID = 2549180662498901580L;
	

    private Long plazo;
    private String periodoPlazo;
    private String periodicidadPlazo;
    private BigDecimal montoFinanciado;
    private BigDecimal valorARecibir;
    private BigDecimal valorAPagar;
    private BigDecimal costoCustodia;
    private BigDecimal costoFideicomiso;
    private BigDecimal costoSeguro;
    private BigDecimal costoTasacion;
    private BigDecimal costoTransporte;
    private BigDecimal costoValoracion;
    private BigDecimal impuestoSolca;
    private String formaPagoImpuestoSolca;
    private String formaPagoCapital;
    private String formaPagoCustodia;
    private String formaPagoFideicomiso;
    private String formaPagoInteres;
    private String formaPagoMora;
    private String formaPagoGastoCobranza;
    private String formaPagoSeguro;
    private String formaPagoTasador;
    private String formaPagoTransporte;
    private String formaPagoValoracion;
    private BigDecimal saldoInteres;
    private BigDecimal saldoMora;
    private BigDecimal gastoCobranza;
    private BigDecimal cuota;
    private BigDecimal saldoCapitalRenov;
    private BigDecimal montoPrevioDesembolso;
    private BigDecimal totalGastosNuevaOperacion;
    private BigDecimal totalCostosOperacionAnterior;
    private BigDecimal custodiaDevengada;
    private String formaPagoCustodiaDevengada;
    private String tipooferta;
    private BigDecimal porcentajeflujoplaneado;
    private BigDecimal dividendoflujoplaneado;
    private BigDecimal dividendosprorrateoserviciosdiferido;
    
	public Long getPlazo() {
		return plazo;
	}
	public void setPlazo(Long plazo) {
		this.plazo = plazo;
	}
	public String getPeriodoPlazo() {
		return periodoPlazo;
	}
	public void setPeriodoPlazo(String periodoPlazo) {
		this.periodoPlazo = periodoPlazo;
	}
	public String getPeriodicidadPlazo() {
		return periodicidadPlazo;
	}
	public void setPeriodicidadPlazo(String periodicidadPlazo) {
		this.periodicidadPlazo = periodicidadPlazo;
	}
	public BigDecimal getMontoFinanciado() {
		return montoFinanciado;
	}
	public void setMontoFinanciado(BigDecimal montoFinanciado) {
		this.montoFinanciado = montoFinanciado;
	}
	public BigDecimal getValorARecibir() {
		return valorARecibir;
	}
	public void setValorARecibir(BigDecimal valorARecibir) {
		this.valorARecibir = valorARecibir;
	}
	public BigDecimal getValorAPagar() {
		return valorAPagar;
	}
	public void setValorAPagar(BigDecimal valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
	public BigDecimal getCostoCustodia() {
		return costoCustodia;
	}
	public void setCostoCustodia(BigDecimal costoCustodia) {
		this.costoCustodia = costoCustodia;
	}
	public BigDecimal getCostoFideicomiso() {
		return costoFideicomiso;
	}
	public void setCostoFideicomiso(BigDecimal costoFideicomiso) {
		this.costoFideicomiso = costoFideicomiso;
	}
	public BigDecimal getCostoSeguro() {
		return costoSeguro;
	}
	public void setCostoSeguro(BigDecimal costoSeguro) {
		this.costoSeguro = costoSeguro;
	}
	public BigDecimal getCostoTasacion() {
		return costoTasacion;
	}
	public void setCostoTasacion(BigDecimal costoTasacion) {
		this.costoTasacion = costoTasacion;
	}
	public BigDecimal getCostoTransporte() {
		return costoTransporte;
	}
	public void setCostoTransporte(BigDecimal costoTransporte) {
		this.costoTransporte = costoTransporte;
	}
	public BigDecimal getCostoValoracion() {
		return costoValoracion;
	}
	public void setCostoValoracion(BigDecimal costoValoracion) {
		this.costoValoracion = costoValoracion;
	}
	public BigDecimal getImpuestoSolca() {
		return impuestoSolca;
	}
	public void setImpuestoSolca(BigDecimal impuestoSolca) {
		this.impuestoSolca = impuestoSolca;
	}
	public String getFormaPagoImpuestoSolca() {
		return formaPagoImpuestoSolca;
	}
	public void setFormaPagoImpuestoSolca(String formaPagoImpuestoSolca) {
		this.formaPagoImpuestoSolca = formaPagoImpuestoSolca;
	}
	public String getFormaPagoCapital() {
		return formaPagoCapital;
	}
	public void setFormaPagoCapital(String formaPagoCapital) {
		this.formaPagoCapital = formaPagoCapital;
	}
	public String getFormaPagoCustodia() {
		return formaPagoCustodia;
	}
	public void setFormaPagoCustodia(String formaPagoCustodia) {
		this.formaPagoCustodia = formaPagoCustodia;
	}
	public String getFormaPagoFideicomiso() {
		return formaPagoFideicomiso;
	}
	public void setFormaPagoFideicomiso(String formaPagoFideicomiso) {
		this.formaPagoFideicomiso = formaPagoFideicomiso;
	}
	public String getFormaPagoInteres() {
		return formaPagoInteres;
	}
	public void setFormaPagoInteres(String formaPagoInteres) {
		this.formaPagoInteres = formaPagoInteres;
	}
	public String getFormaPagoMora() {
		return formaPagoMora;
	}
	public void setFormaPagoMora(String formaPagoMora) {
		this.formaPagoMora = formaPagoMora;
	}
	public String getFormaPagoGastoCobranza() {
		return formaPagoGastoCobranza;
	}
	public void setFormaPagoGastoCobranza(String formaPagoGastoCobranza) {
		this.formaPagoGastoCobranza = formaPagoGastoCobranza;
	}
	public String getFormaPagoSeguro() {
		return formaPagoSeguro;
	}
	public void setFormaPagoSeguro(String formaPagoSeguro) {
		this.formaPagoSeguro = formaPagoSeguro;
	}
	public String getFormaPagoTasador() {
		return formaPagoTasador;
	}
	public void setFormaPagoTasador(String formaPagoTasador) {
		this.formaPagoTasador = formaPagoTasador;
	}
	public String getFormaPagoTransporte() {
		return formaPagoTransporte;
	}
	public void setFormaPagoTransporte(String formaPagoTransporte) {
		this.formaPagoTransporte = formaPagoTransporte;
	}
	public String getFormaPagoValoracion() {
		return formaPagoValoracion;
	}
	public void setFormaPagoValoracion(String formaPagoValoracion) {
		this.formaPagoValoracion = formaPagoValoracion;
	}
	public BigDecimal getSaldoInteres() {
		return saldoInteres;
	}
	public void setSaldoInteres(BigDecimal saldoInteres) {
		this.saldoInteres = saldoInteres;
	}
	public BigDecimal getSaldoMora() {
		return saldoMora;
	}
	public void setSaldoMora(BigDecimal saldoMora) {
		this.saldoMora = saldoMora;
	}
	public BigDecimal getGastoCobranza() {
		return gastoCobranza;
	}
	public void setGastoCobranza(BigDecimal gastoCobranza) {
		this.gastoCobranza = gastoCobranza;
	}
	public BigDecimal getCuota() {
		return cuota;
	}
	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}
	public BigDecimal getSaldoCapitalRenov() {
		return saldoCapitalRenov;
	}
	public void setSaldoCapitalRenov(BigDecimal saldoCapitalRenov) {
		this.saldoCapitalRenov = saldoCapitalRenov;
	}
	public BigDecimal getMontoPrevioDesembolso() {
		return montoPrevioDesembolso;
	}
	public void setMontoPrevioDesembolso(BigDecimal montoPrevioDesembolso) {
		this.montoPrevioDesembolso = montoPrevioDesembolso;
	}
	public BigDecimal getTotalGastosNuevaOperacion() {
		return totalGastosNuevaOperacion;
	}
	public void setTotalGastosNuevaOperacion(BigDecimal totalGastosNuevaOperacion) {
		this.totalGastosNuevaOperacion = totalGastosNuevaOperacion;
	}
	public BigDecimal getTotalCostosOperacionAnterior() {
		return totalCostosOperacionAnterior;
	}
	public void setTotalCostosOperacionAnterior(BigDecimal totalCostosOperacionAnterior) {
		this.totalCostosOperacionAnterior = totalCostosOperacionAnterior;
	}
	public BigDecimal getCustodiaDevengada() {
		return custodiaDevengada;
	}
	public void setCustodiaDevengada(BigDecimal custodiaDevengada) {
		this.custodiaDevengada = custodiaDevengada;
	}
	public String getFormaPagoCustodiaDevengada() {
		return formaPagoCustodiaDevengada;
	}
	public void setFormaPagoCustodiaDevengada(String formaPagoCustodiaDevengada) {
		this.formaPagoCustodiaDevengada = formaPagoCustodiaDevengada;
	}
	public String getTipooferta() {
		return tipooferta;
	}
	public void setTipooferta(String tipooferta) {
		this.tipooferta = tipooferta;
	}
	public BigDecimal getPorcentajeflujoplaneado() {
		return porcentajeflujoplaneado;
	}
	public void setPorcentajeflujoplaneado(BigDecimal porcentajeflujoplaneado) {
		this.porcentajeflujoplaneado = porcentajeflujoplaneado;
	}
	public BigDecimal getDividendoflujoplaneado() {
		return dividendoflujoplaneado;
	}
	public void setDividendoflujoplaneado(BigDecimal dividendoflujoplaneado) {
		this.dividendoflujoplaneado = dividendoflujoplaneado;
	}
	public BigDecimal getDividendosprorrateoserviciosdiferido() {
		return dividendosprorrateoserviciosdiferido;
	}
	public void setDividendosprorrateoserviciosdiferido(BigDecimal dividendosprorrateoserviciosdiferido) {
		this.dividendosprorrateoserviciosdiferido = dividendosprorrateoserviciosdiferido;
	}


	
}
