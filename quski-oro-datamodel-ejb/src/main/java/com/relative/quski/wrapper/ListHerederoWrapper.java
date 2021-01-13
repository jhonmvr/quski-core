package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class ListHerederoWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private List<HerederoWrapper> heredero;

	public List<HerederoWrapper> getHeredero() {
		return heredero;
	}

	public void setHeredero(List<HerederoWrapper> heredero) {
		this.heredero = heredero;
	}


	
	
}