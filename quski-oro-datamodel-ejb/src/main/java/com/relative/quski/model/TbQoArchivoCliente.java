package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_qo_archivo_cliente database table.
 * 
 */
@Entity
@Table(name="tb_qo_archivo_cliente")
public class TbQoArchivoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_ARCHIVO_CLIENTE_ID_GENERATOR", sequenceName="SEQ_ARCHIVO_CLIENTE", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_ARCHIVO_CLIENTE_ID_GENERATOR")
	private Long id;
	
	@Lob
	private byte[] archivo;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="nombre_archivo")
	private String nombreArchivo;

	//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;

	//bi-directional many-to-one association to TbQoTipoArchivo
	@ManyToOne
	@JoinColumn(name="id_tipo_archivo")
	private TbQoTipoArchivo tbQoTipoArchivo;

	public TbQoArchivoCliente() {
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

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
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

	public TbQoCliente getTbQoCliente() {
		return this.tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

	public TbQoTipoArchivo getTbQoTipoArchivo() {
		return this.tbQoTipoArchivo;
	}

	public void setTbQoTipoArchivo(TbQoTipoArchivo tbQoTipoArchivo) {
		this.tbQoTipoArchivo = tbQoTipoArchivo;
	}

}