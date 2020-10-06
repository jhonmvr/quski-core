package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;


public class IntegracionRubroWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tiporubroeconomico;
    private BigDecimal valor;
    private String tagGrupo;
    
    
	public String getTiporubroeconomico() {
		return tiporubroeconomico;
	}
	public void setTiporubroeconomico(String tiporubroeconomico) {
		this.tiporubroeconomico = tiporubroeconomico;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getTagGrupo() {
		return tagGrupo;
	}
	public void setTagGrupo(String tagGrupo) {
		this.tagGrupo = tagGrupo;
	}

}
