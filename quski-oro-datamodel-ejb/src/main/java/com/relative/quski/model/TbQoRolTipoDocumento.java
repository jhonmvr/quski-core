package com.relative.quski.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcessEnum;


/**
 * The persistent class for the tb_qo_tipo_joya database table.
 * 
 */
@Entity
@Table(name="tb_qo_rol_tipo_documento")
public class TbQoRolTipoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TbQoRolTipoDocumento", sequenceName="seq_rol_tipo_documento")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TbQoRolTipoDocumento")
	private Long id;

	@Column(name = "id_tipo_documento")
	private Long idTipoDocumento;

	@Column(name = "id_rol")
	private Long idRol;
	
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	
	@Enumerated(EnumType.STRING)
	private ProcessEnum proceso;
	
	@Column(name="estado_operacion")
	@Enumerated(EnumType.STRING)
	private EstadoOperacionEnum estadoOperacion;

	@Column(name="descarga_plantilla")
	private Boolean descargaPlantilla;
	
	@Column(name="carga_documento")
	private Boolean cargaDocumento;
	
	@Column(name="descarga_documento")
	private Boolean descargaDocumento;
	
	@Column(name="carga_documento_adicional")
	private Boolean cargaDocumentoAdicional;
	
	@Column(name="descarga_documento_adicional")
	private Boolean descargaDocumentoAdicional;
	
	@Column(name="accion_uno")
	private Boolean accionUno;
	
	@Column(name="accion_dos")
	private Boolean accionDos;
	
	@Column(name="accion_tres")
	private Boolean accionTres;
	

	public TbQoRolTipoDocumento() {
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}



	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}



	public Long getIdRol() {
		return idRol;
	}



	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}



	public EstadoEnum getEstado() {
		return estado;
	}



	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}



	public ProcessEnum getProceso() {
		return proceso;
	}



	public void setProceso(ProcessEnum proceso) {
		this.proceso = proceso;
	}



	public EstadoOperacionEnum getEstadoOperacion() {
		return estadoOperacion;
	}



	public void setEstadoOperacion(EstadoOperacionEnum estadoOperacion) {
		this.estadoOperacion = estadoOperacion;
	}



	public Boolean getDescargaPlantilla() {
		return descargaPlantilla;
	}



	public void setDescargaPlantilla(Boolean descargaPlantilla) {
		this.descargaPlantilla = descargaPlantilla;
	}



	public Boolean getCargaDocumento() {
		return cargaDocumento;
	}



	public void setCargaDocumento(Boolean cargaDocumento) {
		this.cargaDocumento = cargaDocumento;
	}



	public Boolean getDescargaDocumento() {
		return descargaDocumento;
	}



	public void setDescargaDocumento(Boolean descargaDocumento) {
		this.descargaDocumento = descargaDocumento;
	}



	public Boolean getCargaDocumentoAdicional() {
		return cargaDocumentoAdicional;
	}



	public void setCargaDocumentoAdicional(Boolean cargaDocumentoAdicional) {
		this.cargaDocumentoAdicional = cargaDocumentoAdicional;
	}



	public Boolean getDescargaDocumentoAdicional() {
		return descargaDocumentoAdicional;
	}



	public void setDescargaDocumentoAdicional(Boolean descargaDocumentoAdicional) {
		this.descargaDocumentoAdicional = descargaDocumentoAdicional;
	}



	public Boolean getAccionUno() {
		return accionUno;
	}



	public void setAccionUno(Boolean accionUno) {
		this.accionUno = accionUno;
	}



	public Boolean getAccionDos() {
		return accionDos;
	}



	public void setAccionDos(Boolean accionDos) {
		this.accionDos = accionDos;
	}



	public Boolean getAccionTres() {
		return accionTres;
	}



	public void setAccionTres(Boolean accionTres) {
		this.accionTres = accionTres;
	}

	
	

}