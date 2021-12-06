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
@Table(name="tb_qo_referido")
public class TbQoReferido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_REFERIDO_ID_GENERATOR", sequenceName="SEQ_REFERIDO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_REFERIDO_ID_GENERATOR")
	private Long id;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;


	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="nombre")
	private String nombre;

	@Column(name="telefono")
	private String telefono;

	
	@ManyToOne
	@JoinColumn(name = "id_negociacion")
	private TbQoNegociacion tbQoNegociacion;


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


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public TbQoNegociacion getTbQoNegociacion() {
		return tbQoNegociacion;
	}


	public void setTbQoNegociacion(TbQoNegociacion tbQoNegociacion) {
		this.tbQoNegociacion = tbQoNegociacion;
	}
	

	
	
}