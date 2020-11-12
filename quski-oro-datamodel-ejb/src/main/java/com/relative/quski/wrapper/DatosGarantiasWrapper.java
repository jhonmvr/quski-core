package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class DatosGarantiasWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigDecimal totaPesoBruto;
	private BigDecimal totalPesoBrutoConFunda; 
	private BigDecimal totalPesoNeto; 
	private BigDecimal totaPesoNetoConFunda; 
	private String codigoTipoFunda; 
	private BigDecimal totalValorAvaluo; 
	private BigDecimal totalValorComercial;
	private BigDecimal totalValorRealizacion;
	private List<JoyaWrapper> garantias;
	private String uriImagenSinFunda;
	private String uriImagenConFunda;
	
	public DatosGarantiasWrapper() {
		super();
	}
	public BigDecimal getTotaPesoBruto() {
		return totaPesoBruto;
	}
	public void setTotaPesoBruto(BigDecimal totaPesoBruto) {
		this.totaPesoBruto = totaPesoBruto;
	}
	public BigDecimal getTotalPesoBrutoConFunda() {
		return totalPesoBrutoConFunda;
	}
	public void setTotalPesoBrutoConFunda(BigDecimal totalPesoBrutoConFunda) {
		this.totalPesoBrutoConFunda = totalPesoBrutoConFunda;
	}
	public BigDecimal getTotalPesoNeto() {
		return totalPesoNeto;
	}
	public void setTotalPesoNeto(BigDecimal totalPesoNeto) {
		this.totalPesoNeto = totalPesoNeto;
	}
	public BigDecimal getTotaPesoNetoConFunda() {
		return totaPesoNetoConFunda;
	}
	public void setTotaPesoNetoConFunda(BigDecimal totaPesoNetoConFunda) {
		this.totaPesoNetoConFunda = totaPesoNetoConFunda;
	}
	public String getCodigoTipoFunda() {
		return codigoTipoFunda;
	}
	public void setCodigoTipoFunda(String codigoTipoFunda) {
		this.codigoTipoFunda = codigoTipoFunda;
	}
	public BigDecimal getTotalValorAvaluo() {
		return totalValorAvaluo;
	}
	public void setTotalValorAvaluo(BigDecimal totalValorAvaluo) {
		this.totalValorAvaluo = totalValorAvaluo;
	}
	public BigDecimal getTotalValorComercial() {
		return totalValorComercial;
	}
	public void setTotalValorComercial(BigDecimal totalValorComercial) {
		this.totalValorComercial = totalValorComercial;
	}
	public BigDecimal getTotalValorRealizacion() {
		return totalValorRealizacion;
	}
	public void setTotalValorRealizacion(BigDecimal totalValorRealizacion) {
		this.totalValorRealizacion = totalValorRealizacion;
	}
	public List<JoyaWrapper> getGarantias() {
		return garantias;
	}
	public void setGarantias(List<JoyaWrapper> garantias) {
		this.garantias = garantias;
	}
	public String getUriImagenSinFunda() {
		return uriImagenSinFunda;
	}
	public void setUriImagenSinFunda(String uriImagenSinFunda) {
		this.uriImagenSinFunda = uriImagenSinFunda;
	}
	public String getUriImagenConFunda() {
		return uriImagenConFunda;
	}
	public void setUriImagenConFunda(String uriImagenConFunda) {
		this.uriImagenConFunda = uriImagenConFunda;
	} 

}
