package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OperacionGlobalWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombreCliente;
	private String identificacion;
	private Long idTipoIdentificacion;
	private String numeroOperacionMadre;
	private String numeroOperacionMupi;
	private String numeroOperacion;
	private Date fechaSolicitud;
	private Date fechaAprobacion;
	private Date fechaVencimiento;
	private BigDecimal montoFinanciado;
	private BigDecimal montoSolicitado;
	private BigDecimal saldo;
	private Long idAgencia;
	private String codigoUsuarioAsesor;
	private String codigoEstadoOperacion;
	private String tipoCredito;
    private String codigoTipoCarteraQuski;
    private String periodoPlazo;
	private String codigoTipoTablaArmotizacionQuski;
	private Long plazo;
	private Boolean impago;
	private Boolean retanqueo;
	private BigDecimal coberturaInicial;
	private BigDecimal coberturaActual;
	private Long diasMora;
	private String codigoEstadoProcesoGarantia;
	private String codigoEstadoUbicacionGrantia;
	private Boolean esMigrado;
	private Long numeroCuotas;
	private DatosBloqueoWrapper datosBloqueo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public Long getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}
	public void setIdTipoIdentificacion(Long idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}
	public String getNumeroOperacionMadre() {
		return numeroOperacionMadre;
	}
	public void setNumeroOperacionMadre(String numeroOperacionMadre) {
		this.numeroOperacionMadre = numeroOperacionMadre;
	}
	public String getNumeroOperacionMupi() {
		return numeroOperacionMupi;
	}
	public void setNumeroOperacionMupi(String numeroOperacionMupi) {
		this.numeroOperacionMupi = numeroOperacionMupi;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}
	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public BigDecimal getMontoFinanciado() {
		return montoFinanciado;
	}
	public void setMontoFinanciado(BigDecimal montoFinanciado) {
		this.montoFinanciado = montoFinanciado;
	}
	public BigDecimal getMontoSolicitado() {
		return montoSolicitado;
	}
	public void setMontoSolicitado(BigDecimal montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public Long getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}
	public String getCodigoUsuarioAsesor() {
		return codigoUsuarioAsesor;
	}
	public void setCodigoUsuarioAsesor(String codigoUsuarioAsesor) {
		this.codigoUsuarioAsesor = codigoUsuarioAsesor;
	}
	public String getCodigoEstadoOperacion() {
		return codigoEstadoOperacion;
	}
	public void setCodigoEstadoOperacion(String codigoEstadoOperacion) {
		this.codigoEstadoOperacion = codigoEstadoOperacion;
	}
	public String getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	public String getCodigoTipoTablaArmotizacionQuski() {
		return codigoTipoTablaArmotizacionQuski;
	}
	public void setCodigoTipoTablaArmotizacionQuski(String codigoTipoTablaArmotizacionQuski) {
		this.codigoTipoTablaArmotizacionQuski = codigoTipoTablaArmotizacionQuski;
	}
	public Long getPlazo() {
		return plazo;
	}
	public void setPlazo(Long plazo) {
		this.plazo = plazo;
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
	public Long getDiasMora() {
		return diasMora;
	}
	public void setDiasMora(Long diasMora) {
		this.diasMora = diasMora;
	}
	public String getCodigoEstadoProcesoGarantia() {
		return codigoEstadoProcesoGarantia;
	}
	public void setCodigoEstadoProcesoGarantia(String codigoEstadoProcesoGarantia) {
		this.codigoEstadoProcesoGarantia = codigoEstadoProcesoGarantia;
	}
	public String getCodigoEstadoUbicacionGrantia() {
		return codigoEstadoUbicacionGrantia;
	}
	public void setCodigoEstadoUbicacionGrantia(String codigoEstadoUbicacionGrantia) {
		this.codigoEstadoUbicacionGrantia = codigoEstadoUbicacionGrantia;
	}
	public Long getNumeroCuotas() {
		return numeroCuotas;
	}
	public void setNumeroCuotas(Long numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}
	public DatosBloqueoWrapper getDatosBloqueo() {
		return datosBloqueo;
	}
	public void setDatosBloqueo(DatosBloqueoWrapper datosBloqueo) {
		this.datosBloqueo = datosBloqueo;
	}
	public Boolean getImpago() {
		return impago;
	}
	public void setImpago(Boolean impago) {
		this.impago = impago;
	}
	public Boolean getRetanqueo() {
		return retanqueo;
	}
	public void setRetanqueo(Boolean retanqueo) {
		this.retanqueo = retanqueo;
	}
	public Boolean getEsMigrado() {
		return esMigrado;
	}
	public void setEsMigrado(Boolean esMigrado) {
		this.esMigrado = esMigrado;
	}
	public String getCodigoTipoCarteraQuski() {
		return codigoTipoCarteraQuski;
	}
	public void setCodigoTipoCarteraQuski(String codigoTipoCarteraQuski) {
		this.codigoTipoCarteraQuski = codigoTipoCarteraQuski;
	}
	public String getPeriodoPlazo() {
		return periodoPlazo;
	}
	public void setPeriodoPlazo(String periodoPlazo) {
		this.periodoPlazo = periodoPlazo;
	}
}
