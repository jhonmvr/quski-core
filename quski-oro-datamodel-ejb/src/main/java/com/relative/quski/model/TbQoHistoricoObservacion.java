package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_qo_historico_observacion database table.
 * 
 */
@Entity
@Table(name="tb_qo_historico_observacion")
@NamedQuery(name="TbQoHistoricoObservacion.findAll", query="SELECT t FROM TbQoHistoricoObservacion t")
public class TbQoHistoricoObservacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6407675881469561584L;

	@Id
	@SequenceGenerator(name = "TB_QO_HISTORICO_OBSERVACION_ID_GENERATOR", sequenceName = "SEQ_HISTORICO_OBSERVACION", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QO_HISTORICO_OBSERVACION_ID_GENERATOR")
	private Long id;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	private String observacion;

	private String usuario;

	//bi-directional many-to-one association to TbQoCreditoNegociacion
	@ManyToOne
	@JoinColumn(name="id_credito")
	private TbQoCreditoNegociacion tbQoCreditoNegociacion;

	public TbQoHistoricoObservacion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public TbQoCreditoNegociacion getTbQoCreditoNegociacion() {
		return this.tbQoCreditoNegociacion;
	}

	public void setTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		this.tbQoCreditoNegociacion = tbQoCreditoNegociacion;
	}

}