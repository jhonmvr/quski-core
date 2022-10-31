package com.relative.quski.wrapper;

import java.io.Serializable;

public class VersionWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6552178687524913163L;

	private String Date;
	private String FileBase64;
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getFileBase64() {
		return FileBase64;
	}
	public void setFileBase64(String fileBase64) {
		FileBase64 = fileBase64;
	}
	
	
	
	
}