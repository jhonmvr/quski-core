package com.relative.quski.wrapper;

import java.io.Serializable;
import java.sql.Timestamp;

import com.relative.quski.enums.ProcesoEnum;

public class ProcesoCaducadoWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoBpm;
	private String codigSoftbank;
	private ProcesoEnum proceso;
	private String aprobador;
	private Timestamp tiempoInicio;
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
	public ProcesoEnum getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}
	public String getAprobador() {
		return aprobador;
	}
	public void setAprobador(String aprobador) {
		this.aprobador = aprobador;
	}
	public Timestamp getTiempoInicio() {
		return tiempoInicio;
	}
	public void setTiempoInicio(Timestamp tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}
	public Long getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}
	public void setTiempoTranscurrido(Long tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}

}
