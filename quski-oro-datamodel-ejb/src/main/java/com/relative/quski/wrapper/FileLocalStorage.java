package com.relative.quski.wrapper;

import java.io.Serializable;

import com.relative.quski.enums.EstadoEnum;

public class FileLocalStorage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6552178687524913163L;
	
	private String nombreArchivo;
	private String archivo;
	private EstadoEnum estado;
	private String cuentaMupi;
	private String numeroOperacion;
	private String idPago;
	
	
	public String getCuentaMupi() {
		return cuentaMupi;
	}
	public void setCuentaMupi(String cuentaMupi) {
		this.cuentaMupi = cuentaMupi;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getIdPago() {
		return idPago;
	}
	public void setIdPago(String idPago) {
		this.idPago = idPago;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public EstadoEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoEnum act) {
		this.estado = act;
	}
	
	
	

}
