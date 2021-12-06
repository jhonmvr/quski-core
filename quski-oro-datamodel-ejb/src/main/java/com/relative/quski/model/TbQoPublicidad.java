package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

import java.util.Date;


/**
 * The persistent class for the tb_qo_variables_crediticias database table.
 * 
 */
@Entity
@Table(name="tb_qo_publicidad")
public class TbQoPublicidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_PUBLICIDAD_ID_GENERATOR", sequenceName="SEQ_PUBLICIDAD", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_PUBLICIDAD_ID_GENERATOR")
	private Long id;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;


	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private String nombre;

	private boolean bandera;

	public Long getId() {
		return id;
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

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isBandera() {
		return bandera;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}


	
	
}