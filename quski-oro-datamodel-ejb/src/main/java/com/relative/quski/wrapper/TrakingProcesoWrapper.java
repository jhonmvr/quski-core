package com.relative.quski.wrapper;

import java.io.Serializable;
import java.sql.Date;

public class TrakingProcesoWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7074898727229426762L;

	private String proceso;
	private String codigoBpm;
	private String codigoSoftbank;
	private Date fechaCreacion;
	private String horaInicio;
	private String horaFin;
	private Long tiempo;
	private String tiempoTranscurrido;
	private String venderdor;
	private String aprobador;
	private String observacion;
	private String actividad;
	private String usuario;
	private String seccion;
	private String area;
	
	public TrakingProcesoWrapper(String codigoBpm, String proceso, String codigoSoftbank, Date fechaCreacion, String actividad, String  tiempoTranscurrido, String  usuario) {
		this.proceso = proceso;
		this.codigoBpm = codigoBpm;
		this.codigoSoftbank = codigoSoftbank;
		this.fechaCreacion = fechaCreacion;
		this.actividad = actividad;
		this.tiempoTranscurrido = tiempoTranscurrido;
		this.usuario = usuario;
		}
		
	public TrakingProcesoWrapper(String codigoBpm,String proceso,  String codigoSoftbank, Date fechaCreacion, String actividad, String  seccion, String  usuario, String  horaInicio, String  horaFin, String  tiempoTranscurrido, String observacion) {
		this.proceso = proceso;
		this.codigoBpm = codigoBpm;
		this.codigoSoftbank = codigoSoftbank;
		this.fechaCreacion = fechaCreacion;
		this.actividad = actividad;
		this.seccion = seccion;
		this.usuario = usuario;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.tiempoTranscurrido = tiempoTranscurrido;
		this.observacion = observacion;
	}
	
	public TrakingProcesoWrapper(String codigoBpm,String proceso,  String seccion, String tiempoTranscurrido) {
		this.proceso = proceso;
		this.codigoBpm = codigoBpm;
		this.seccion = seccion;
		this.tiempoTranscurrido = tiempoTranscurrido;
	}
	
	public TrakingProcesoWrapper( String codigoBpm,String proceso, Date fechaCreacion, String area, String  tiempoTranscurrido) {
		this.proceso = proceso;
		this.codigoBpm = codigoBpm;
		this.fechaCreacion = fechaCreacion;
		this.area = area;
		this.tiempoTranscurrido = tiempoTranscurrido;
	}


	
	
	public TrakingProcesoWrapper( String codigoBpm, String proceso, String codigoSoftbank, Date fechaCreacion,
			String horaInicio, String horaFin, String tiempoTranscurrido, String venderdor, String aprobador,
			String observacion) {
		this.proceso = proceso;
		this.codigoBpm = codigoBpm;
		this.codigoSoftbank = codigoSoftbank;
		this.fechaCreacion = fechaCreacion;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.tiempoTranscurrido = tiempoTranscurrido;
		this.venderdor = venderdor;
		this.aprobador = aprobador;
		this.observacion = observacion;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public String getCodigoBpm() {
		return codigoBpm;
	}
	public void setCodigoBpm(String codigoBpm) {
		this.codigoBpm = codigoBpm;
	}
	public String getCodigoSoftbank() {
		return codigoSoftbank;
	}
	public void setCodigoSoftbank(String codigoSoftbank) {
		this.codigoSoftbank = codigoSoftbank;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getVenderdor() {
		return venderdor;
	}
	public void setVenderdor(String venderdor) {
		this.venderdor = venderdor;
	}
	public String getAprobador() {
		return aprobador;
	}
	public void setAprobador(String aprobador) {
		this.aprobador = aprobador;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Long getTiempo() {
		return tiempo;
	}
	public void setTiempo(Long tiempo) {
		this.tiempo = tiempo;
	}
	public String getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}
	public void setTiempoTranscurrido(String tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
}
