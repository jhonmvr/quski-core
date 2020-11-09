package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.relative.quski.model.TbQoRubro;

public class DatosCreditoSoftbankWrapper  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String numeroOperacion;
	private Boolean esProductoOro;
	private String codigoTablaAmortizacionQuski;
	private String tipoCarteraQuski;
	private BigDecimal montoPreaprobado;
	private BigDecimal montoAEntregar;
	private Long plazo;
	private String tipoOperacion;
	private String estado;
	private Date fechaEfectiva;
	private Date fechaVencimiento;
	private BigDecimal valorDeCuota;
	private String destinoOperacion;
	private BigDecimal riesgoTotalCliente;
	private BigDecimal aRecibirCliente;
	private BigDecimal aPagarPorCliente;
	private BigDecimal valorCreditoAnterior;
	private BigDecimal totalDeCostosNuevaOperacion;
	private List<TbQoRubro> rubros; 
	private BigDecimal saldoCapitalDeOperacionAnterior;
	private BigDecimal saldoInteresOperacionAnterior;
	private BigDecimal montoDeDesembolso;
	private String documentosHabilitantes;
	private String ListaNumerosGarantia;
	private String NumeroDeformaNumerada;
	
	private Long numeroFunda;
	private String pesoFunda;
	private String descripcionProducto;
	
	public Long getNumeroFunda() {
		return numeroFunda;
	}
	public void setNumeroFunda(Long numeroFunda) {
		this.numeroFunda = numeroFunda;
	}
	public String getPesoFunda() {
		return pesoFunda;
	}
	public void setPesoFunda(String pesoFunda) {
		this.pesoFunda = pesoFunda;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
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
	
	public List<TbQoRubro> getRubros() {
		return rubros;
	}
	public void setRubros(List<TbQoRubro> rubros) {
		this.rubros = rubros;
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

	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Date getFechaEfectiva() {
		return fechaEfectiva;
	}
	public void setFechaEfectiva(Date fechaEfectiva) {
		this.fechaEfectiva = fechaEfectiva;
	}
	public String getDestinoOperacion() {
		return destinoOperacion;
	}
	public void setDestinoOperacion(String destinoOperacion) {
		this.destinoOperacion = destinoOperacion;
	}
	public BigDecimal getMontoPreaprobado() {
		return montoPreaprobado;
	}
	public void setMontoPreaprobado(BigDecimal montoPreaprobado) {
		this.montoPreaprobado = montoPreaprobado;
	}
	public BigDecimal getMontoAEntregar() {
		return montoAEntregar;
	}
	public void setMontoAEntregar(BigDecimal montoAEntregar) {
		this.montoAEntregar = montoAEntregar;
	}
	public Long getPlazo() {
		return plazo;
	}
	public void setPlazo(Long plazo) {
		this.plazo = plazo;
	}
	public BigDecimal getValorDeCuota() {
		return valorDeCuota;
	}
	public void setValorDeCuota(BigDecimal valorDeCuota) {
		this.valorDeCuota = valorDeCuota;
	}
	public BigDecimal getaRecibirCliente() {
		return aRecibirCliente;
	}
	public void setaRecibirCliente(BigDecimal aRecibirCliente) {
		this.aRecibirCliente = aRecibirCliente;
	}
	public BigDecimal getaPagarPorCliente() {
		return aPagarPorCliente;
	}
	public void setaPagarPorCliente(BigDecimal aPagarPorCliente) {
		this.aPagarPorCliente = aPagarPorCliente;
	}
	public BigDecimal getValorCreditoAnterior() {
		return valorCreditoAnterior;
	}
	public void setValorCreditoAnterior(BigDecimal valorCreditoAnterior) {
		this.valorCreditoAnterior = valorCreditoAnterior;
	}
	public BigDecimal getTotalDeCostosNuevaOperacion() {
		return totalDeCostosNuevaOperacion;
	}
	public void setTotalDeCostosNuevaOperacion(BigDecimal totalDeCostosNuevaOperacion) {
		this.totalDeCostosNuevaOperacion = totalDeCostosNuevaOperacion;
	}
	public BigDecimal getSaldoCapitalDeOperacionAnterior() {
		return saldoCapitalDeOperacionAnterior;
	}
	public void setSaldoCapitalDeOperacionAnterior(BigDecimal saldoCapitalDeOperacionAnterior) {
		this.saldoCapitalDeOperacionAnterior = saldoCapitalDeOperacionAnterior;
	}
	public BigDecimal getSaldoInteresOperacionAnterior() {
		return saldoInteresOperacionAnterior;
	}
	public void setSaldoInteresOperacionAnterior(BigDecimal saldoInteresOperacionAnterior) {
		this.saldoInteresOperacionAnterior = saldoInteresOperacionAnterior;
	}
	public BigDecimal getMontoDeDesembolso() {
		return montoDeDesembolso;
	}
	public void setMontoDeDesembolso(BigDecimal montoDeDesembolso) {
		this.montoDeDesembolso = montoDeDesembolso;
	}
	public BigDecimal getRiesgoTotalCliente() {
		return riesgoTotalCliente;
	}
	public void setRiesgoTotalCliente(BigDecimal riesgoTotalCliente) {
		this.riesgoTotalCliente = riesgoTotalCliente;
	}

	
	
}
