package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tb_qo_patrimonio database table.
 * 
 */
@Entity
@Table(name="tb_qo_patrimonio")
public class TbQoPatrimonio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_PATRIMONIO_ID_GENERATOR", sequenceName="SEQ_PATRIMONIO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_PATRIMONIO_ID_GENERATOR")
	private Long id;

	private String activos;

	private BigDecimal avaluo;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private BigDecimal ifis;

	private BigDecimal infocorp;

	private String pasivos;
	
	private String estado;

	//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;

	public TbQoPatrimonio() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivos() {
		return this.activos;
	}

	public void setActivos(String activos) {
		this.activos = activos;
	}

	public BigDecimal getAvaluo() {
		return this.avaluo;
	}

	public void setAvaluo(BigDecimal avaluo) {
		this.avaluo = avaluo;
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

	public BigDecimal getIfis() {
		return this.ifis;
	}

	public void setIfis(BigDecimal ifis) {
		this.ifis = ifis;
	}

	public BigDecimal getInfocorp() {
		return this.infocorp;
	}

	public void setInfocorp(BigDecimal infocorp) {
		this.infocorp = infocorp;
	}

	public String getPasivos() {
		return this.pasivos;
	}

	public void setPasivos(String pasivos) {
		this.pasivos = pasivos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	public TbQoCliente getTbQoCliente() {
		return this.tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}


}