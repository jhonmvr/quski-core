package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class TipoOroWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3962306281827331375L;
	
	
	private String codigo;
	private String nombre;
	private float valorOro;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getValorOro() {
		return valorOro;
	}
	public void setValorOro(float valorOro) {
		this.valorOro = valorOro;
	}
	
	
	

}
