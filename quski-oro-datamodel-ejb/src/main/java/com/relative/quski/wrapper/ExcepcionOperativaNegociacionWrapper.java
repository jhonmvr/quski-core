package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

import com.relative.quski.model.TbQoExcepcionOperativa;

public class ExcepcionOperativaNegociacionWrapper implements Serializable {

	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ClienteYReferidoWrapper opcionCredito;
	private TbQoExcepcionOperativa ex;
	public ExcepcionOperativaNegociacionWrapper(ClienteYReferidoWrapper opcionCredito, TbQoExcepcionOperativa ex) {
		super();
		this.opcionCredito = opcionCredito;
		this.ex = ex;
	}
	
	public ExcepcionOperativaNegociacionWrapper() {
		super();
	}

	public ClienteYReferidoWrapper getOpcionCredito() {
		return opcionCredito;
	}
	public void setOpcionCredito(ClienteYReferidoWrapper opcionCredito) {
		this.opcionCredito = opcionCredito;
	}
	public TbQoExcepcionOperativa getEx() {
		return ex;
	}
	public void setEx(TbQoExcepcionOperativa ex) {
		this.ex = ex;
	}
	
}
