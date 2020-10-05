package com.relative.quski.wrapper;

import java.io.Serializable;

public class CrmProspectoCortoWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombreCompleto;
    private String phoneHome;
    private String phoneMobile;
    private String phoneOther;
    private String phoneWork;
    private String cedula;
    private String email;
    
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getPhoneHome() {
		return phoneHome;
	}
	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}
	public String getPhoneMobile() {
		return phoneMobile;
	}
	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}
	public String getPhoneOther() {
		return phoneOther;
	}
	public void setPhoneOther(String phoneOther) {
		this.phoneOther = phoneOther;
	}
	public String getPhoneWork() {
		return phoneWork;
	}
	public void setPhoneWork(String phoneWork) {
		this.phoneWork = phoneWork;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
