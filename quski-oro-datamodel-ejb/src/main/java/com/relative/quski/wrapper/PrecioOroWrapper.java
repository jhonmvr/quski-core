package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class PrecioOroWrapper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6329630401434837187L;
	
	private Long id;
	private Long idCotizador;
	private Long idTipoOro;
	private BigDecimal precio;
	private String quilate;
	
	
	public PrecioOroWrapper(Long id, Long idCotizador, Long idTipoOro, BigDecimal precio, String quilate) {
		super();
		this.id = id;
		this.idCotizador = idCotizador;
		this.idTipoOro = idTipoOro;
		this.precio = precio;
		this.quilate = quilate;
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
	public Long getIdTipoOro() {
		return idTipoOro;
	}
	public void setIdTipoOro(Long idTipoOro) {
		this.idTipoOro = idTipoOro;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public String getQuilate() {
		return quilate;
	}
	public void setQuilate(String quilate) {
		this.quilate = quilate;
	}
	
	
	

}
