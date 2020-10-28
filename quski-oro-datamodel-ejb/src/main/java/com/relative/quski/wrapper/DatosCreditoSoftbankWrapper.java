package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoRubro;

public class DatosCreditoSoftbankWrapper  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String numeroOperacion;
	private Boolean esProductoOro;
	private String codigoTablaAmortizacionQuski;
	private String tipoCarteraQuski;
	private String montoPreaprobado;
	private String montoAEntregar;
	private String plazo;
	private String estado;
	private String valorDeCuota;
	private String aRecibirCliente;
	private String aPagarPorCliente;
	private String valorCreditoAnterior;
	private String totalDeCostosNuevaOperacion;
	private List<TbQoRubro> rubros; 
	private String saldoCapitalDeOperacionAnterior;
	private String saldoInteresOperacionAnterior;
	private String montoDeDesembolso;
	private String documentosHabilitantes;
	private String ListaNumerosGarantia;
	private String NumeroDeformaNumerada;
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public Boolean getEsProductoOro() {
		return esProductoOro;
	}
	public void setEsProductoOro(Boolean esProductoOro) {
		this.esProductoOro = esProductoOro;
	}
	public String getCodigoTablaAmortizacionQuski() {
		return codigoTablaAmortizacionQuski;
	}
	public void setCodigoTablaAmortizacionQuski(String codigoTablaAmortizacionQuski) {
		this.codigoTablaAmortizacionQuski = codigoTablaAmortizacionQuski;
	}
	public String getTipoCarteraQuski() {
		return tipoCarteraQuski;
	}
	public void setTipoCarteraQuski(String tipoCarteraQuski) {
		this.tipoCarteraQuski = tipoCarteraQuski;
	}
	public String getMontoPreaprobado() {
		return montoPreaprobado;
	}
	public void setMontoPreaprobado(String montoPreaprobado) {
		this.montoPreaprobado = montoPreaprobado;
	}
	public String getMontoAEntregar() {
		return montoAEntregar;
	}
	public void setMontoAEntregar(String montoAEntregar) {
		this.montoAEntregar = montoAEntregar;
	}
	public String getValorDeCuota() {
		return valorDeCuota;
	}
	public void setValorDeCuota(String valorDeCuota) {
		this.valorDeCuota = valorDeCuota;
	}
	public String getaRecibirCliente() {
		return aRecibirCliente;
	}
	public void setaRecibirCliente(String aRecibirCliente) {
		this.aRecibirCliente = aRecibirCliente;
	}
	public String getaPagarPorCliente() {
		return aPagarPorCliente;
	}
	public void setaPagarPorCliente(String aPagarPorCliente) {
		this.aPagarPorCliente = aPagarPorCliente;
	}
	public String getValorCreditoAnterior() {
		return valorCreditoAnterior;
	}
	public void setValorCreditoAnterior(String valorCreditoAnterior) {
		this.valorCreditoAnterior = valorCreditoAnterior;
	}
	public String getTotalDeCostosNuevaOperacion() {
		return totalDeCostosNuevaOperacion;
	}
	public void setTotalDeCostosNuevaOperacion(String totalDeCostosNuevaOperacion) {
		this.totalDeCostosNuevaOperacion = totalDeCostosNuevaOperacion;
	}
	public List<TbQoRubro> getRubros() {
		return rubros;
	}
	public void setRubros(List<TbQoRubro> rubros) {
		this.rubros = rubros;
	}
	public String getSaldoCapitalDeOperacionAnterior() {
		return saldoCapitalDeOperacionAnterior;
	}
	public void setSaldoCapitalDeOperacionAnterior(String saldoCapitalDeOperacionAnterior) {
		this.saldoCapitalDeOperacionAnterior = saldoCapitalDeOperacionAnterior;
	}
	public String getSaldoInteresOperacionAnterior() {
		return saldoInteresOperacionAnterior;
	}
	public void setSaldoInteresOperacionAnterior(String saldoInteresOperacionAnterior) {
		this.saldoInteresOperacionAnterior = saldoInteresOperacionAnterior;
	}
	public String getMontoDeDesembolso() {
		return montoDeDesembolso;
	}
	public void setMontoDeDesembolso(String montoDeDesembolso) {
		this.montoDeDesembolso = montoDeDesembolso;
	}
	public String getDocumentosHabilitantes() {
		return documentosHabilitantes;
	}
	public void setDocumentosHabilitantes(String documentosHabilitantes) {
		this.documentosHabilitantes = documentosHabilitantes;
	}
	public String getListaNumerosGarantia() {
		return ListaNumerosGarantia;
	}
	public void setListaNumerosGarantia(String listaNumerosGarantia) {
		ListaNumerosGarantia = listaNumerosGarantia;
	}
	public String getNumeroDeformaNumerada() {
		return NumeroDeformaNumerada;
	}
	public void setNumeroDeformaNumerada(String numeroDeformaNumerada) {
		NumeroDeformaNumerada = numeroDeformaNumerada;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPlazo() {
		return plazo;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	
	
}
