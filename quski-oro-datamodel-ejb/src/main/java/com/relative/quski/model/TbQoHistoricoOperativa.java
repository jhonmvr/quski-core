package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_qo_historico_operativa database table.
 * 
 */
@Entity
@Table(name="tb_qo_historico_operativa")
@NamedQuery(name="TbQoHistoricoOperativa.findAll", query="SELECT t FROM TbQoHistoricoOperativa t")
public class TbQoHistoricoOperativa implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_HISTORICO_OPERATIVA_ID_GENERATOR", sequenceName="SEQ_HISTORICO_OPERATIVA", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_HISTORICO_OPERATIVA_ID_GENERATOR")
	private Long id;

	private String excepcion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;


	@Column(name="fecha_regularizacion")
	private Date fechaRegularizacion;

	private String usuario;

	//bi-directional many-to-one association to TbQoNegociacion
	@ManyToOne
	@JoinColumn(name="id_credito")
	private TbQoNegociacion tbQoNegociacion;

	public TbQoHistoricoOperativa() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExcepcion() {
		return this.excepcion;
	}

	public void setExcepcion(String excepcion) {
		this.excepcion = excepcion;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaRegularizacion() {
		return this.fechaRegularizacion;
	}

	public void setFechaRegularizacion(Date fechaRegularizacion) {
		this.fechaRegularizacion = fechaRegularizacion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public TbQoNegociacion getTbQoNegociacion() {
		return this.tbQoNegociacion;
	}

	public void setTbQoNegociacion(TbQoNegociacion tbQoNegociacion) {
		this.tbQoNegociacion = tbQoNegociacion;
	}

}