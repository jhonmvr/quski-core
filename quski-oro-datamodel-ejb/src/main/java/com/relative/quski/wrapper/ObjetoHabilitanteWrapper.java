package com.relative.quski.wrapper;

import java.io.Serializable;

public class ObjetoHabilitanteWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ObjetoHabilitanteWrapper() {
		
	}
	private byte[] documentoHabilitanteByte;
	private String objectoStorage;
	
	
	
	public String getObjectoStorage() {
		return objectoStorage;
	}
	public void setObjectoStorage(String objectoStorage) {
		this.objectoStorage = objectoStorage;
	}
	public byte[] getDocumentoHabilitanteByte() {
		return documentoHabilitanteByte;
	}
	public void setDocumentoHabilitanteByte(byte[] documentoHabilitanteByte) {
		this.documentoHabilitanteByte = documentoHabilitanteByte;
	}

}
