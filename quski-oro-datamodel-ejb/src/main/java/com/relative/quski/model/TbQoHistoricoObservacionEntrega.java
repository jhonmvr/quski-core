package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_qo_historico_observacion_entrega database table.
 * 
 */
@Entity
@Table(name="tb_qo_historico_observacion_entrega")
@NamedQuery(name="TbQoHistoricoObservacionEntrega.findAll", query="SELECT t FROM TbQoHistoricoObservacionEntrega t")
public class TbQoHistoricoObservacionEntrega implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_HISTORICO_OBSERVACION_ENTREGA_ID_GENERATOR", sequenceName="SEQ_OBSERVACION_ENTREGA", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_HISTORICO_OBSERVACION_ENTREGA_ID_GENERATOR")
	private Long id;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="id_devolucion")
	private BigDecimal idDevolucion;

	private String observacion;

	private String usuario;

	public TbQoHistoricoObservacionEntrega() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public BigDecimal getIdDevolucion() {
		return this.idDevolucion;
	}

	public void setIdDevolucion(BigDecimal idDevolucion) {
		this.idDevolucion = idDevolucion;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}