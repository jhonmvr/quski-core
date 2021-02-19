package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoRolTipoDocumento;

public class DocumentoHabilitanteWrapper implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8940327911305978824L;
	
	
	private Long idDocumentoHabilitante;
	private Long idTipoDocumento;
	private String idReferencia;
	private String descripcionTipoDocumento;
	private String objectId;
	private String mimeType;
	private EstadoOperacionEnum estadoOperacion;
	private ProcessEnum proceso;
	private String pantilla;
	private String servicio;
	private Boolean estaCargado;
	private List<TbQoRolTipoDocumento> roles;
	
	
	
	public DocumentoHabilitanteWrapper(Long idTipoDocumento,Long idDocumentoHabilitante, String idReferencia, String descripcionTipoDocumento,
			EstadoOperacionEnum estadoOperacion, ProcessEnum proceso, String pantilla, String servicio, Boolean estaCargado, List<TbQoRolTipoDocumento> roles) {
		super();
		this.idTipoDocumento = idTipoDocumento;
		this.idReferencia = idReferencia;
		this.descripcionTipoDocumento = descripcionTipoDocumento;
		this.estadoOperacion = estadoOperacion;
		this.proceso = proceso;
		this.pantilla = pantilla;
		this.estaCargado = estaCargado;
		this.roles = roles;
		this.idDocumentoHabilitante=idDocumentoHabilitante;
		this.servicio=servicio;
	}
	
	public DocumentoHabilitanteWrapper(Long idTipoDocumento,Long idDocumentoHabilitante, String idReferencia, String objectId,String mimeType, String descripcionTipoDocumento,
			EstadoOperacionEnum estadoOperacion, ProcessEnum proceso, String pantilla,String servicio ) {
		super();
		this.idTipoDocumento = idTipoDocumento;
		this.idReferencia = idReferencia;
		this.descripcionTipoDocumento = descripcionTipoDocumento;
		this.estadoOperacion = estadoOperacion;
		this.proceso = proceso;
		this.pantilla = pantilla;
		this.idDocumentoHabilitante=idDocumentoHabilitante;
		this.objectId=objectId;
		this.mimeType=mimeType;
		this.servicio=servicio;
	}
	
	public DocumentoHabilitanteWrapper(Long idTipoDocumento,  String descripcionTipoDocumento,
			EstadoOperacionEnum estadoOperacion, ProcessEnum proceso, String pantilla,String servicio) {
		super();
		this.idTipoDocumento = idTipoDocumento;
		this.descripcionTipoDocumento = descripcionTipoDocumento;
		this.estadoOperacion = estadoOperacion;
		this.proceso = proceso;
		this.pantilla = pantilla;
		this.servicio=servicio;
	}
	
	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getIdReferencia() {
		return idReferencia;
	}
	public void setIdReferencia(String idReferencia) {
		this.idReferencia = idReferencia;
	}
	public String getDescripcionTipoDocumento() {
		return descripcionTipoDocumento;
	}
	public void setDescripcionTipoDocumento(String descripcionTipoDocumento) {
		this.descripcionTipoDocumento = descripcionTipoDocumento;
	}
	public EstadoOperacionEnum getEstadoOperacion() {
		return estadoOperacion;
	}
	public void setEstadoOperacion(EstadoOperacionEnum estadoOperacion) {
		this.estadoOperacion = estadoOperacion;
	}
	public ProcessEnum getProceso() {
		return proceso;
	}
	public void setProceso(ProcessEnum proceso) {
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
	public List<TbQoRolTipoDocumento> getRoles() {
		return roles;
	}
	public void setRoles(List<TbQoRolTipoDocumento> roles) {
		this.roles = roles;
	}
	
	 public Long getIdDocumentoHabilitante() {
		return idDocumentoHabilitante;
	}

	public void setIdDocumentoHabilitante(Long idDocumentoHabilitante) {
		this.idDocumentoHabilitante = idDocumentoHabilitante;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}



	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}



	public static class DocumentoHabilitanteBuilder {
		private Long idTipoDocumento;
		private Long idDocumentoHabilitante;
		private String idReferencia;
		private String descripcionTipoDocumento;
		private EstadoOperacionEnum estadoOperacion;
		private ProcessEnum proceso;
		private String pantilla;
		private String servicio;
		private Boolean estaCargado;
		private List<TbQoRolTipoDocumento> roles;
		
		public DocumentoHabilitanteBuilder idTipoDocumento(Long idTipoDocumento) {
			this.idTipoDocumento=idTipoDocumento;
			return this;
		}
		
		public DocumentoHabilitanteBuilder idDocumentoHabilitante(Long idDocumentoHabilitante) {
			this.idDocumentoHabilitante=idDocumentoHabilitante;
			return this;
		}
		
		public DocumentoHabilitanteBuilder idReferencia(String idReferencia) {
			this.idReferencia=idReferencia;
			return this;
		}
		
		public DocumentoHabilitanteBuilder descripcionTipoDocumento(String descripcionTipoDocumento) {
			this.descripcionTipoDocumento=descripcionTipoDocumento;
			return this;
		}
		
		public DocumentoHabilitanteBuilder estadoOperacion(EstadoOperacionEnum estadoOperacion) {
			this.estadoOperacion=estadoOperacion;
			return this;
		}
		
		public DocumentoHabilitanteBuilder proceso(ProcessEnum proceso) {
			this.proceso=proceso;
			return this;
		}
		
		public DocumentoHabilitanteBuilder pantilla(String pantilla) {
			this.pantilla=pantilla;
			return this;
		}
		
		public DocumentoHabilitanteBuilder servicio(String servicio) {
			this.servicio=servicio;
			return this;
		}
		
		public DocumentoHabilitanteBuilder estaCargado(Boolean estaCargado) {
			this.estaCargado=estaCargado;
			return this;
		}
		
		public DocumentoHabilitanteBuilder roles(List<TbQoRolTipoDocumento> roles) {
			this.roles=roles;
			return this;
		}
		
		public DocumentoHabilitanteWrapper build() {
			return new DocumentoHabilitanteWrapper(idTipoDocumento,idDocumentoHabilitante, idReferencia, descripcionTipoDocumento, estadoOperacion, proceso, pantilla,servicio, estaCargado, roles);
		}
		
		
		
	 }
	
	
}
