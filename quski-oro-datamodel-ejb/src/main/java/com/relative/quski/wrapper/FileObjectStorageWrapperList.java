package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class FileObjectStorageWrapperList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FileObjectStorageWrapper> items;
	
	public List<FileObjectStorageWrapper> getItems() {
		return items;
	}
	public void setItems(List<FileObjectStorageWrapper> items) {
		this.items = items;
	}

}
