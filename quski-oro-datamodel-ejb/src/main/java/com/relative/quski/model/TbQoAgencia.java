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


	@Column(name="nombre_agencia")
	private String nombreAgencia;

	//bi-directional many-to-one association to Parroquia
	@ManyToOne
	@JoinColumn(name="id_parroquia")
	private Parroquia parroquia;
	
	@ManyToOne
	@JoinColumn(name="id_canton")
	private Canton canton;
	
	@ManyToOne
	@JoinColumn(name="id_provincia")
	private Provincia provincia;

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

	
	
	public Parroquia getParroquia() {
		return parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}

	public Canton getCanton() {
		return canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getNombreAgencia() {
		return this.nombreAgencia;
	}

	public void setNombreAgencia(String nombreAgencia) {
		this.nombreAgencia = nombreAgencia;
	}

	
	

}