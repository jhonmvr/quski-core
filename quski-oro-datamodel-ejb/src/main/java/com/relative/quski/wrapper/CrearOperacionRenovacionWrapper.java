package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CrearOperacionRenovacionWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idTipoIdentificacion;
	private String identificacion;
	private String nombreCliente;
	private String fechaEfectiva;
	private String codigoTablaAmortizacionQuski;
	private String codigoTipoPrestamo;
	private String codigoGradoInteres;
	private BigDecimal montoFinanciado;
	private Long pagoDia;
	private GaranteWrapper datosCodeudorApoderado;
	private DatosRegistroWrapper datosRegistro; 
	private List<DatosImpComWrapper> datosImpCom;
	private List<DatosCuentaClienteWrapper> datosCuentaCliente;
	private String numeroOperacionMadre;
	
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
	public String getCodigoTipoPrestamo() {
		return codigoTipoPrestamo;
	}
	public void setCodigoTipoPrestamo(String codigoTipoPrestamo) {
		this.codigoTipoPrestamo = codigoTipoPrestamo;
	}
	public String getCodigoGradoInteres() {
		return codigoGradoInteres;
	}
	public void setCodigoGradoInteres(String codigoGradoInteres) {
		this.codigoGradoInteres = codigoGradoInteres;
	}
	public BigDecimal getMontoFinanciado() {
		return montoFinanciado;
	}
	public void setMontoFinanciado(BigDecimal montoFinanciado) {
		this.montoFinanciado = montoFinanciado;
	}
	public Long getPagoDia() {
		return pagoDia;
	}
	public void setPagoDia(Long pagoDia) {
		this.pagoDia = pagoDia;
	}
	public GaranteWrapper getDatosCodeudorApoderado() {
		return datosCodeudorApoderado;
	}
	public void setDatosCodeudorApoderado(GaranteWrapper datosCodeudorApoderado) {
		this.datosCodeudorApoderado = datosCodeudorApoderado;
	}
	public DatosRegistroWrapper getDatosRegistro() {
		return datosRegistro;
	}
	public void setDatosRegistro(DatosRegistroWrapper datosRegistro) {
		this.datosRegistro = datosRegistro;
	}
	public List<DatosImpComWrapper> getDatosImpCom() {
		return datosImpCom;
	}
	public void setDatosImpCom(List<DatosImpComWrapper> datosImpCom) {
		this.datosImpCom = datosImpCom;
	}
	public List<DatosCuentaClienteWrapper> getDatosCuentaCliente() {
		return datosCuentaCliente;
	}
	public void setDatosCuentaCliente(List<DatosCuentaClienteWrapper> datosCuentaCliente) {
		this.datosCuentaCliente = datosCuentaCliente;
	}
	public String getNumeroOperacionMadre() {
		return numeroOperacionMadre;
	}
	public void setNumeroOperacionMadre(String numeroOperacionMadre) {
		this.numeroOperacionMadre = numeroOperacionMadre;
	}





}
