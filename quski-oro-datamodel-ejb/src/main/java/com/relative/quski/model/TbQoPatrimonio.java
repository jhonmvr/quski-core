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
	@SequenceGenerator(name="TB_QO_PATRIMONIO_ID_GENERATOR", sequenceName="SEQ_PATRIMONIO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_PATRIMONIO_ID_GENERATOR")
	private Long id;

	private String activo;

	private BigDecimal avaluo;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;

	private BigDecimal ifis;

	@Temporal(TemporalType.DATE)
	private Date infocorp;

	private String pasivos;

	public TbQoPatrimonio() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
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


	public TbQoCliente getTbQoCliente() {
		return tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

	public BigDecimal getIfis() {
		return this.ifis;
	}

	public void setIfis(BigDecimal ifis) {
		this.ifis = ifis;
	}

	public Date getInfocorp() {
		return this.infocorp;
	}

	public void setInfocorp(Date infocorp) {
		this.infocorp = infocorp;
	}

	public String getPasivos() {
		return this.pasivos;
	}

	public void setPasivos(String pasivos) {
		this.pasivos = pasivos;
	}

}