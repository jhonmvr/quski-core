package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_qo_tipo_oro database table.
 * 
 */
@Entity
@Table(name="tb_qo_tipo_oro")
public class TbQoTipoOro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_TIPO_ORO_ID_GENERATOR", sequenceName="SEG_TB_QO_TIPO_ORO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_TIPO_ORO_ID_GENERATOR")
	private Long id;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private BigDecimal precio;

	private String quilate;

	//bi-directional many-to-one association to TbQoPrecioOro
	@OneToMany(mappedBy="tbQoTipoOro")
	private List<TbQoPrecioOro> tbQoPrecioOros;

	//bi-directional many-to-one association to TbQoTasacion
	@OneToMany(mappedBy="tbQoTipoOro")
	private List<TbQoTasacion> tbQoTasacions;

	public TbQoTipoOro() {
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

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
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

	public List<TbQoTasacion> getTbQoTasacions() {
		return this.tbQoTasacions;
	}

	public void setTbQoTasacions(List<TbQoTasacion> tbQoTasacions) {
		this.tbQoTasacions = tbQoTasacions;
	}

	public TbQoTasacion addTbQoTasacion(TbQoTasacion tbQoTasacion) {
		getTbQoTasacions().add(tbQoTasacion);
		tbQoTasacion.setTbQoTipoOro(this);

		return tbQoTasacion;
	}

	public TbQoTasacion removeTbQoTasacion(TbQoTasacion tbQoTasacion) {
		getTbQoTasacions().remove(tbQoTasacion);
		tbQoTasacion.setTbQoTipoOro(null);

		return tbQoTasacion;
	}

}