package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_tipo_joya database table.
 * 
 */
@Entity
@Table(name="tb_tipo_joya")
//@NamedQuery(name="TbTipoJoya.findAll", query="SELECT t FROM TbTipoJoya t")
public class TbTipoJoya implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_JOYA_ID_GENERATOR", sequenceName="SEQ_JOYA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_JOYA_ID_GENERATOR")
	private Long id;

	private String codigo;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

/*	//bi-directional many-to-one association to TqQoTasacion
	@OneToMany(mappedBy="tbTipoJoya")
	private List<TbQoTasacion> tqQoTasacions;*/

	public TbTipoJoya() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	
}