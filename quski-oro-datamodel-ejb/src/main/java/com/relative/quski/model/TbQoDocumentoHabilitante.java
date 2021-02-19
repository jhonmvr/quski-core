package com.relative.quski.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcessEnum;


/**
 * The persistent class for the tb_qo_documento_habilitante database table.
 * 
 */
@Entity
@Table(name="tb_qo_documento_habilitante")
public class TbQoDocumentoHabilitante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_DOCUMENTO_HABILITANTE_ID_GENERATOR", sequenceName="SEQ_HABILITANTE",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_DOCUMENTO_HABILITANTE_ID_GENERATOR")
	private Long id;

	@Lob
	private byte[] archivo;
	
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	
	@Enumerated(EnumType.STRING)
	private ProcessEnum proceso;
	
	@Column(name="id_referencia")
	private String idReferencia;
	
	@Column(name="object_id")
	private String objectId;
	
	@Column(name="estado_operacion")
	@Enumerated(EnumType.STRING)
	private EstadoOperacionEnum estadoOperacion;


	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="nombre_archivo")
	private String nombreArchivo;
	
	@Column(name="tipo_ducumento")
	private String tipoDocumento;

	//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;

	//bi-directional many-to-one association to TbQoCotizador
	@ManyToOne
	@JoinColumn(name="id_cotizacion")
	private TbQoCotizador tbQoCotizador;

	//bi-directional many-to-one association to TbQoNegociacion
	@ManyToOne
	@JoinColumn(name="id_negociacion")
	private TbQoNegociacion tbQoNegociacion;

	//bi-directional many-to-one association to TbQoTipoDocumento
	@ManyToOne
	@JoinColumn(name="id_tipo_documento")
	private TbQoTipoDocumento tbQoTipoDocumento;
	
	
	
	

	public TbQoDocumentoHabilitante() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getArchivo() {
		return this.archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public EstadoEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoEnum act) {
		this.estado = act;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombreArchivo() {
		return this.nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	

	public ProcessEnum getProceso() {
		return proceso;
	}

	public void setProceso(ProcessEnum proceso) {
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

	public EstadoOperacionEnum getEstadoOperacion() {
		return estadoOperacion;
	}

	public void setEstadoOperacion(EstadoOperacionEnum estadoOperacion) {
		this.estadoOperacion = estadoOperacion;
	}

	public TbQoCliente getTbQoCliente() {
		return this.tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

	public TbQoCotizador getTbQoCotizador() {
		return this.tbQoCotizador;
	}

	public void setTbQoCotizador(TbQoCotizador tbQoCotizador) {
		this.tbQoCotizador = tbQoCotizador;
	}

	public TbQoNegociacion getTbQoNegociacion() {
		return this.tbQoNegociacion;
	}

	public void setTbQoNegociacion(TbQoNegociacion tbQoNegociacion) {
		this.tbQoNegociacion = tbQoNegociacion;
	}

	public TbQoTipoDocumento getTbQoTipoDocumento() {
		return this.tbQoTipoDocumento;
	}

	public void setTbQoTipoDocumento(TbQoTipoDocumento tbQoTipoDocumento) {
		this.tbQoTipoDocumento = tbQoTipoDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	
	

}