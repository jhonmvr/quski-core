package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.model.TbQoPrecioOro;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_qo_tipo_oro database table.
 * 
 */
@Entity
@Table(name="tb_qo_tipo_oro")
@NamedQuery(name="TbQoTipoOro.findAll", query="SELECT t FROM TbQoTipoOro t")
public class TbQoTipoOro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_TIPO_ORO_ID_GENERATOR", sequenceName="SEQ_TIPO_ORO" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_TIPO_ORO_ID_GENERATOR")
	private long id;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private String quilate;

	//bi-directional many-to-one association to TbQoPrecioOro
	@OneToMany(mappedBy="tbQoTipoOro")
	private List<TbQoPrecioOro> tbQoPrecioOros;

	public TbQoTipoOro() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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

	public String getQuilate() {
		return this.quilate;
	}

	public void setQuilate(String quilate) {
		this.quilate = quilate;
	}

	public List<TbQoPrecioOro> getTbQoPrecioOros() {
		return this.tbQoPrecioOros;
	}

	public void setTbQoPrecioOros(List<TbQoPrecioOro> tbQoPrecioOros) {
		this.tbQoPrecioOros = tbQoPrecioOros;
	}

	public TbQoPrecioOro addTbQoPrecioOro(TbQoPrecioOro tbQoPrecioOro) {
		getTbQoPrecioOros().add(tbQoPrecioOro);
		tbQoPrecioOro.setTbQoTipoOro(this);

		return tbQoPrecioOro;
	}

	public TbQoPrecioOro removeTbQoPrecioOro(TbQoPrecioOro tbQoPrecioOro) {
		getTbQoPrecioOros().remove(tbQoPrecioOro);
		tbQoPrecioOro.setTbQoTipoOro(null);

		return tbQoPrecioOro;
	}

}