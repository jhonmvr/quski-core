package com.relative.quski.wrapper;

import java.io.Serializable;

import com.relative.quski.enums.EstadoEnum;

public class FileObjectStorageLoad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6552178687524913163L;
	
	private Object _id;
	private String name;
	private String type;
	private EstadoEnum process;
	private Long relatedId;
	private String relatedIdStr;
	private String typeAction;
	private String fileBase64;
	private String typeDocument;
	private String date;

	
	public Object get_id() {
		return _id;
	}
	public void set_id(Object _id) {
		this._id = _id;
	}
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
	public EstadoEnum getProcess() {
		return process;
	}
	public void setProcess(EstadoEnum process) {
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
	public String getTypeAction() {
		return typeAction;
	}
	public void setTypeAction(String typeAction) {
		this.typeAction = typeAction;
	}
	public String getFileBase64() {
		return fileBase64;
	}
	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
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
