package com.relative.quski.wrapper;

import java.io.Serializable;

public class PerfilesWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ProfileId;
	private Boolean create;
	private Boolean update;
	private Boolean read;
	private Boolean delete;
	
	public String getId() {
		return ProfileId;
	}
	public void setId(String id) {
		this.ProfileId = id;
	}
	public Boolean getCreate() {
		return create;
	}
	public void setCreate(Boolean create) {
		this.create = create;
	}
	public Boolean getUpdate() {
		return update;
	}
	public void setUpdate(Boolean update) {
		this.update = update;
	}
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}
	public Boolean getDelete() {
		return delete;
	}
	public void setDelete(Boolean delete) {
		this.delete = delete;
	}
	

}
