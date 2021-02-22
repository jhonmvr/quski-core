package com.relative.quski.wrapper;

import java.io.Serializable;

public class AbonoWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroOperacion;
	private String referencia;
	private String observacion;
	private String idAgencia;
	private String codigoUsuario;
    private Long idTipoIdentificacion;

	private String identificacion;
    private String fechaRegistro;
    
    public AbonoWrapper(String numeroOperacion, String referencia, String observacion, String idAgencia,
			String codigoUsuario, String identificacion, String nombreCliente, String valor,  String fechaRegistro) {
		super();
		this.numeroOperacion = numeroOperacion;
		this.referencia = referencia;
		this.observacion = observacion;
		this.idAgencia = idAgencia;
		this.codigoUsuario = codigoUsuario;
		this.identificacion = identificacion;
		this.fechaRegistro = fechaRegistro;
		this.nombreCliente = nombreCliente;
		this.valor = valor;
	}
    
    public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(String idAgencia) {
		this.idAgencia = idAgencia;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
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
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	private String nombreCliente;
    private String valor;

}
