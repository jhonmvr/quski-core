package com.relative.quski.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the tb_qo_agencia database table.
 * 
 */
@Entity
@Table(name="tb_qo_agencia")
@NamedQuery(name="TbQoAgencia.findAll", query="SELECT t FROM TbQoAgencia t")
public class TbQoAgencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_AGENCIA_ID_GENERATOR", sequenceName="SEQ_TB_QO_AGENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_AGENCIA_ID_GENERATOR")
	private long id;

	@Column(name="direccion_agencia")
	private String direccionAgencia;

	@Column(name="nombre_agencia")
	private String nombreAgencia;

	//bi-directional many-to-one association to Canton
	@ManyToOne
	@JoinColumn(name="id_canton")
	private Canton canton;

	//bi-directional many-to-one association to Parroquia
	@ManyToOne
	@JoinColumn(name="id_parroquia")
	private Parroquia parroquia;

	//bi-directional many-to-one association to Provincia
	@ManyToOne
	@JoinColumn(name="id_provincia")
	private Provincia provincia;
	
	@OneToMany(mappedBy="tbQoAgencia")
	private List<TbQoCreditoNegociacion> tbQoCreditoNegociacions;

	public TbQoAgencia() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDireccionAgencia() {
		return this.direccionAgencia;
	}

	public void setDireccionAgencia(String direccionAgencia) {
		this.direccionAgencia = direccionAgencia;
	}

	public String getNombreAgencia() {
		return this.nombreAgencia;
	}

	public void setNombreAgencia(String nombreAgencia) {
		this.nombreAgencia = nombreAgencia;
	}

	public Canton getCanton() {
		return this.canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	public Parroquia getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public List<TbQoCreditoNegociacion> getTbQoCreditoNegociacions() {
		return this.tbQoCreditoNegociacions;
	}
	public void setTbQoCreditoNegociacions(List<TbQoCreditoNegociacion> tbQoCreditoNegociacions) {
		this.tbQoCreditoNegociacions = tbQoCreditoNegociacions;
	}
	
	public TbQoCreditoNegociacion addTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		getTbQoCreditoNegociacions().add(tbQoCreditoNegociacion);
		tbQoCreditoNegociacion.setTbQoAgencia(this);

		return tbQoCreditoNegociacion;
	}
	public TbQoCreditoNegociacion removeTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		getTbQoCreditoNegociacions().remove(tbQoCreditoNegociacion);
		tbQoCreditoNegociacion.setTbQoAgencia(null);

		return tbQoCreditoNegociacion;
	}
}