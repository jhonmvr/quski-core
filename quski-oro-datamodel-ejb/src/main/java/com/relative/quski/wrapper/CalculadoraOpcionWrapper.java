package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraOpcionWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	  private Integer plazo;        
      private String periodoPlazo;     
      private String periodicidadPlazo; 
      private Double montoFinanciado;
      private Double valorARecibir;    
      private Double valorAPagar;   
      private Double costoCustodia;
      private Double costoFideicomiso; 
      private Double costoSeguro;  
      private Double costoTasacion;
      private Double costoTransporte;
      private Double costoValoracion;
      private Double impuestoSolca;
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
      private Double saldoInteres;
      private Double saldoMora;
      private Double gastoCobranza;
      private Double cuota;
      private Double saldoCapitalRenov;
      private Double montoPrevioDesembolso;
      private Double totalGastosNuevaOperacion;
      private Double totalCostosOperacionAnterior;
      private Double custodiaDevengada;
      private String formaPagoCustodiaDevengada;
      private String tipoOferta;
      private Integer porcetajeFlujoPlaneado; 
      private Integer dividendoFlujoPlaneado; 
      private Integer dividendoProrrateoServiciosDiferido;
      
      
    public static List<CalculadoraOpcionWrapper> generateMockup() {
    	List<CalculadoraOpcionWrapper> tmp = new ArrayList<>();
		CalculadoraOpcionWrapper o = new CalculadoraOpcionWrapper();
		o.setPlazo( 90 );
		o.setPeriodoPlazo("D");     
	    o.setPeriodicidadPlazo("M"); 
	    o.setMontoFinanciado( 480.80 );
	    o.setValorARecibir( 421.50 );    
	    o.setValorAPagar( 0.00 );   
	    o.setCostoCustodia( 29.68 );
	    o.setCostoFideicomiso( 2.69 ); 
	    o.setCostoSeguro( 0.38 );  
	    o.setCostoTasacion( 25.95 );
	    o.setCostoTransporte( 0.00 );
	    o.setCostoValoracion(0.00 );
	    o.setImpuestoSolca( 0.60 );
	    o.setFormaPagoImpuestoSolca("C");
	    o.setFormaPagoCapital("C");
	    o.setFormaPagoCustodia("C");
	    o.setFormaPagoFideicomiso("C");
	    o.setFormaPagoInteres("C");
	    o.setFormaPagoMora("C");
	    o.setFormaPagoGastoCobranza("C");
	    o.setFormaPagoSeguro("C");
	    o.setFormaPagoTasador("C");
	    o.setFormaPagoTransporte("C");
	    o.setFormaPagoValoracion("C");
	    o.setSaldoInteres( 0.00 );
	    o.setSaldoMora( 0.00 );
	    o.setGastoCobranza( 0.00);
	    o.setCuota( 500.10 );
	    o.setSaldoCapitalRenov( 0.00 );
	    o.setMontoPrevioDesembolso( 0.00 );
	    o.setTotalGastosNuevaOperacion( 58.70 );
	    o.setTotalCostosOperacionAnterior( 0.00 );
	    o.setCustodiaDevengada( 0.00 );
	    o.setFormaPagoCustodiaDevengada("C");
	    o.setTipoOferta("N");
	    o.setPorcetajeFlujoPlaneado( 0 ); 
	    o.setDividendoFlujoPlaneado( 999 ); 
	    o.setDividendoProrrateoServiciosDiferido( 0 );
	    tmp.add(o);
	    CalculadoraOpcionWrapper a = new CalculadoraOpcionWrapper();
		a.setPlazo( 90 );
		a.setPeriodoPlazo("D");     
	    a.setPeriodicidadPlazo("M"); 
	    a.setMontoFinanciado( 480.80 );
	    a.setValorARecibir( 421.50 );    
	    a.setValorAPagar( 0.00 );   
	    a.setCostoCustodia( 29.68 );
	    a.setCostoFideicomiso( 2.69 ); 
	    a.setCostoSeguro( 0.38 );  
	    a.setCostoTasacion( 25.95 );
	    a.setCostoTransporte( 0.00 );
	    a.setCostoValoracion(0.00 );
	    a.setImpuestoSolca( 0.60 );
	    a.setFormaPagoImpuestoSolca("C");
	    a.setFormaPagoCapital("C");
	    a.setFormaPagoCustodia("C");
	    a.setFormaPagoFideicomiso("C");
	    a.setFormaPagoInteres("C");
	    a.setFormaPagoMora("C");
	    a.setFormaPagoGastoCobranza("C");
	    a.setFormaPagoSeguro("C");
	    a.setFormaPagoTasador("C");
	    a.setFormaPagoTransporte("C");
	    a.setFormaPagoValoracion("C");
	    a.setSaldoInteres( 0.00 );
	    a.setSaldoMora( 0.00 );
	    a.setGastoCobranza( 0.00);
	    a.setCuota( 500.10 );
	    a.setSaldoCapitalRenov( 0.00 );
	    a.setMontoPrevioDesembolso( 0.00 );
	    a.setTotalGastosNuevaOperacion( 58.70 );
	    a.setTotalCostosOperacionAnterior( 0.00 );
	    a.setCustodiaDevengada( 0.00 );
	    a.setFormaPagoCustodiaDevengada("C");
	    a.setTipoOferta("N");
	    a.setPorcetajeFlujoPlaneado( 0 ); 
	    a.setDividendoFlujoPlaneado( 999 ); 
	    a.setDividendoProrrateoServiciosDiferido( 0 );
	    tmp.add(a);
	    CalculadoraOpcionWrapper e = new CalculadoraOpcionWrapper();
		e.setPlazo( 90 );
		e.setPeriodoPlazo("D");     
	    e.setPeriodicidadPlazo("M"); 
	    e.setMontoFinanciado( 480.80 );
	    e.setValorARecibir( 421.50 );    
	    e.setValorAPagar( 0.00 );   
	    e.setCostoCustodia( 29.68 );
	    e.setCostoFideicomiso( 2.69 ); 
	    e.setCostoSeguro( 0.38 );  
	    e.setCostoTasacion( 25.95 );
	    e.setCostoTransporte( 0.00 );
	    e.setCostoValoracion(0.00 );
	    e.setImpuestoSolca( 0.60 );
	    e.setFormaPagoImpuestoSolca("C");
	    e.setFormaPagoCapital("C");
	    e.setFormaPagoCustodia("C");
	    e.setFormaPagoFideicomiso("C");
	    e.setFormaPagoInteres("C");
	    e.setFormaPagoMora("C");
	    e.setFormaPagoGastoCobranza("C");
	    e.setFormaPagoSeguro("C");
	    e.setFormaPagoTasador("C");
	    e.setFormaPagoTransporte("C");
	    e.setFormaPagoValoracion("C");
	    e.setSaldoInteres( 0.00 );
	    e.setSaldoMora( 0.00 );
	    e.setGastoCobranza( 0.00);
	    e.setCuota( 500.10 );
	    e.setSaldoCapitalRenov( 0.00 );
	    e.setMontoPrevioDesembolso( 0.00 );
	    e.setTotalGastosNuevaOperacion( 58.70 );
	    e.setTotalCostosOperacionAnterior( 0.00 );
	    e.setCustodiaDevengada( 0.00 );
	    e.setFormaPagoCustodiaDevengada("C");
	    e.setTipoOferta("N");
	    e.setPorcetajeFlujoPlaneado( 0 ); 
	    e.setDividendoFlujoPlaneado( 999 ); 
	    e.setDividendoProrrateoServiciosDiferido( 0 );
	    tmp.add(e);
    	return tmp;
    }
	public Integer getPlazo() {
		return plazo;
	}
	public void setPlazo(Integer plazo) {
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
	public Double getMontoFinanciado() {
		return montoFinanciado;
	}
	public void setMontoFinanciado(Double montoFinanciado) {
		this.montoFinanciado = montoFinanciado;
	}
	public Double getValorARecibir() {
		return valorARecibir;
	}
	public void setValorARecibir(Double valorARecibir) {
		this.valorARecibir = valorARecibir;
	}
	public Double getValorAPagar() {
		return valorAPagar;
	}
	public void setValorAPagar(Double valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
	public Double getCostoCustodia() {
		return costoCustodia;
	}
	public void setCostoCustodia(Double costoCustodia) {
		this.costoCustodia = costoCustodia;
	}
	public Double getCostoFideicomiso() {
		return costoFideicomiso;
	}
	public void setCostoFideicomiso(Double costoFideicomiso) {
		this.costoFideicomiso = costoFideicomiso;
	}
	public Double getCostoSeguro() {
		return costoSeguro;
	}
	public void setCostoSeguro(Double costoSeguro) {
		this.costoSeguro = costoSeguro;
	}
	public Double getCostoTasacion() {
		return costoTasacion;
	}
	public void setCostoTasacion(Double costoTasacion) {
		this.costoTasacion = costoTasacion;
	}
	public Double getCostoTransporte() {
		return costoTransporte;
	}
	public void setCostoTransporte(Double costoTransporte) {
		this.costoTransporte = costoTransporte;
	}
	public Double getCostoValoracion() {
		return costoValoracion;
	}
	public void setCostoValoracion(Double costoValoracion) {
		this.costoValoracion = costoValoracion;
	}
	public Double getImpuestoSolca() {
		return impuestoSolca;
	}
	public void setImpuestoSolca(Double impuestoSolca) {
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
	public Double getSaldoInteres() {
		return saldoInteres;
	}
	public void setSaldoInteres(Double saldoInteres) {
		this.saldoInteres = saldoInteres;
	}
	public Double getSaldoMora() {
		return saldoMora;
	}
	public void setSaldoMora(Double saldoMora) {
		this.saldoMora = saldoMora;
	}
	public Double getGastoCobranza() {
		return gastoCobranza;
	}
	public void setGastoCobranza(Double gastoCobranza) {
		this.gastoCobranza = gastoCobranza;
	}
	public Double getCuota() {
		return cuota;
	}
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
	public Double getSaldoCapitalRenov() {
		return saldoCapitalRenov;
	}
	public void setSaldoCapitalRenov(Double saldoCapitalRenov) {
		this.saldoCapitalRenov = saldoCapitalRenov;
	}
	public Double getMontoPrevioDesembolso() {
		return montoPrevioDesembolso;
	}
	public void setMontoPrevioDesembolso(Double montoPrevioDesembolso) {
		this.montoPrevioDesembolso = montoPrevioDesembolso;
	}
	public Double getTotalGastosNuevaOperacion() {
		return totalGastosNuevaOperacion;
	}
	public void setTotalGastosNuevaOperacion(Double totalGastosNuevaOperacion) {
		this.totalGastosNuevaOperacion = totalGastosNuevaOperacion;
	}
	public Double getTotalCostosOperacionAnterior() {
		return totalCostosOperacionAnterior;
	}
	public void setTotalCostosOperacionAnterior(Double totalCostosOperacionAnterior) {
		this.totalCostosOperacionAnterior = totalCostosOperacionAnterior;
	}
	public Double getCustodiaDevengada() {
		return custodiaDevengada;
	}
	public void setCustodiaDevengada(Double custodiaDevengada) {
		this.custodiaDevengada = custodiaDevengada;
	}
	public String getFormaPagoCustodiaDevengada() {
		return formaPagoCustodiaDevengada;
	}
	public void setFormaPagoCustodiaDevengada(String formaPagoCustodiaDevengada) {
		this.formaPagoCustodiaDevengada = formaPagoCustodiaDevengada;
	}
	public String getTipoOferta() {
		return tipoOferta;
	}
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}
	public Integer getPorcetajeFlujoPlaneado() {
		return porcetajeFlujoPlaneado;
	}
	public void setPorcetajeFlujoPlaneado(Integer porcetajeFlujoPlaneado) {
		this.porcetajeFlujoPlaneado = porcetajeFlujoPlaneado;
	}
	public Integer getDividendoFlujoPlaneado() {
		return dividendoFlujoPlaneado;
	}
	public void setDividendoFlujoPlaneado(Integer dividendoFlujoPlaneado) {
		this.dividendoFlujoPlaneado = dividendoFlujoPlaneado;
	}
	public Integer getDividendoProrrateoServiciosDiferido() {
		return dividendoProrrateoServiciosDiferido;
	}
	public void setDividendoProrrateoServiciosDiferido(Integer dividendoProrrateoServiciosDiferido) {
		this.dividendoProrrateoServiciosDiferido = dividendoProrrateoServiciosDiferido;
	}
}
