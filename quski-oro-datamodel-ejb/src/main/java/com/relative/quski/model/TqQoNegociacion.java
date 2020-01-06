package com.relative.quski.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tq_qo_negociacion database table.
 * 
 */
@Entity
@Table(name="tq_qo_negociacion")
@NamedQuery(name="TqQoNegociacion.findAll", query="SELECT t FROM TqQoNegociacion t")
public class TqQoNegociacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TQ_QO_NEGOCIACION_ID_GENERATOR", sequenceName="SEQ_NEGOCIACION", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TQ_QO_NEGOCIACION_ID_GENERATOR")
	private Long id;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	//bi-directional many-to-one association to TbQoVariablesCrediticia
	@OneToMany(mappedBy="tqQoNegociacion")
	private List<TbQoVariableCrediticia> tbQoVariablesCrediticias;

	//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="tb_qo_cliente_id")
	private TbQoCliente tbQoCliente;

	public TqQoNegociacion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<TbQoVariableCrediticia> getTbQoVariablesCrediticias() {
		return this.tbQoVariablesCrediticias;
	}

	public void setTbQoVariablesCrediticias(List<TbQoVariableCrediticia> tbQoVariablesCrediticias) {
		this.tbQoVariablesCrediticias = tbQoVariablesCrediticias;
	}

	public TbQoVariableCrediticia addTbQoVariablesCrediticia(TbQoVariableCrediticia tbQoVariablesCrediticia) {
		getTbQoVariablesCrediticias().add(tbQoVariablesCrediticia);
		tbQoVariablesCrediticia.setTqQoNegociacion(this);

		return tbQoVariablesCrediticia;
	}

	public TbQoVariableCrediticia removeTbQoVariablesCrediticia(TbQoVariableCrediticia tbQoVariablesCrediticia) {
		getTbQoVariablesCrediticias().remove(tbQoVariablesCrediticia);
		tbQoVariablesCrediticia.setTqQoNegociacion(null);

		return tbQoVariablesCrediticia;
	}

	public TbQoCliente getTbQoCliente() {
		return this.tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

}