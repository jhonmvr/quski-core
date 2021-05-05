package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_qo_negociacion database table.
 * 
 */
@Entity
@Table(name="tb_qo_negociacion")
public class TbQoNegociacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_NEGOCIACION_ID_GENERATOR", sequenceName="SEQ_NEGOCIACION", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_NEGOCIACION_ID_GENERATOR")
	private Long id;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;


	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private String asesor;
	
	@Column(name="correo_asesor")
	private String correoAsesor;
	
	@Column(name="nombre_asesor")
	private String nombreAsesor;
	
	private String aprobador;
	
	@Column(name="observacion_asesor")
	private String observacionAsesor;
	
	//bi-directional many-to-one association to TbQoCreditoNegociacion
	@OneToMany(mappedBy="tbQoNegociacion")
	private List<TbQoCreditoNegociacion> tbQoCreditoNegociacions;

	//bi-directional many-to-one association to TbQoDocumentoHabilitante
	@OneToMany(mappedBy="tbQoNegociacion")
	private List<TbQoDocumentoHabilitante> tbQoDocumentoHabilitantes;

	//bi-directional many-to-one association to TbQoExcepcion
	@OneToMany(mappedBy="tbQoNegociacion")
	private List<TbQoExcepcion> tbQoExcepcions;

	//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;

	//bi-directional many-to-one association to TbQoVariablesCrediticia
	@OneToMany(mappedBy="tbQoNegociacion")
	private List<TbQoVariablesCrediticia> tbQoVariablesCrediticias;

	//bi-directional many-to-one association to TbQoRiesgoAcumulado
	@OneToMany(mappedBy="tbQoNegociacion")
	private List<TbQoRiesgoAcumulado> tbQoRiesgoAcumulados;

	public TbQoNegociacion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getAsesor() {
		return asesor;
	}

	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}
	public String getAprobador() {
		return aprobador;
	}

	public void setAprobador(String aprobador) {
		this.aprobador = aprobador;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<TbQoCreditoNegociacion> getTbQoCreditoNegociacions() {
		return this.tbQoCreditoNegociacions;
	}

	public void setTbQoCreditoNegociacions(List<TbQoCreditoNegociacion> tbQoCreditoNegociacions) {
		this.tbQoCreditoNegociacions = tbQoCreditoNegociacions;
	}

	public TbQoCreditoNegociacion addTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		getTbQoCreditoNegociacions().add(tbQoCreditoNegociacion);
		tbQoCreditoNegociacion.setTbQoNegociacion(this);

		return tbQoCreditoNegociacion;
	}

	public TbQoCreditoNegociacion removeTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		getTbQoCreditoNegociacions().remove(tbQoCreditoNegociacion);
		tbQoCreditoNegociacion.setTbQoNegociacion(null);

		return tbQoCreditoNegociacion;
	}

	public List<TbQoDocumentoHabilitante> getTbQoDocumentoHabilitantes() {
		return this.tbQoDocumentoHabilitantes;
	}

	public void setTbQoDocumentoHabilitantes(List<TbQoDocumentoHabilitante> tbQoDocumentoHabilitantes) {
		this.tbQoDocumentoHabilitantes = tbQoDocumentoHabilitantes;
	}

	public TbQoDocumentoHabilitante addTbQoDocumentoHabilitante(TbQoDocumentoHabilitante tbQoDocumentoHabilitante) {
		getTbQoDocumentoHabilitantes().add(tbQoDocumentoHabilitante);
		tbQoDocumentoHabilitante.setTbQoNegociacion(this);

		return tbQoDocumentoHabilitante;
	}

	public TbQoDocumentoHabilitante removeTbQoDocumentoHabilitante(TbQoDocumentoHabilitante tbQoDocumentoHabilitante) {
		getTbQoDocumentoHabilitantes().remove(tbQoDocumentoHabilitante);
		tbQoDocumentoHabilitante.setTbQoNegociacion(null);

		return tbQoDocumentoHabilitante;
	}

	public List<TbQoExcepcion> getTbQoExcepciones() {
		return this.tbQoExcepcions;
	}

	public void setTbQoExcepciones(List<TbQoExcepcion> tbQoExcepcions) {
		this.tbQoExcepcions = tbQoExcepcions;
	}

	public TbQoExcepcion addTbQoExcepcion(TbQoExcepcion tbQoExcepcion) {
		getTbQoExcepciones().add(tbQoExcepcion);
		tbQoExcepcion.setTbQoNegociacion(this);

		return tbQoExcepcion;
	}

	public TbQoExcepcion removeTbQoExcepcion(TbQoExcepcion tbQoExcepcion) {
		getTbQoExcepciones().remove(tbQoExcepcion);
		tbQoExcepcion.setTbQoNegociacion(null);

		return tbQoExcepcion;
	}

	public TbQoCliente getTbQoCliente() {
		return this.tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

	public List<TbQoVariablesCrediticia> getTbQoVariablesCrediticias() {
		return this.tbQoVariablesCrediticias;
	}

	public void setTbQoVariablesCrediticias(List<TbQoVariablesCrediticia> tbQoVariablesCrediticias) {
		this.tbQoVariablesCrediticias = tbQoVariablesCrediticias;
	}

	public TbQoVariablesCrediticia addTbQoVariablesCrediticia(TbQoVariablesCrediticia tbQoVariablesCrediticia) {
		getTbQoVariablesCrediticias().add(tbQoVariablesCrediticia);
		tbQoVariablesCrediticia.setTbQoNegociacion(this);

		return tbQoVariablesCrediticia;
	}

	public TbQoVariablesCrediticia removeTbQoVariablesCrediticia(TbQoVariablesCrediticia tbQoVariablesCrediticia) {
		getTbQoVariablesCrediticias().remove(tbQoVariablesCrediticia);
		tbQoVariablesCrediticia.setTbQoNegociacion(null);

		return tbQoVariablesCrediticia;
	}

	public List<TbQoRiesgoAcumulado> getTbQoRiesgoAcumulados() {
		return this.tbQoRiesgoAcumulados;
	}

	public void setTbQoRiesgoAcumulados(List<TbQoRiesgoAcumulado> tbQoRiesgoAcumulados) {
		this.tbQoRiesgoAcumulados = tbQoRiesgoAcumulados;
	}

	public TbQoRiesgoAcumulado addTbQoRiesgoAcumulado(TbQoRiesgoAcumulado tbQoRiesgoAcumulado) {
		getTbQoRiesgoAcumulados().add(tbQoRiesgoAcumulado);
		tbQoRiesgoAcumulado.setTbQoNegociacion(this);

		return tbQoRiesgoAcumulado;
	}

	public TbQoRiesgoAcumulado removeTbQoRiesgoAcumulado(TbQoRiesgoAcumulado tbQoRiesgoAcumulado) {
		getTbQoRiesgoAcumulados().remove(tbQoRiesgoAcumulado);
		tbQoRiesgoAcumulado.setTbQoNegociacion(null);

		return tbQoRiesgoAcumulado;
	}

	public String getCorreoAsesor() {
		return correoAsesor;
	}

	public void setCorreoAsesor(String correoAsesor) {
		this.correoAsesor = correoAsesor;
	}

	public String getNombreAsesor() {
		return nombreAsesor;
	}

	public void setNombreAsesor(String nombreAsesor) {
		this.nombreAsesor = nombreAsesor;
	}

	public String getObservacionAsesor() {
		return observacionAsesor;
	}

	public void setObservacionAsesor(String observacionAsesor) {
		this.observacionAsesor = observacionAsesor;
	}
	
	

}