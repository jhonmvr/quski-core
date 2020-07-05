package com.relative.quski.wrapper;

import java.io.Serializable;

public class VariableCrediticiaWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8784239006681739583L;
	private Long id;
	private Long idCotizador;
	private String orden;
	private String nombre;
	private String valor;

	public VariableCrediticiaWrapper(Long id, Long idCotizador, String orden, String nombre, String valor) {
		super();
		this.id = id;
		this.idCotizador = idCotizador;
		this.orden = orden;
		this.nombre = nombre;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCotizador() {
		return idCotizador;
	}

	public void setIdCotizador(Long idCotizador) {
		this.idCotizador = idCotizador;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
