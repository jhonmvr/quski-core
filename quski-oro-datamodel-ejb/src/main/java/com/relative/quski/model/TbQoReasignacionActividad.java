package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;


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

	public TbQoCreditoNegociacion getTbQoCreditoNegociacion() {
		return this.tbQoCreditoNegociacion;
	}

	public void setTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		this.tbQoCreditoNegociacion = tbQoCreditoNegociacion;
	}

}