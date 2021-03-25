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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.TipoExcepcionEnum;


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
	private Long id;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	
	@Enumerated(EnumType.STRING)
	private TipoExcepcionEnum excepcion;


	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private String rol;

	public TbQoExcepcionRol() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public TipoExcepcionEnum getExcepcion() {
		return excepcion;
	}

	public void setExcepcion(TipoExcepcionEnum excepcion) {
		this.excepcion = excepcion;
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

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}