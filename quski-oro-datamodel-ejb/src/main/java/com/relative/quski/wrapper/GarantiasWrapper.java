package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

import com.relative.quski.enums.EstadoEnum;

public class GarantiasWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public GarantiasWrapper() {
		
	}
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

}
