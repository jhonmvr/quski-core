/**
 * 
 */
package com.relative.quski.wrapper;

import java.io.Serializable;

import com.relative.quski.enums.TipoExcepcionEnum;

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
	private TipoExcepcionEnum tipoExcepcion ;
	private String nombreCliente;
	public ExcepcionRolWrapper(Long id, TipoExcepcionEnum tipoExcepcion, String nombreCliente) {
		super();
		this.id = id;
		this.tipoExcepcion = tipoExcepcion;
		this.nombreCliente = nombreCliente;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoExcepcionEnum getTipoExcepcion() {
		return tipoExcepcion;
	}
	public void setTipoExcepcion(TipoExcepcionEnum tipoExcepcion) {
		this.tipoExcepcion = tipoExcepcion;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	
	

}
