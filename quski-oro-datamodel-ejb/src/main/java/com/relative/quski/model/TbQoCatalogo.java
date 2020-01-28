package com.relative.quski.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.quski.enums.EstadoEnum;

@Entity
@Table(name="TB_QO_CATALOGO")

public class TbQoCatalogo implements Serializable  {
	private static final Long serialVersionUID=1L;
	
	@Id
	@SequenceGenerator(name="TB_QO_CATALOGO_ID_GENERATOR", sequenceName="SEQ_CATALOGO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_CATALOGO_ID_GENERATOR")
	private Long id;
	
	@Column(name="NOMBRE_CATALOGO")
	private String nombreCatalogo;
	
	@Column(name="DESCRIPCION_CATALOGO")
	private String descripcionCatalogo;
	
	
	@Column(name="TIPO_CATALOGO")
	private String tipoCatalogo;
	
	@Column(name="VALOR_CATALOGO")
	private String valorCatalogo;
	
	@Column(name="ESTADO")
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombreCatalogo() {
		return nombreCatalogo;
	}
	public void setNombreCatalogo(String nombreCatalogo) {
		this.nombreCatalogo = nombreCatalogo;
	}
	public String getTipoCatalogo() {
		return tipoCatalogo;
	}
	public void setTipoCatalogo(String tipoCatalogo) {
		this.tipoCatalogo = tipoCatalogo;
	}
	public String getValorCatalogo() {
		return valorCatalogo;
	}
	public void setValorCatalogo(String valorCatalogo) {
		this.valorCatalogo = valorCatalogo;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public EstadoEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getDescripcionCatalogo() {
		return descripcionCatalogo;
	}
	public void setDescripcionCatalogo(String descripcionCatalogo) {
		this.descripcionCatalogo = descripcionCatalogo;
	}



}
