package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class DocumentoHabilitanteWrapper implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8940327911305978824L;
	
	private Long idTipoDocumento;
	private Long idReferencia;
	private String descripcionTipoDocumento;
	private String estadoOperacion;
	private String proceso;
	private String pantilla;
	private Boolean estaCargado;
	private List<Long> roles;
	
	
	
	public DocumentoHabilitanteWrapper(Long idTipoDocumento, Long idReferencia, String descripcionTipoDocumento,
			String estadoOperacion, String proceso, String pantilla, Boolean estaCargado, List<Long> roles) {
		super();
		this.idTipoDocumento = idTipoDocumento;
		this.idReferencia = idReferencia;
		this.descripcionTipoDocumento = descripcionTipoDocumento;
		this.estadoOperacion = estadoOperacion;
		this.proceso = proceso;
		this.pantilla = pantilla;
		this.estaCargado = estaCargado;
		this.roles = roles;
	}
	
	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public Long getIdReferencia() {
		return idReferencia;
	}
	public void setIdReferencia(Long idReferencia) {
		this.idReferencia = idReferencia;
	}
	public String getDescripcionTipoDocumento() {
		return descripcionTipoDocumento;
	}
	public void setDescripcionTipoDocumento(String descripcionTipoDocumento) {
		this.descripcionTipoDocumento = descripcionTipoDocumento;
	}
	public String getEstadoOperacion() {
		return estadoOperacion;
	}
	public void setEstadoOperacion(String estadoOperacion) {
		this.estadoOperacion = estadoOperacion;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public String getPantilla() {
		return pantilla;
	}
	public void setPantilla(String pantilla) {
		this.pantilla = pantilla;
	}
	public Boolean getEstaCargado() {
		return estaCargado;
	}
	public void setEstaCargado(Boolean estaCargado) {
		this.estaCargado = estaCargado;
	}
	public List<Long> getRoles() {
		return roles;
	}
	public void setRoles(List<Long> roles) {
		this.roles = roles;
	}
	
	 public static class DocumentoHabilitanteBuilder {
		private Long idTipoDocumento;
		private Long idReferencia;
		private String descripcionTipoDocumento;
		private String estadoOperacion;
		private String proceso;
		private String pantilla;
		private Boolean estaCargado;
		private List<Long> roles;
		
		public DocumentoHabilitanteBuilder idTipoDocumento(Long idTipoDocumento) {
			this.idTipoDocumento=idTipoDocumento;
			return this;
		}
		
		public DocumentoHabilitanteBuilder idReferencia(Long idReferencia) {
			this.idReferencia=idReferencia;
			return this;
		}
		
		public DocumentoHabilitanteBuilder descripcionTipoDocumento(String descripcionTipoDocumento) {
			this.descripcionTipoDocumento=descripcionTipoDocumento;
			return this;
		}
		
		public DocumentoHabilitanteBuilder estadoOperacion(String estadoOperacion) {
			this.estadoOperacion=estadoOperacion;
			return this;
		}
		
		public DocumentoHabilitanteBuilder proceso(String proceso) {
			this.proceso=proceso;
			return this;
		}
		
		public DocumentoHabilitanteBuilder pantilla(String pantilla) {
			this.pantilla=pantilla;
			return this;
		}
		
		public DocumentoHabilitanteBuilder estaCargado(Boolean estaCargado) {
			this.estaCargado=estaCargado;
			return this;
		}
		
		public DocumentoHabilitanteBuilder roles(List<Long> roles) {
			this.roles=roles;
			return this;
		}
		
		public DocumentoHabilitanteWrapper build() {
			return new DocumentoHabilitanteWrapper(idTipoDocumento, idReferencia, descripcionTipoDocumento, estadoOperacion, proceso, pantilla, estaCargado, roles);
		}
		
		
		
	 }
	
	
}
