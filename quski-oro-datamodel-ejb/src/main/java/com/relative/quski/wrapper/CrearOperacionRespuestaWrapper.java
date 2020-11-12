package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class CrearOperacionRespuestaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public CrearOperacionRespuestaWrapper() {
		
	}
	private String numeroOperacion;
	private Integer periodo ;
	private Long plazo;
	private String producto;
	private String estado;
	private BigDecimal deudaInicial;
	private BigDecimal montoEntregar;
	private BigDecimal aPagarPorCliente;
	private BigDecimal valorCuota;
	private BigDecimal valorOperacionRenovada;
	private BigDecimal costosOperacion;
	private BigDecimal totalInteresVencimiento;
	private String codigoTablaAmortizacionQuski;
	private String codigoTipoCarteraQuski;
	private Boolean esProductoOro;
	private String uriHabilitantes;
	private DatosImpComWrapper datosImpCom;
	private Boolean existeError;
	private String mensaje;
	private Integer codigoServicio;
	
	
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public Integer getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	public Long getPlazo() {
		return plazo;
	}
	public void setPlazo(Long plazo) {
		this.plazo = plazo;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public BigDecimal getDeudaInicial() {
		return deudaInicial;
	}
	public void setDeudaInicial(BigDecimal deudaInicial) {
		this.deudaInicial = deudaInicial;
	}
	public BigDecimal getMontoEntregar() {
		return montoEntregar;
	}
	public void setMontoEntregar(BigDecimal montoEntregar) {
		this.montoEntregar = montoEntregar;
	}
	public BigDecimal getaPagarPorCliente() {
		return aPagarPorCliente;
	}
	public void setaPagarPorCliente(BigDecimal aPagarPorCliente) {
		this.aPagarPorCliente = aPagarPorCliente;
	}
	public BigDecimal getValorCuota() {
		return valorCuota;
	}
	public void setValorCuota(BigDecimal valorCuota) {
		this.valorCuota = valorCuota;
	}
	public BigDecimal getValorOperacionRenovada() {
		return valorOperacionRenovada;
	}
	public void setValorOperacionRenovada(BigDecimal valorOperacionRenovada) {
		this.valorOperacionRenovada = valorOperacionRenovada;
	}
	public BigDecimal getCostosOperacion() {
		return costosOperacion;
	}
	public void setCostosOperacion(BigDecimal costosOperacion) {
		this.costosOperacion = costosOperacion;
	}
	public BigDecimal getTotalInteresVencimiento() {
		return totalInteresVencimiento;
	}
	public void setTotalInteresVencimiento(BigDecimal totalInteresVencimiento) {
		this.totalInteresVencimiento = totalInteresVencimiento;
	}
	public String getCodigoTablaAmortizacionQuski() {
		return codigoTablaAmortizacionQuski;
	}
	public void setCodigoTablaAmortizacionQuski(String codigoTablaAmortizacionQuski) {
		this.codigoTablaAmortizacionQuski = codigoTablaAmortizacionQuski;
	}
	public String getCodigoTipoCarteraQuski() {
		return codigoTipoCarteraQuski;
	}
	public void setCodigoTipoCarteraQuski(String codigoTipoCarteraQuski) {
		this.codigoTipoCarteraQuski = codigoTipoCarteraQuski;
	}
	public Boolean getEsProductoOro() {
		return esProductoOro;
	}
	public void setEsProductoOro(Boolean esProductoOro) {
		this.esProductoOro = esProductoOro;
	}
	public DatosImpComWrapper getDatosImpCom() {
		return datosImpCom;
	}
	public void setDatosImpCom(DatosImpComWrapper datosImpCom) {
		this.datosImpCom = datosImpCom;
	}
	public Boolean getExisteError() {
		return existeError;
	}
	public void setExisteError(Boolean existeError) {
		this.existeError = existeError;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Integer getCodigoServicio() {
		return codigoServicio;
	}
	public void setCodigoServicio(Integer codigoServicio) {
		this.codigoServicio = codigoServicio;
	}
	public String getUriHabilitantes() {
		return uriHabilitantes;
	}
	public void setUriHabilitantes(String uriHabilitantes) {
		this.uriHabilitantes = uriHabilitantes;
	}
}
