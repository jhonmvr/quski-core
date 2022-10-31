package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

public class WrapperMetadatosHabilitante implements Serializable {

	public WrapperMetadatosHabilitante(BigDecimal id, String proceso, String idReferencia, String objectId,
			String nombreArchivo, String tipoDucumento,	BigDecimal idTipoDocumento) {
		super();
		this.id = id;
		this.proceso = proceso;
		this.idReferencia = idReferencia;
		this.objectId = objectId;
		this.nombreArchivo = nombreArchivo;
		this.tipoDucumento = tipoDucumento;
		this.idTipoDocumento = idTipoDocumento;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="id")
	private BigDecimal id;
	
	@Column(name="proceso")
	private String proceso;
	
	@Column(name="id_referencia")
	private String idReferencia;
	
	@Column(name="object_id")
	private String objectId;

	@Column(name="nombre_archivo")
	private String nombreArchivo;
	
	@Column(name="tipo_ducumento")
	private String tipoDucumento;

	@Column(name="id_tipo_documento")
	private BigDecimal idTipoDocumento;
	

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getNombreArchivo() {
		return this.nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getIdReferencia() {
		return idReferencia;
	}

	public void setIdReferencia(String idReferencia) {
		this.idReferencia = idReferencia;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getTipoDocumento() {
		return tipoDucumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDucumento = tipoDocumento;
	}

	public BigDecimal getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(BigDecimal idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

}
