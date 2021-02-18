package com.relative.quski.wrapper;

import java.io.Serializable;

public class ObjetoHabilitanteWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ObjetoHabilitanteWrapper() {
		
	}
	byte[] documentoHabilitanteByte;
	
	
	public byte[] getDocumentoHabilitanteByte() {
		return documentoHabilitanteByte;
	}
	public void setDocumentoHabilitanteByte(byte[] documentoHabilitanteByte) {
		this.documentoHabilitanteByte = documentoHabilitanteByte;
	}

}
