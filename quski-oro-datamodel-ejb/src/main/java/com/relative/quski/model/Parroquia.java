package com.relative.quski.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the parroquia database table.
 * 
 */
@Entity
@Table(name="parroquia")
public class Parroquia implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="PARROQUIA_ID_GENERATOR", sequenceName="SEG_PARROQUIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PARROQUIA_ID_GENERATOR")
	private Long id;

	@Column(name="codigo_canton")
	private String codigoCanton;

	@Column(name="codigo_parroquia")
	private BigDecimal codigoParroquia;

	@Column(name="codigo_provincia")
	private String codigoProvincia;

	private String estado;

	@Column(name="nombre_parroquia")
	private String nombreParroquia;

	//bi-directional many-to-one association to Canton
	@ManyToOne
	@JoinColumn(name="id_canton")
	private Canton canton;

	//bi-directional many-to-one association to TbQoAgencia
	@OneToMany(mappedBy="parroquia")
	private List<TbQoAgencia> tbQoAgencias;

	public Parroquia() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoCanton() {
		return this.codigoCanton;
	}

	public void setCodigoCanton(String codigoCanton) {
		this.codigoCanton = codigoCanton;
	}

	public BigDecimal getCodigoParroquia() {
		return this.codigoParroquia;
	}

	public void setCodigoParroquia(BigDecimal codigoParroquia) {
		this.codigoParroquia = codigoParroquia;
	}

	public String getCodigoProvincia() {
		return this.codigoProvincia;
	}

	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombreParroquia() {
		return this.nombreParroquia;
	}

	public void setNombreParroquia(String nombreParroquia) {
		this.nombreParroquia = nombreParroquia;
	}

	public Canton getCanton() {
		return this.canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	public List<TbQoAgencia> getTbQoAgencias() {
		return this.tbQoAgencias;
	}

	public void setTbQoAgencias(List<TbQoAgencia> tbQoAgencias) {
		this.tbQoAgencias = tbQoAgencias;
	}

	public TbQoAgencia addTbQoAgencia(TbQoAgencia tbQoAgencia) {
		getTbQoAgencias().add(tbQoAgencia);
		tbQoAgencia.setParroquia(this);

		return tbQoAgencia;
	}

	public TbQoAgencia removeTbQoAgencia(TbQoAgencia tbQoAgencia) {
		getTbQoAgencias().remove(tbQoAgencia);
		tbQoAgencia.setParroquia(null);

		return tbQoAgencia;
	}

}