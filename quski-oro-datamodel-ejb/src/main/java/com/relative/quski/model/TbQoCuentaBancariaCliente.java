package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

/**
 * The persistent class for the tb_qo_cuenta_bancaria_cliente database table.
 * 
 */
@Entity
@Table(name="tb_qo_cuenta_bancaria_cliente")
public class TbQoCuentaBancariaCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_CUENTA_BANCARIA_CLIENTE_ID_GENERATOR", sequenceName="SEQ_CUENTA",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_CUENTA_BANCARIA_CLIENTE_ID_GENERATOR")
	private Long id;

	private Long banco;

	private String cuenta;
	
	@Column(name="es_nueva")
	private Boolean esNueva;

	


	@Column(name="es_ahorros")
	private Boolean esAhorros;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	// bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private TbQoCliente tbQoCliente;

	@Column(name="id_softbank")
	private Long idSoftbank;

	
	
	public TbQoCuentaBancariaCliente() {
	}
	

	public Boolean getEsNueva() {
		return esNueva;
	}

	public void setEsNueva(Boolean esNueva) {
		this.esNueva = esNueva;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBanco() {
		return banco;
	}

	public void setBanco(Long banco) {
		this.banco = banco;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public TbQoCliente getTbQoCliente() {
		return tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

	public Long getIdSoftbank() {
		return idSoftbank;
	}

	public void setIdSoftbank(Long idSoftbank) {
		this.idSoftbank = idSoftbank;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public Boolean getEsAhorros() {
		return this.esAhorros;
	}

	public void setEsAhorros(Boolean esAhorros) {
		this.esAhorros = esAhorros;
	}
}