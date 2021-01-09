package com.relative.quski.wrapper;

import java.io.Serializable;

public class ArchivoComprobanteWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileBase64;
	private String name;
	private String process;
	private Long relatedId;
	private String relatedIdStr;
	private String type;
	private Long typeAction;
	
	public String getFileBase64() {
		return fileBase64;
	}
	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public Long getRelatedId() {
		return relatedId;
	}
	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}
	public String getRelatedIdStr() {
		return relatedIdStr;
	}
	public void setRelatedIdStr(String relatedIdStr) {
		this.relatedIdStr = relatedIdStr;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getTypeAction() {
		return typeAction;
	}
	public void setTypeAction(Long typeAction) {
		this.typeAction = typeAction;
	}
}
