package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;


public class BusquedaOperacionesWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ProcesoEnum proceso; 		// Proceso //
	private Date fechaCreacionDesde;	// Proceso //
	private Date fechaCreacionHasta; 	// Proceso //
	private List<EstadoProcesoEnum> estado;   // Proceso //
	private String asesor; 				// Proceso // 
	
	private String codigoBpm; 				// Varia // 
	private String codigoSoft; 				// Varia // 

	private String nombreCompleto; 		// Cliente //
	private String identificacion; 		// Cliente //
	private String actividad; 			// Tracking //
	private Long numberPage;
	private Long numberItems;
	
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public ProcesoEnum getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}
	public Date getFechaCreacionDesde() {
		return fechaCreacionDesde;
	}
	public void setFechaCreacionDesde(Date fechaCreacionDesde) {
		this.fechaCreacionDesde = fechaCreacionDesde;
	}
	public Date getFechaCreacionHasta() {
		return fechaCreacionHasta;
	}
	public void setFechaCreacionHasta(Date fechaCreacionHasta) {
		this.fechaCreacionHasta = fechaCreacionHasta;
	}
	public List<EstadoProcesoEnum> getEstado() {
		return estado;
	}
	public void setEstado(List<EstadoProcesoEnum> estado) {
		this.estado = estado;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public String getAsesor() {
		return asesor;
	}
	public void setAsesor(String asesor) {
		this.asesor = asesor;
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
	public String getCodigoBpm() {
		return codigoBpm;
	}
	public void setCodigoBpm(String codigoBpm) {
		this.codigoBpm = codigoBpm;
	}
	public String getCodigoSoft() {
		return codigoSoft;
	}
	public void setCodigoSoft(String codigoSoft) {
		this.codigoSoft = codigoSoft;
	}
	

}
