/**
 * 
 */
package com.relative.quski.wrapper;

import java.io.Serializable;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
public class ExcepcionRolWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String tipoExcepcion ;
	private String nombreCliente;
	private String apellidoCliente;
	private Long idNegociacion;
	private String identificacion;
	private String nombreCompleto;
	public ExcepcionRolWrapper(Long id, String tipoExcepcion, String nombreCliente,String apellidoCliente,Long idNegociacion, String identificacion,String nombreCompleto) {
		super();
		this.id = id;
		this.tipoExcepcion = tipoExcepcion;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente=apellidoCliente;
		this.idNegociacion=idNegociacion;
		this.identificacion=identificacion;
		this.setNombreCompleto(nombreCompleto);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoExcepcion() {
		return tipoExcepcion;
	}
	public void setTipoExcepcion(String tipoExcepcion) {
		this.tipoExcepcion = tipoExcepcion;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getApellidoCliente() {
		return apellidoCliente;
	}
	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}
	public Long getIdNegociacion() {
		return idNegociacion;
	}
	public void setIdNegociacion(Long idNegociacion) {
		this.idNegociacion = idNegociacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	
	
	

}
