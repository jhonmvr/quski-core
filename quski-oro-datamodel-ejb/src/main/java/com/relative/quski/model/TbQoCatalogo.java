package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

import java.util.Date;


/**
 * The persistent class for the tb_qo_catalogo database table.
 * 
 */
@Entity
@Table(name="tb_qo_catalogo")
public class TbQoCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_CATALOGO_ID_GENERATOR", sequenceName="SEQ_CATALOGO",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_CATALOGO_ID_GENERATOR")
	private Long id;

	@Column(name="descripcion_catalogo")
	private String descripcionCatalogo;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="nombre_catalogo")
	private String nombreCatalogo;

	@Column(name="tipo_catalogo")
	private String tipoCatalogo;

	@Column(name="valor_catalogo")
	private String valorCatalogo;

	public TbQoCatalogo() {
	}

	public Long getId() {
		
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcionCatalogo() {
		return this.descripcionCatalogo;
	}

	public void setDescripcionCatalogo(String descripcionCatalogo) {
		this.descripcionCatalogo = descripcionCatalogo;
	}

	public EstadoEnum getEstado() {
		return this.estado;
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

	public String getNombreCatalogo() {
		return this.nombreCatalogo;
	}

	public void setNombreCatalogo(String nombreCatalogo) {
		this.nombreCatalogo = nombreCatalogo;
	}

	public String getTipoCatalogo() {
		return this.tipoCatalogo;
	}

	public void setTipoCatalogo(String tipoCatalogo) {
		this.tipoCatalogo = tipoCatalogo;
	}

	public String getValorCatalogo() {
		return this.valorCatalogo;
	}

	public void setValorCatalogo(String valorCatalogo) {
		this.valorCatalogo = valorCatalogo;
	}

}