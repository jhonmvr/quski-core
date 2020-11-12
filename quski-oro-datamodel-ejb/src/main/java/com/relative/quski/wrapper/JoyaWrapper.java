package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class JoyaWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
    private Long numeroGarantia;
    private Long numeroExpediente;
    private String codigoTipoGarantia;
    private String descripcion;
    private String codigoSubTipoGarantia;
    private String tipoCobertura;
    private BigDecimal valorComercial;
    private BigDecimal valorAvaluo;
    private BigDecimal valorRealizacion;
    private BigDecimal valorOro;
    private String fechaAvaluo;
    private Long idAgenciaRegistro;
    private Long idAgenciaCustodia;
    private String referencia;
    private String codigoTipoJoya;
    private String descripcionJoya;
    private String codigoEstadoJoya;
    private String codigoTipoOro;
    private BigDecimal pesoBruto;
    private Boolean tienePiedras;
    private String detallePiedras;
    private BigDecimal descuentoPiedras;
    private BigDecimal pesoNeto;
    private Long numeroPiezas;
    private BigDecimal descuentoSuelda;
    
	public JoyaWrapper() {
		super();
	}

	public Long getNumeroGarantia() {
		return numeroGarantia;
	}

	public void setNumeroGarantia(Long numeroGarantia) {
		this.numeroGarantia = numeroGarantia;
	}

	public Long getNumeroExpediente() {
		return numeroExpediente;
	}

	public void setNumeroExpediente(Long numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	public String getCodigoTipoGarantia() {
		return codigoTipoGarantia;
	}

	public void setCodigoTipoGarantia(String codigoTipoGarantia) {
		this.codigoTipoGarantia = codigoTipoGarantia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoSubTipoGarantia() {
		return codigoSubTipoGarantia;
	}

	public void setCodigoSubTipoGarantia(String codigoSubTipoGarantia) {
		this.codigoSubTipoGarantia = codigoSubTipoGarantia;
	}

	public String getTipoCobertura() {
		return tipoCobertura;
	}

	public void setTipoCobertura(String tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}

	public BigDecimal getValorComercial() {
		return valorComercial;
	}

	public void setValorComercial(BigDecimal valorComercial) {
		this.valorComercial = valorComercial;
	}

	public BigDecimal getValorAvaluo() {
		return valorAvaluo;
	}

	public void setValorAvaluo(BigDecimal valorAvaluo) {
		this.valorAvaluo = valorAvaluo;
	}

	public BigDecimal getValorRealizacion() {
		return valorRealizacion;
	}

	public void setValorRealizacion(BigDecimal valorRealizacion) {
		this.valorRealizacion = valorRealizacion;
	}

	public BigDecimal getValorOro() {
		return valorOro;
	}

	public void setValorOro(BigDecimal valorOro) {
		this.valorOro = valorOro;
	}

	public String getFechaAvaluo() {
		return fechaAvaluo;
	}

	public void setFechaAvaluo(String fechaAvaluo) {
		this.fechaAvaluo = fechaAvaluo;
	}

	public Long getIdAgenciaRegistro() {
		return idAgenciaRegistro;
	}

	public void setIdAgenciaRegistro(Long idAgenciaRegistro) {
		this.idAgenciaRegistro = idAgenciaRegistro;
	}

	public Long getIdAgenciaCustodia() {
		return idAgenciaCustodia;
	}

	public void setIdAgenciaCustodia(Long idAgenciaCustodia) {
		this.idAgenciaCustodia = idAgenciaCustodia;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCodigoTipoJoya() {
		return codigoTipoJoya;
	}

	public void setCodigoTipoJoya(String codigoTipoJoya) {
		this.codigoTipoJoya = codigoTipoJoya;
	}

	public String getDescripcionJoya() {
		return descripcionJoya;
	}

	public void setDescripcionJoya(String descripcionJoya) {
		this.descripcionJoya = descripcionJoya;
	}

	public String getCodigoEstadoJoya() {
		return codigoEstadoJoya;
	}

	public void setCodigoEstadoJoya(String codigoEstadoJoya) {
		this.codigoEstadoJoya = codigoEstadoJoya;
	}

	public String getCodigoTipoOro() {
		return codigoTipoOro;
	}

	public void setCodigoTipoOro(String codigoTipoOro) {
		this.codigoTipoOro = codigoTipoOro;
	}

	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public Boolean getTienePiedras() {
		return tienePiedras;
	}

	public void setTienePiedras(Boolean tienePiedras) {
		this.tienePiedras = tienePiedras;
	}

	public String getDetallePiedras() {
		return detallePiedras;
	}

	public void setDetallePiedras(String detallePiedras) {
		this.detallePiedras = detallePiedras;
	}

	public BigDecimal getDescuentoPiedras() {
		return descuentoPiedras;
	}

	public void setDescuentoPiedras(BigDecimal descuentoPiedras) {
		this.descuentoPiedras = descuentoPiedras;
	}

	public BigDecimal getPesoNeto() {
		return pesoNeto;
	}

	public void setPesoNeto(BigDecimal pesoNeto) {
		this.pesoNeto = pesoNeto;
	}

	public Long getNumeroPiezas() {
		return numeroPiezas;
	}

	public void setNumeroPiezas(Long numeroPiezas) {
		this.numeroPiezas = numeroPiezas;
	}

	public BigDecimal getDescuentoSuelda() {
		return descuentoSuelda;
	}

	public void setDescuentoSuelda(BigDecimal descuentoSuelda) {
		this.descuentoSuelda = descuentoSuelda;
	}
	

}
