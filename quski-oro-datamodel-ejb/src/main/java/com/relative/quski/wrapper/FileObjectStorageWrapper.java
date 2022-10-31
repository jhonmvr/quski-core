package com.relative.quski.wrapper;

import java.io.Serializable;

public class FileObjectStorageWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	private String process;
	private Long relateId;
	private String relateIdStr;
	private String typeAction;
	private String typeDocument;
	private String date;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public Long getRelateId() {
		return relateId;
	}
	public void setRelateId(Long relateId) {
		this.relateId = relateId;
	}
	public String getRelateIdStr() {
		return relateIdStr;
	}
	public void setRelateIdStr(String relateIdStr) {
		this.relateIdStr = relateIdStr;
	}
	public String getTypeAction() {
		return typeAction;
	}
	public void setTypeAction(String typeAction) {
		this.typeAction = typeAction;
	}
	public String getTypeDocument() {
		return typeDocument;
	}
	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
