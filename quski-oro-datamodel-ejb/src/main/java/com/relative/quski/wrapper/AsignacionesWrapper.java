package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;

import com.relative.quski.model.TbQoProceso;

/**
 * The persistent class for the tb_qo_credito_negociacion database table.
 * 
 */
//@Entity
//@Table(name="tb_qo_credito_negociacion")
public class AsignacionesWrapper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//@Id
	//@SequenceGenerator(name="TB_QO_CREDITO_NEGOCIACION_ID_GENERATOR", sequenceName="SEG_QO_CREDITO_NEGOCIACION")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_CREDITO_NEGOCIACION_ID_GENERATOR")
	private Long id;	
	
	private String estado;
	
	//@Temporal(TemporalType.DATE)
	//@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	//@Temporal(TemporalType.DATE)
	//@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;
	
	//@Column(name="codigo_operacion")
	private String codigoOperacion;
	
	//private String situacionCodigo;
	//private String estadoCodigo;

	//bi-directional many-to-one association to TbQoAgencia
	//@ManyToOne
	//@JoinColumn(name="id_agencia")
	private AgenciaWrapper agenciaWrapper;
	//bi-directional many-to-one association to TbQoNegociacion
	//@ManyToOne
	//@JoinColumn(name="id_negociacion")
	private NegociacionWrapper negociacionWrapper;
	//bi-directional many-to-one association to TbQoProceso
	//	@ManyToOne
	//@JoinColumn(name="id_proceso")
	private TbQoProceso tbQoProceso;

	
	public AsignacionesWrapper() {
			
		}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoOperacion() {
		return this.codigoOperacion;
	}

	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}


	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public AgenciaWrapper getAgenciaWrapper() {
		return this.agenciaWrapper;
	}

	public void setAgenciaWrapper(AgenciaWrapper agenciaWrapper) {
		this.agenciaWrapper = agenciaWrapper;
	}

	public NegociacionWrapper getNegociacionWrapper() {
		return this.negociacionWrapper;
	}

	public void setNegociacionWrapper(NegociacionWrapper negociacionWrapper) {
		this.negociacionWrapper = negociacionWrapper;
	}

	public TbQoProceso getTbQoProceso() {
		return this.tbQoProceso;
	}

	public void setTbQoProceso(TbQoProceso tbQoProceso) {
		this.tbQoProceso = tbQoProceso;
	}




		
	
}
