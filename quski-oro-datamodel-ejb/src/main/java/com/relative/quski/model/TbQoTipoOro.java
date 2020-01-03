package com.relative.quski.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.relative.quski.enums.EstadoEnum;

/**
 * The persistent class for the tb_mi_tipo_oro database table.
 * 
 */
@Entity
@Table(name="tb_qo_tipo_oro")
public class TbQoTipoOro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_TIPO_ORO_ID_GENERATOR", sequenceName="SEQ_TIPO_ORO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_TIPO_ORO_ID_GENERATOR")
	private Long id;
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	private String quilate;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	//bi-directional many-to-one association to TbMiMovimientoCaja
		@OneToMany(mappedBy="tbQoTipoOro")
		private List<TbQoPrecioOro> tbQoPrecioOro;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EstadoEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}
	public String getQuilate() {
		return quilate;
	}
	public void setQuilate(String quilate) {
		this.quilate = quilate;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public List<TbQoPrecioOro> getTbQoPrecioOro() {
		return tbQoPrecioOro;
	}
	public void setTbQoPrecioOro(List<TbQoPrecioOro> tbQoPrecioOro) {
		this.tbQoPrecioOro = tbQoPrecioOro;
	}
	
}
