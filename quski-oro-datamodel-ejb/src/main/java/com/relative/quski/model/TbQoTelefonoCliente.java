package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

/**
 * The persistent class for the tb_qo_telefono_cliente database table.
 * 
 */
@Entity
@Table(name="tb_qo_telefono_cliente")
public class TbQoTelefonoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_TELEFONO_CLIENTE_ID_GENERATOR", sequenceName="SEQ_TELEFONO",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_TELEFONO_CLIENTE_ID_GENERATOR")
	private Long id;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	// bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private TbQoCliente tbQoCliente;

	@Column(name="id_softbank")
	private Long idSoftbank;

	private String numero;

	@Column(name="tipo_telefono")
	private String tipoTelefono;

	public TbQoTelefonoCliente() {
	}

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

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipoTelefono() {
		return this.tipoTelefono;
	}

	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

}