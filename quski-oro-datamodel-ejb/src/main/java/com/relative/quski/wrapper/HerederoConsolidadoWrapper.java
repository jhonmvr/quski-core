
package com.relative.quski.wrapper;

import java.io.Serializable;

public class HerederoConsolidadoWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String campoCompleto;

	public String getCampoCompleto() {
		return campoCompleto;
	}

	public void setCampoCompleto(String campoCompleto) {
		this.campoCompleto = campoCompleto;
	}

}
