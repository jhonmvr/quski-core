package com.relative.quski.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.relative.quski.enums.EstadoEnum;


/**
 * The persistent class for the tb_qo_reasignacion_actividad database table.
 * 
 */
@Entity
@Table(name="tb_qo_reasignacion_actividad")

public class TbQoReasignacionActividad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_REASIGNACION_ACTIVIDAD_ID_GENERATOR", sequenceName="SEQ_REASIGNACION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_REASIGNACION_ACTIVIDAD_ID_GENERATOR")
	private Long id;

	@Column(name="id_usuario_antiguo")
	private String idUsuarioAntiguo;

	@Column(name="id_usuario_nuevo")
	private String idUsuarioNuevo;

	private String observacion;
	
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	//bi-directional many-to-one association to TbQoCreditoNegociacion
	@ManyToOne
	@JoinColumn(name="id_credito_negociacion")
	private TbQoCreditoNegociacion tbQoCreditoNegociacion;

	public TbQoReasignacionActividad() {
	}

	 
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getIdUsuarioAntiguo() {
		return this.idUsuarioAntiguo;
	}

	public void setIdUsuarioAntiguo(String idUsuarioAntiguo) {
		this.idUsuarioAntiguo = idUsuarioAntiguo;
	}

	public String getIdUsuarioNuevo() {
		return this.idUsuarioNuevo;
	}

	public void setIdUsuarioNuevo(String idUsuarioNuevo) {
		this.idUsuarioNuevo = idUsuarioNuevo;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	 

	public EstadoEnum getEstado() {
		return estado;
	}


	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}


	public TbQoCreditoNegociacion getTbQoCreditoNegociacion() {
		return this.tbQoCreditoNegociacion;
	}

	public void setTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		this.tbQoCreditoNegociacion = tbQoCreditoNegociacion;
	}

}