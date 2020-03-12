package com.relative.quski.model;

import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_qo_agencia database table.
 * 
 */
@Entity
@Table(name="tb_qo_agencia")
public class TbQoAgencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_AGENCIA_ID_GENERATOR", sequenceName="SEG_QO_AGENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_AGENCIA_ID_GENERATOR")
	private long id;

	@Column(name="direccion_agencia")
	private String direccionAgencia;

	@Column(name="id_canton")
	private BigDecimal idCanton;

	@Column(name="id_parroquia")
	private BigDecimal idParroquia;

	@Column(name="id_provincia")
	private BigDecimal idProvincia;

	@Column(name="nombre_agencia")
	private String nombreAgencia;

	//bi-directional many-to-one association to TbQoCreditoNegociacion
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

	public BigDecimal getIdCanton() {
		return this.idCanton;
	}

	public void setIdCanton(BigDecimal idCanton) {
		this.idCanton = idCanton;
	}

	public BigDecimal getIdParroquia() {
		return this.idParroquia;
	}

	public void setIdParroquia(BigDecimal idParroquia) {
		this.idParroquia = idParroquia;
	}

	public BigDecimal getIdProvincia() {
		return this.idProvincia;
	}

	public void setIdProvincia(BigDecimal idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombreAgencia() {
		return this.nombreAgencia;
	}

	public void setNombreAgencia(String nombreAgencia) {
		this.nombreAgencia = nombreAgencia;
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