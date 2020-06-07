package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tb_qo_ingreso_egreso_cliente database table.
 * 
 */
@Entity
@Table(name="tb_qo_ingreso_egreso_cliente")
public class TbQoIngresoEgresoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_INGRESO_EGRESO_CLIENTE_ID_GENERATOR", sequenceName="SEQ_INGRESO_EGRESO_CLIENTE",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_INGRESO_EGRESO_CLIENTE_ID_GENERATOR")
	private Long id;

	@Column(name="valor_egreso")
	private BigDecimal valorEgreso;

	@Column(name="valor_ingreso")
	private BigDecimal valorIngreso;

	//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;

	public TbQoIngresoEgresoCliente() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorEgreso() {
		return this.valorEgreso;
	}

	public void setValorEgreso(BigDecimal valorEgreso) {
		this.valorEgreso = valorEgreso;
	}

	public BigDecimal getValorIngreso() {
		return this.valorIngreso;
	}

	public void setValorIngreso(BigDecimal valorIngreso) {
		this.valorIngreso = valorIngreso;
	}

	public TbQoCliente getTbQoCliente() {
		return this.tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

}