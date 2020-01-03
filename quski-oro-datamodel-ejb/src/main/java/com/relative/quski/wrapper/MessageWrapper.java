package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;

public class MessageWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4200706203534999302L;
	
	private String mensaje;
	private String codigo;
	private Date fecha;
	private Long idReferencia;
	private String sessionIdSender;
	private String sessionIdReceiver;
	private Long counter;
	private Boolean addCount;
	private String hash;
	private String lastId;
	private Boolean finished;
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Long getIdReferencia() {
		return idReferencia;
	}
	public void setIdReferencia(Long idReferencia) {
		this.idReferencia = idReferencia;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getSessionIdReceiver() {
		return sessionIdReceiver;
	}
	public void setSessionIdReceiver(String sessionIdReceiver) {
		this.sessionIdReceiver = sessionIdReceiver;
	}
	public String getSessionIdSender() {
		return sessionIdSender;
	}
	public void setSessionIdSender(String sessionIdSender) {
		this.sessionIdSender = sessionIdSender;
	}
	public Long getCounter() {
		return counter;
	}
	public void setCounter(Long counter) {
		this.counter = counter;
	}
	public Boolean getAddCount() {
		return addCount;
	}
	public void setAddCount(Boolean addCount) {
		this.addCount = addCount;
	}
	public String getLastId() {
		return lastId;
	}
	public void setLastId(String lastId) {
		this.lastId = lastId;
	}
	public Boolean getFinished() {
		return finished;
	}
	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
	

}
