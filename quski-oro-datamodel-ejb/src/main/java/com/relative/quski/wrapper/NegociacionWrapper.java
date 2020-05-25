package com.relative.quski.wrapper;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.relative.quski.model.TbQoCliente;



/**
 * The persistent class for the tb_qo_negociacion database table.
 * 
 */
@Entity
@Table(name="tb_qo_negociacion")
public class NegociacionWrapper implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_NEGOCIACION_ID_GENERATOR", sequenceName="SEG_TB_QO_NEGOCIACION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_NEGOCIACION_ID_GENERATOR")
	private Long id;

	private String estado;

	//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;

	
	public NegociacionWrapper() {
			
		}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
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
