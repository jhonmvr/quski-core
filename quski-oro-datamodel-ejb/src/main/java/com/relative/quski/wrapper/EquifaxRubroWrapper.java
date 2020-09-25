package com.relative.quski.wrapper;

import java.io.Serializable;

public class EquifaxRubroWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tiporubroeconomico;
	private Long valor;
	private String tagGrupo;
	
	public String getTiporubroeconomico() {
		return tiporubroeconomico;
	}
	public void setTiporubroeconomico(String tiporubroeconomico) {
		this.tiporubroeconomico = tiporubroeconomico;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	public String getTagGrupo() {
		return tagGrupo;
	}
	public void setTagGrupo(String tagGrupo) {
		this.tagGrupo = tagGrupo;
	}


}
