package com.relative.quski.wrapper;

import java.io.Serializable;

public class ValidarDocumentoWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6260125875802003028L;
	private String identificacion;
	private String nip;
	private String periodo_plazo;
	private String numero_prestamo;
	private Double monto;
	private String nombre;
	private Long plazo;
	private Double total_peso_neto;
	private Double total_valor_avaluo;
	private Double total_valor_realizacion;
	private Double valor_recibir;
	private Double total_peso_bruto;
	private Long total_numero_piezas;
	private String numero_funda;
	private String id_documento;
	private String numero_operacion;
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getPeriodo_plazo() {
		return periodo_plazo;
	}
	public void setPeriodo_plazo(String periodo_plazo) {
		this.periodo_plazo = periodo_plazo;
	}
	public String getNumero_prestamo() {
		return numero_prestamo;
	}
	public void setNumero_prestamo(String numero_prestamo) {
		this.numero_prestamo = numero_prestamo;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getPlazo() {
		return plazo;
	}
	public void setPlazo(Long plazo) {
		this.plazo = plazo;
	}
	public Double getTotal_peso_neto() {
		return total_peso_neto;
	}
	public void setTotal_peso_neto(Double total_peso_neto) {
		this.total_peso_neto = total_peso_neto;
	}
	public Double getTotal_valor_avaluo() {
		return total_valor_avaluo;
	}
	public void setTotal_valor_avaluo(Double total_valor_avaluo) {
		this.total_valor_avaluo = total_valor_avaluo;
	}
	public Double getTotal_valor_realizacion() {
		return total_valor_realizacion;
	}
	public void setTotal_valor_realizacion(Double total_valor_realizacion) {
		this.total_valor_realizacion = total_valor_realizacion;
	}
	public Double getValor_recibir() {
		return valor_recibir;
	}
	public void setValor_recibir(Double valor_recibir) {
		this.valor_recibir = valor_recibir;
	}
	public Double getTotal_peso_bruto() {
		return total_peso_bruto;
	}
	public void setTotal_peso_bruto(Double total_peso_bruto) {
		this.total_peso_bruto = total_peso_bruto;
	}
	public Long getTotal_numero_piezas() {
		return total_numero_piezas;
	}
	public void setTotal_numero_piezas(Long total_numero_piezas) {
		this.total_numero_piezas = total_numero_piezas;
	}
	public String getNumero_funda() {
		return numero_funda;
	}
	public void setNumero_funda(String numero_funda) {
		this.numero_funda = numero_funda;
	}
	public String getId_documento() {
		return id_documento;
	}
	public void setId_documento(String id_documento) {
		this.id_documento = id_documento;
	}
	public String getNumero_operacion() {
		return numero_operacion;
	}
	public void setNumero_operacion(String numero_operacion) {
		this.numero_operacion = numero_operacion;
	}
	
}
