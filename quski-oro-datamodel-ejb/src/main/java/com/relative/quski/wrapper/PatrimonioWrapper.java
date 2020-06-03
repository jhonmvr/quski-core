package com.relative.quski.wrapper;

import java.io.Serializable;

public class PatrimonioWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public PatrimonioWrapper() {
		
	}
	private String activo;
	private String avaluo;
	private String pasivo;
	private Double ifis;
	private Double infocorp;
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getAvaluo() {
		return avaluo;
	}
	public void setAvaluo(String avaluo) {
		this.avaluo = avaluo;
	}
	public String getPasivo() {
		return pasivo;
	}
	public void setPasivo(String pasivo) {
		this.pasivo = pasivo;
	}
	public Double getIfis() {
		return ifis;
	}
	public void setIfis(Double ifis) {
		this.ifis = ifis;
	}
	public Double getInfocorp() {
		return infocorp;
	}
	public void setInfocorp(Double infocorp) {
		this.infocorp = infocorp;
	}
	
}
