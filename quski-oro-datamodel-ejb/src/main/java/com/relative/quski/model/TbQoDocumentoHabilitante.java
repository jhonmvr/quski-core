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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.quski.enums.EstadoEnum;


/**
 * The persistent class for the tb_qo_documento_habilitante database table.
 * 
 */
@Entity
@Table(name="tb_qo_documento_habilitante")
@NamedQuery(name="TbQoDocumentoHabilitante.findAll", query="SELECT t FROM TbQoDocumentoHabilitante t")
public class TbQoDocumentoHabilitante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_DOCUMENTO_HABILITANTE_ID_GENERATOR", sequenceName="SEQ_HABILITANTE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_DOCUMENTO_HABILITANTE_ID_GENERATOR")
	private Long id;

	@Lob
	private byte[] archivo;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	
	@ManyToOne
	@JoinColumn(name="id_cotizacion")
	private TbQoCotizador tbQoCotizador;
	
	@ManyToOne
	@JoinColumn(name="id_negociacion")
	private TbQoNegociacion tbQoNegociacion;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;
	

	@Column(name="nombre_archivo")
	private String nombreArchivo;

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
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}


	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
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

	public TbQoTipoDocumento getTbQoTipoDocumento() {
		return this.tbQoTipoDocumento;
	}

	public void setTbQoTipoDocumento(TbQoTipoDocumento tbQoTipoDocumento) {
		this.tbQoTipoDocumento = tbQoTipoDocumento;
	}

	public TbQoCotizador getTbQoCotizador() {
		return tbQoCotizador;
	}

	public void setTbQoCotizador(TbQoCotizador tbQoCotizador) {
		this.tbQoCotizador = tbQoCotizador;
	}

	public TbQoNegociacion getTbQoNegociacion() {
		return tbQoNegociacion;
	}

	public void setTbQoNegociacion(TbQoNegociacion tbQoNegociacion) {
		this.tbQoNegociacion = tbQoNegociacion;
	}

	public TbQoCliente getTbQoCliente() {
		return tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

	
	
}