package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_qo_proceso database table.
 * 
 */
@Entity
@Table(name="tb_qo_proceso")
public class TbQoProceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_PROCESO_ID_GENERATOR", sequenceName="SEG_QO_PROCESO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_PROCESO_ID_GENERATOR")
	private long id;

	@Column(name="nombre_proceso")
	private String nombreProceso;
	
	@Column(name="codigo_proceso")
	private String codigoProceso;

	//bi-directional many-to-one association to TbQoCreditoNegociacion
	@OneToMany(mappedBy="tbQoProceso")
	private List<TbQoCreditoNegociacion> tbQoCreditoNegociacions;

	public TbQoProceso() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreProceso() {
		return this.nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}
	
	public String getCodigoProceso() {
		return this.codigoProceso;
	}

	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}

	public List<TbQoCreditoNegociacion> getTbQoCreditoNegociacions() {
		return this.tbQoCreditoNegociacions;
	}

	public void setTbQoCreditoNegociacions(List<TbQoCreditoNegociacion> tbQoCreditoNegociacions) {
		this.tbQoCreditoNegociacions = tbQoCreditoNegociacions;
	}

	public TbQoCreditoNegociacion addTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		getTbQoCreditoNegociacions().add(tbQoCreditoNegociacion);
		tbQoCreditoNegociacion.setTbQoProceso(this);

		return tbQoCreditoNegociacion;
	}

	public TbQoCreditoNegociacion removeTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		getTbQoCreditoNegociacions().remove(tbQoCreditoNegociacion);
		tbQoCreditoNegociacion.setTbQoProceso(null);

		return tbQoCreditoNegociacion;
	}

}