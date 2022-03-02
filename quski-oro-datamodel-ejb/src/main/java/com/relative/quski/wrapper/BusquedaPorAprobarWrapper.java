package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.enums.ProcesoEnum;



public class BusquedaPorAprobarWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<ProcesoEnum> proceso; 		
	private String codigo;
	private List<Long> idAgencia; 	
	private String cedula;
	private Long numberPage;
	private Long numberItems;
	
	public List<ProcesoEnum> getProceso() {
		return proceso;
	}
	public void setProceso(List<ProcesoEnum> proceso) {
		this.proceso = proceso;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<Long> getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(List<Long> idAgencia) {
		this.idAgencia = idAgencia;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	} 	
	public Long getNumberPage() {
		return numberPage;
	}
	public void setNumberPage(Long numberPage) {
		this.numberPage = numberPage;
	}
	public Long getNumberItems() {
		return numberItems;
	}
	public void setNumberItems(Long numberItems) {
		this.numberItems = numberItems;
	}
	
}
