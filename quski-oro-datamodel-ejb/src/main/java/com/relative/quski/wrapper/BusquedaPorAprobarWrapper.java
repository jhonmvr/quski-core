package com.relative.quski.wrapper;

import java.io.Serializable;
import com.relative.quski.enums.ProcesoEnum;


public class BusquedaPorAprobarWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ProcesoEnum proceso; 		
	private String codigo;
	private Long idAgencia; 	
	private String cedula;
	
	public ProcesoEnum getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Long getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	} 	
	
}
