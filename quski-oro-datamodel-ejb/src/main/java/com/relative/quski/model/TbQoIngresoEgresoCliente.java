package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCliente;

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

	@Column(name="es_egreso")
	private Boolean esEgreso;

	@Column(name="es_ingreso")
	private Boolean esIngreso;

	//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;

	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	public TbQoIngresoEgresoCliente() {
	}
	public TbQoIngresoEgresoCliente( TbQoCliente cliente) {
		this.tbQoCliente = cliente;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEsEgreso() {
		return this.esEgreso;
	}

	public void setEsEgreso(Boolean esEgreso) {
		this.esEgreso = esEgreso;
	}

	public Boolean getEsIngreso() {
		return this.esIngreso;
	}

	public void setEsIngreso(Boolean esIngreso) {
		this.esIngreso = esIngreso;
	}

	public TbQoCliente getTbQoCliente() {
		return this.tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

}