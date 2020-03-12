package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the provincia database table.
 * 
 */
@Entity
@Table(name="provincia")
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROVINCIA_ID_GENERATOR", sequenceName="SEQ_PROVINCIA",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROVINCIA_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=3)
	private long id;

	@Column(name="codigo_provincia", length=2)
	private String codigoProvincia;

	@Column(length=20)
	private String estado;

	@Column(name="nombre_provincia", length=60)
	private String nombreProvincia;

	//bi-directional many-to-one association to Canton
	@OneToMany(mappedBy="provincia")
	private List<Canton> cantons;

	//bi-directional many-to-one association to TbQoAgencia
	@OneToMany(mappedBy="provincia")
	private List<TbQoAgencia> tbQoAgencias;

	public Provincia() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getNombreProvincia() {
		return this.nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public List<Canton> getCantons() {
		return this.cantons;
	}

	public void setCantons(List<Canton> cantons) {
		this.cantons = cantons;
	}

	public Canton addCanton(Canton canton) {
		getCantons().add(canton);
		canton.setProvincia(this);

		return canton;
	}

	public Canton removeCanton(Canton canton) {
		getCantons().remove(canton);
		canton.setProvincia(null);

		return canton;
	}

	public List<TbQoAgencia> getTbQoAgencias() {
		return this.tbQoAgencias;
	}

	public void setTbQoAgencias(List<TbQoAgencia> tbQoAgencias) {
		this.tbQoAgencias = tbQoAgencias;
	}

}