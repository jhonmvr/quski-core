package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_qo_validacion_documento database table.
 * 
 */
@Entity
@Table(name="tb_qo_validacion_documento")
public class TbQoValidacionDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_VALIDACION_DOCUMENTO_ID_GENERATOR", sequenceName="SEQ_VALIDACION_DOCUMENTO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_VALIDACION_DOCUMENTO_ID_GENERATOR")
	private Long id;


	private Boolean coincidencia;

	private String confianza;

	private String item;

	private String valor;

	//bi-directional many-to-one association to TbQoCreditoNegociacion2
	@ManyToOne
	@JoinColumn(name="id_credito")
	private TbQoCreditoNegociacion tbQoCreditoNegociacion;

	public TbQoValidacionDocumento() {
	}

	public TbQoValidacionDocumento(Boolean coincidencia, String confianza, String item, String valor) {
		super();
		this.coincidencia = coincidencia;
		this.confianza = confianza;
		this.item = item;
		this.valor = valor;
	}
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getCoincidencia() {
		return this.coincidencia;
	}

	public void setCoincidencia(Boolean coincidencia) {
		this.coincidencia = coincidencia;
	}

	public String getConfianza() {
		return this.confianza;
	}

	public void setConfianza(String confianza) {
		this.confianza = confianza;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public TbQoCreditoNegociacion getTbQoCreditoNegociacion() {
		return this.tbQoCreditoNegociacion;
	}

	public void setTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		this.tbQoCreditoNegociacion = tbQoCreditoNegociacion;
	}

}