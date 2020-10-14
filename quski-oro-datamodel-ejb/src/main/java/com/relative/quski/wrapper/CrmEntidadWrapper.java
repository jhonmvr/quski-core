package com.relative.quski.wrapper;

import java.io.Serializable;

public class CrmEntidadWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private String firstName;      
	private String phoneHome;         
	private String phoneMobile;         
	private String phoneOther;          
	private String phoneWork;           
	private String cedulaC;         
	private String leadSourceDescription;       
	private String emailAddress;           
	private String emailAddressCaps;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public String getCedulaC() {
		return cedulaC;
	}
	public void setCedulaC(String cedulaC) {
		this.cedulaC = cedulaC;
	}
	public String getLeadSourceDescription() {
		return leadSourceDescription;
	}
	public void setLeadSourceDescription(String leadSourceDescription) {
		this.leadSourceDescription = leadSourceDescription;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getEmailAddressCaps() {
		return emailAddressCaps;
	}
	public void setEmailAddressCaps(String emailAddressCaps) {
		this.emailAddressCaps = emailAddressCaps;
	}        



	
}