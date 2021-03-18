package com.relative.quski.wrapper;

import java.io.Serializable;


public class ProcesoCaducadoWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoBpm;
	private String codigSoftbank;
	private String proceso;
	private String aprobador;
	private String tiempoInicio;
	private Long tiempoTranscurrido;
	
	public String getCodigoBpm() {
		return codigoBpm;
	}
	public void setCodigoBpm(String codigoBpm) {
		this.codigoBpm = codigoBpm;
	}
	public String getCodigSoftbank() {
		return codigSoftbank;
	}
	public void setCodigSoftbank(String codigSoftbank) {
		this.codigSoftbank = codigSoftbank;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public String getAprobador() {
		return aprobador;
	}
	public void setAprobador(String aprobador) {
		this.aprobador = aprobador;
	}
	public String getTiempoInicio() {
		return tiempoInicio;
	}
	public void setTiempoInicio(String tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}
	public Long getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}
	public void setTiempoTranscurrido(Long tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}

}
