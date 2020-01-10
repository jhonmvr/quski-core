package com.relative.quski.wrapper;

import java.io.Serializable;

public class ClienteWrapper implements Serializable{
public ClienteWrapper() {
		
	}
	private String nombreCompleto;
	private String edad;
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	
}
