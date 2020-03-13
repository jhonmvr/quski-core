package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the canton database table.
 * 
 */
@Entity
@NamedQuery(name="Canton.findAll", query="SELECT c FROM Canton c")
public class Canton implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CANTON_ID_GENERATOR", sequenceName="SEQ_CANTON")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CANTON_ID_GENERATOR")
	private long id;

	@Column(name="codigo_canton")
	private String codigoCanton;

	@Column(name="codigo_provincia")
	private String codigoProvincia;

	private String estado;

	@Column(name="nombre_canton")
	private String nombreCanton;

	//bi-directional many-to-one association to Provincia
	@ManyToOne
	@JoinColumn(name="id_provincia")
	private Provincia provincia;

	//bi-directional many-to-one association to Parroquia
	@OneToMany(mappedBy="canton")
	private List<Parroquia> parroquias;

	//bi-directional many-to-one association to TbQoAgencia
	@OneToMany(mappedBy="canton")
	private List<TbQoAgencia> tbQoAgencias;

	public Canton() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigoCanton() {
		return this.codigoCanton;
	}

	public void setCodigoCanton(String codigoCanton) {
		this.codigoCanton = codigoCanton;
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

	public String getNombreCanton() {
		return this.nombreCanton;
	}

	public void setNombreCanton(String nombreCanton) {
		this.nombreCanton = nombreCanton;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public List<Parroquia> getParroquias() {
		return this.parroquias;
	}

	public void setParroquias(List<Parroquia> parroquias) {
		this.parroquias = parroquias;
	}

	public Parroquia addParroquia(Parroquia parroquia) {
		getParroquias().add(parroquia);
		parroquia.setCanton(this);

		return parroquia;
	}

	public Parroquia removeParroquia(Parroquia parroquia) {
		getParroquias().remove(parroquia);
		parroquia.setCanton(null);

		return parroquia;
	}

	public List<TbQoAgencia> getTbQoAgencias() {
		return this.tbQoAgencias;
	}

	public void setTbQoAgencias(List<TbQoAgencia> tbQoAgencias) {
		this.tbQoAgencias = tbQoAgencias;
	}

	public TbQoAgencia addTbQoAgencia(TbQoAgencia tbQoAgencia) {
		getTbQoAgencias().add(tbQoAgencia);
		tbQoAgencia.setCanton(this);

		return tbQoAgencia;
	}

	public TbQoAgencia removeTbQoAgencia(TbQoAgencia tbQoAgencia) {
		getTbQoAgencias().remove(tbQoAgencia);
		tbQoAgencia.setCanton(null);

		return tbQoAgencia;
	}

}