package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

import java.util.Date;


/**
 * The persistent class for the tb_qo_excepcion_rol database table.
 * 
 */
@Entity
@Table(name="tb_qo_excepcion_rol")
public class TbQoExcepcionRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_EXCEPCION_ROL_ID_GENERATOR", sequenceName="SEQ_EXCEPCION_ROL", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_EXCEPCION_ROL_ID_GENERATOR")
	private long id;
	@Enumerated((EnumType.STRING))
	private EstadoEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="id_rol")
	private String idRol;

	//bi-directional many-to-one association to TbQoExcepcione
	@ManyToOne
	@JoinColumn(name="id_excepcion")
	private TbQoExcepcione tbQoExcepcione;

	public TbQoExcepcionRol() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getIdRol() {
		return this.idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public TbQoExcepcione getTbQoExcepcione() {
		return this.tbQoExcepcione;
	}

	public void setTbQoExcepcione(TbQoExcepcione tbQoExcepcione) {
		this.tbQoExcepcione = tbQoExcepcione;
	}

}