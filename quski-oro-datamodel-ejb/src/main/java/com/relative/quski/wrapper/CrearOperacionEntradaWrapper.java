package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

import com.relative.quski.enums.EstadoEnum;

public class CrearOperacionEntradaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public CrearOperacionEntradaWrapper() {
		
	}
	private Long idTipoIdentificacion;
	private String identificacion;
	private String nombreCliente;
	private Boolean esProductoOro;
	private String fechaEfectiva;
	private String codigoTablaAmortizacionQuski;
	private String codigoTipoCarteraQuski;
	private String codigoTipoPrestamo;
	private BigDecimal montoFinanciado;
	private Integer pagoDia;
	private String codigoGradoInteres;
	private Double valorCuota;
	private EstadoEnum estado;
	private DatosRegistroWrapper datosRegistro; 
	private DatosImpComWrapper datosImpCom;
	private DatosCuentaClienteWrapper datosCuentaCliente;
	private GarantiasWrapper garantias;
	public Long getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}
	public void setIdTipoIdentificacion(Long idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public Boolean getEsProductoOro() {
		return esProductoOro;
	}
	public void setEsProductoOro(Boolean esProductoOro) {
		this.esProductoOro = esProductoOro;
	}
	public String getFechaEfectiva() {
		return fechaEfectiva;
	}
	public void setFechaEfectiva(String fechaEfectiva) {
		this.fechaEfectiva = fechaEfectiva;
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
	public String getCodigoTipoPrestamo() {
		return codigoTipoPrestamo;
	}
	public void setCodigoTipoPrestamo(String codigoTipoPrestamo) {
		this.codigoTipoPrestamo = codigoTipoPrestamo;
	}
	public BigDecimal getMontoFinanciado() {
		return montoFinanciado;
	}
	public void setMontoFinanciado(BigDecimal montoFinanciado) {
		this.montoFinanciado = montoFinanciado;
	}
	public Integer getPagoDia() {
		return pagoDia;
	}
	public void setPagoDia(Integer pagoDia) {
		this.pagoDia = pagoDia;
	}
	public String getCodigoGradoInteres() {
		return codigoGradoInteres;
	}
	public void setCodigoGradoInteres(String codigoGradoInteres) {
		this.codigoGradoInteres = codigoGradoInteres;
	}
	public Double getValorCuota() {
		return valorCuota;
	}
	public void setValorCuota(Double valorCuota) {
		this.valorCuota = valorCuota;
	}
	public EstadoEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}
	public DatosRegistroWrapper getDatosRegistro() {
		return datosRegistro;
	}
	public void setDatosRegistro(DatosRegistroWrapper datosRegistro) {
		this.datosRegistro = datosRegistro;
	}
	public DatosImpComWrapper getDatosImpCom() {
		return datosImpCom;
	}
	public void setDatosImpCom(DatosImpComWrapper datosImpCom) {
		this.datosImpCom = datosImpCom;
	}
	public DatosCuentaClienteWrapper getDatosCuentaCliente() {
		return datosCuentaCliente;
	}
	public void setDatosCuentaCliente(DatosCuentaClienteWrapper datosCuentaCliente) {
		this.datosCuentaCliente = datosCuentaCliente;
	}
	public GarantiasWrapper getGarantias() {
		return garantias;
	}
	public void setGarantias(GarantiasWrapper garantias) {
		this.garantias = garantias;
	} 
	
	
	
	

}
