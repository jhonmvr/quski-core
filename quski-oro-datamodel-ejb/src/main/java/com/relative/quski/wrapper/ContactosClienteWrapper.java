package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class ContactosClienteWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7200331918932476776L;
	private Long id; 
    private String codigoTipoReferencia; 
    private  String nombres;
    private String apellidos; 
    private String direccion;
    private List<TelefonosContactoClienteWrapper>telefonos;
    private Boolean activo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoTipoReferencia() {
		return codigoTipoReferencia;
	}
	public void setCodigoTipoReferencia(String codigoTipoReferencia) {
		this.codigoTipoReferencia = codigoTipoReferencia;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public List<TelefonosContactoClienteWrapper> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(List<TelefonosContactoClienteWrapper> telefonos) {
		this.telefonos = telefonos;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	} 
}
