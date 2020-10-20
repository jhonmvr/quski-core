package com.relative.quski.wrapper;

import java.io.Serializable;

public class FileWrapper implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8944764688045016002L;
	
	private String name;
	private String type;
	private String process;
	private Long relatedId;
	private String relatedIdStr;
	private String typeAction;
	private String fileBase64;
	private String objectId;
    private Long size;
    private String url;
    private String thumbnailUrl;
    private String deleteUrl;
    private String deleteType;
    private byte[] file;
    private String estadoOperacion;
    
    public FileWrapper() {}
    
    public FileWrapper(String name,Long size,String url,String thumbnailUrl,String deleteUrl,String deleteType,byte[] file){
    	this.name=name;
    	this.size=size;
    	this.url=url;
    	this.thumbnailUrl=thumbnailUrl;
    	this.deleteUrl=deleteUrl;
    	this.deleteType=deleteType;
    	this.file=file;
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public String getDeleteUrl() {
		return deleteUrl;
	}
	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}
	public String getDeleteType() {
		return deleteType;
	}
	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFileBase64() {
		return fileBase64;
	}

	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

	public String getTypeAction() {
		return typeAction;
	}

	public void setTypeAction(String typeAction) {
		this.typeAction = typeAction;
	}

	public String getRelatedIdStr() {
		return relatedIdStr;
	}

	public void setRelatedIdStr(String relatedIdStr) {
		this.relatedIdStr = relatedIdStr;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getEstadoOperacion() {
		return estadoOperacion;
	}

	public void setEstadoOperacion(String estadoOperacion) {
		this.estadoOperacion = estadoOperacion;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
    
	
    

}