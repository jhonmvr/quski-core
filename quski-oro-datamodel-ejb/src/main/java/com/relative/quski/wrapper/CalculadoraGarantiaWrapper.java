package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraGarantiaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;


	private String tipoJoya;
	private String descripcion;
	private String estadoJoya;
	private String tipoOroKilataje;
	private Double pesoGr;
	private String tienePiedras;
	private String detallePiedras;
	private Double descuentoPesoPiedras;
	private Double pesoNeto;
	private Double valorOro;
	private Double valorAvaluo;
	private Double valorAplicable;
	private Double precioOro;
	private Double valorAplicableCredito;
	private Double valorRealizacion;
	private Integer numeroPiezas;
	private Double descuentoSuelda;
    
    public static List<CalculadoraGarantiaWrapper> generateMockup() {
    	List<CalculadoraGarantiaWrapper> tmp = new ArrayList<>();
    	CalculadoraGarantiaWrapper o = new CalculadoraGarantiaWrapper();
    	o.setTipoJoya( "ANI" );
		o.setDescripcion( "BUEN ESTADO" );
		o.setEstadoJoya( "BUE" );
		o.setTipoOroKilataje( "18K" );
		o.setPesoGr( 0.73 );
		o.setTienePiedras( "S" );
		o.setDetallePiedras( "PIEDRAS" );
		o.setDescuentoPesoPiedras( 0.73 );
		o.setPesoNeto( 5.00 );
		o.setPrecioOro( 263.72 );
		o.setValorAplicableCredito( 293.02 );
		o.setValorRealizacion( 232.07 );
		o.setNumeroPiezas( 1 );
		o.setDescuentoSuelda( 0.00 );
		tmp.add(o);
		return tmp;
    }

	public String getTipoJoya() {
		return tipoJoya;
	}

	public void setTipoJoya(String tipoJoya) {
		this.tipoJoya = tipoJoya;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstadoJoya() {
		return estadoJoya;
	}

	public void setEstadoJoya(String estadoJoya) {
		this.estadoJoya = estadoJoya;
	}

	public String getTipoOroKilataje() {
		return tipoOroKilataje;
	}

	public void setTipoOroKilataje(String tipoOroKilataje) {
		this.tipoOroKilataje = tipoOroKilataje;
	}

	public Double getPesoGr() {
		return pesoGr;
	}

	public void setPesoGr(Double pesoGr) {
		this.pesoGr = pesoGr;
	}

	public String getTienePiedras() {
		return tienePiedras;
	}

	public void setTienePiedras(String tienePiedras) {
		this.tienePiedras = tienePiedras;
	}

	public String getDetallePiedras() {
		return detallePiedras;
	}

	public void setDetallePiedras(String detallePiedras) {
		this.detallePiedras = detallePiedras;
	}

	public Double getDescuentoPesoPiedras() {
		return descuentoPesoPiedras;
	}

	public void setDescuentoPesoPiedras(Double descuentoPesoPiedras) {
		this.descuentoPesoPiedras = descuentoPesoPiedras;
	}

	public Double getPesoNeto() {
		return pesoNeto;
	}

	public void setPesoNeto(Double pesoNeto) {
		this.pesoNeto = pesoNeto;
	}

	public Double getPrecioOro() {
		return precioOro;
	}

	public void setPrecioOro(Double precioOro) {
		this.precioOro = precioOro;
	}

	public Double getValorAplicableCredito() {
		return valorAplicableCredito;
	}

	public void setValorAplicableCredito(Double valorAplicableCredito) {
		this.valorAplicableCredito = valorAplicableCredito;
	}

	public Double getValorRealizacion() {
		return valorRealizacion;
	}

	public void setValorRealizacion(Double valorRealizacion) {
		this.valorRealizacion = valorRealizacion;
	}

	public Integer getNumeroPiezas() {
		return numeroPiezas;
	}

	public void setNumeroPiezas(Integer numeroPiezas) {
		this.numeroPiezas = numeroPiezas;
	}

	public Double getDescuentoSuelda() {
		return descuentoSuelda;
	}

	public void setDescuentoSuelda(Double descuentoSuelda) {
		this.descuentoSuelda = descuentoSuelda;
	}

	public Double getValorOro() {
		return valorOro;
	}

	public void setValorOro(Double valorOro) {
		this.valorOro = valorOro;
	}

	public Double getValorAvaluo() {
		return valorAvaluo;
	}

	public void setValorAvaluo(Double valorAvaluo) {
		this.valorAvaluo = valorAvaluo;
	}

	public Double getValorAplicable() {
		return valorAplicable;
	}

	public void setValorAplicable(Double valorAplicable) {
		this.valorAplicable = valorAplicable;
	}
	
}
