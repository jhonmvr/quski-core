package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the tb_qo_dato_trabajo_cliente database table.
 * 
 */
@Entity
@Table(name="tb_qo_dato_trabajo_cliente")
public class TbQoDatoTrabajoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_DATO_TRABAJO_CLIENTE_ID_GENERATOR", sequenceName="SEQ_DATOS",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_DATO_TRABAJO_CLIENTE_ID_GENERATOR")
	private Long id;

	@Column(name="actividad_economica")
	private Long actividadEconomica;

	@Column(name="actividad_economica_mupi")
	private String actividadEconomicaMupi;

	private String cargo;

	@Column(name="es_relacion_dependencia")
	private Boolean esRelacionDependencia;
	
	@Column(name="nombre_empresa")
	private String nombreEmpresa;

	private Boolean esprincipal;

	// bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private TbQoCliente tbQoCliente;

	@Column(name="id_softbank")
	private Long idSoftbank;
	
	private String ocupacion;

	@Column(name="origen_ingreso")
	private String origenIngreso;

	public TbQoDatoTrabajoCliente() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(Long actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	public String getActividadEconomicaMupi() {
		return actividadEconomicaMupi;
	}

	public void setActividadEconomicaMupi(String actividadEconomicaMupi) {
		this.actividadEconomicaMupi = actividadEconomicaMupi;
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

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Boolean getEsRelacionDependencia() {
		return this.esRelacionDependencia;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public void setEsRelacionDependencia(Boolean esRelacionDependencia) {
		this.esRelacionDependencia = esRelacionDependencia;
	}

	public Boolean getEsprincipal() {
		return this.esprincipal;
	}

	public void setEsprincipal(Boolean esprincipal) {
		this.esprincipal = esprincipal;
	}

	public String getOcupacion() {
		return this.ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getOrigenIngreso() {
		return this.origenIngreso;
	}

	public void setOrigenIngreso(String origenIngreso) {
		this.origenIngreso = origenIngreso;
	}

}