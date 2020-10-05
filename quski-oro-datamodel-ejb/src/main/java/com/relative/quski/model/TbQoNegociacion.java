package com.relative.quski.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.SituacionEnum;


/**
 * The persistent class for the tb_qo_negociacion database table.
 * 
 */
@Entity
@Table(name="tb_qo_negociacion")
public class TbQoNegociacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_NEGOCIACION_ID_GENERATOR", sequenceName="SEQ_NEGOCIACION"	,initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_NEGOCIACION_ID_GENERATOR")
	private Long id;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="id_asesor")
	private String idAsesor;
	
	@Enumerated(EnumType.STRING)
	private SituacionEnum situacion;

	private String tipo;

	//bi-directional many-to-one association to TbQoCreditoNegociacion
	@OneToMany(mappedBy="tbQoNegociacion")
	private List<TbQoCreditoNegociacion> tbQoCreditoNegociacions;

	//bi-directional many-to-one association to TbQoDocumentoHabilitante
	@OneToMany(mappedBy="tbQoNegociacion")
	private List<TbQoDocumentoHabilitante> tbQoDocumentoHabilitantes;

	//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;

	//bi-directional many-to-one association to TbQoVariablesCrediticia
	@OneToMany(mappedBy="tbQoNegociacion")
	private List<TbQoVariablesCrediticia> tbQoVariablesCrediticias;

	public TbQoNegociacion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SituacionEnum getSituacion() {
		return situacion;
	}

	public void setSituacion(SituacionEnum situacion) {
		this.situacion = situacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getIdAsesor() {
		return this.idAsesor;
	}

	public void setIdAsesor(String idAsesor) {
		this.idAsesor = idAsesor;
	}

	public List<TbQoCreditoNegociacion> getTbQoCreditoNegociacions() {
		return this.tbQoCreditoNegociacions;
	}

	public void setTbQoCreditoNegociacions(List<TbQoCreditoNegociacion> tbQoCreditoNegociacions) {
		this.tbQoCreditoNegociacions = tbQoCreditoNegociacions;
	}

	public TbQoCreditoNegociacion addTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		getTbQoCreditoNegociacions().add(tbQoCreditoNegociacion);
		tbQoCreditoNegociacion.setTbQoNegociacion(this);

		return tbQoCreditoNegociacion;
	}

	public TbQoCreditoNegociacion removeTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		getTbQoCreditoNegociacions().remove(tbQoCreditoNegociacion);
		tbQoCreditoNegociacion.setTbQoNegociacion(null);

		return tbQoCreditoNegociacion;
	}

	public List<TbQoDocumentoHabilitante> getTbQoDocumentoHabilitantes() {
		return this.tbQoDocumentoHabilitantes;
	}

	public void setTbQoDocumentoHabilitantes(List<TbQoDocumentoHabilitante> tbQoDocumentoHabilitantes) {
		this.tbQoDocumentoHabilitantes = tbQoDocumentoHabilitantes;
	}

	public TbQoDocumentoHabilitante addTbQoDocumentoHabilitante(TbQoDocumentoHabilitante tbQoDocumentoHabilitante) {
		getTbQoDocumentoHabilitantes().add(tbQoDocumentoHabilitante);
		tbQoDocumentoHabilitante.setTbQoNegociacion(this);

		return tbQoDocumentoHabilitante;
	}

	public TbQoDocumentoHabilitante removeTbQoDocumentoHabilitante(TbQoDocumentoHabilitante tbQoDocumentoHabilitante) {
		getTbQoDocumentoHabilitantes().remove(tbQoDocumentoHabilitante);
		tbQoDocumentoHabilitante.setTbQoNegociacion(null);

		return tbQoDocumentoHabilitante;
	}

	public TbQoCliente getTbQoCliente() {
		return this.tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

	public List<TbQoVariablesCrediticia> getTbQoVariablesCrediticias() {
		return this.tbQoVariablesCrediticias;
	}

	public void setTbQoVariablesCrediticias(List<TbQoVariablesCrediticia> tbQoVariablesCrediticias) {
		this.tbQoVariablesCrediticias = tbQoVariablesCrediticias;
	}

	public TbQoVariablesCrediticia addTbQoVariablesCrediticia(TbQoVariablesCrediticia tbQoVariablesCrediticia) {
		getTbQoVariablesCrediticias().add(tbQoVariablesCrediticia);
		tbQoVariablesCrediticia.setTbQoNegociacion(this);

		return tbQoVariablesCrediticia;
	}

	public TbQoVariablesCrediticia removeTbQoVariablesCrediticia(TbQoVariablesCrediticia tbQoVariablesCrediticia) {
		getTbQoVariablesCrediticias().remove(tbQoVariablesCrediticia);
		tbQoVariablesCrediticia.setTbQoNegociacion(null);
		return tbQoVariablesCrediticia;
	}

}