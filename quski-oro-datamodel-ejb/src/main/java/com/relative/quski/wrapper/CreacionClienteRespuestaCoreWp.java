package com.relative.quski.wrapper;

import java.io.Serializable;

public class CreacionClienteRespuestaCoreWp implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Boolean isCore;
	private Boolean isSoftbank;
	
	
	public Boolean getIsCore() {
		return isCore;
	}
	public void setIsCore(Boolean isCore) {
		this.isCore = isCore;
	}
	public Boolean getIsSoftbank() {
		return isSoftbank;
	}
	public void setIsSoftbank(Boolean isSoftbank) {
		this.isSoftbank = isSoftbank;
	}

}
