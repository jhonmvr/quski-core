package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.model.TqQoNegociacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_qo_cliente database table.
 * 
 */
@Entity
@Table(name="tb_qo_cliente")
@NamedQuery(name="TbQoCliente.findAll", query="SELECT t FROM TbQoCliente t")
public class TbQoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_CLIENTE_ID_GENERATOR", sequenceName="SEQ__CLIENTE" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_CLIENTE_ID_GENERATOR")
	private Long id;

	@Column(name="actividad_economica")
	private String actividadEconomica;

	@Column(name="apellido_paterno")
	private String apellidoPaterno;

	@Column(name="cargas_familiares")
	private BigDecimal cargasFamiliares;

	@Column(name="cedula_cliente")
	private String cedulaCliente;

	private BigDecimal edad;

	private String estado;

	@Column(name="estado_civil")
	private String estadoCivil;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	private String genero;

	@Column(name="lugar_nacimiento")
	private String lugarNacimiento;

	private String nacionalidad;

	@Column(name="nivel_educacion")
	private String nivelEducacion;

	@Column(name="primer_nombre")
	private String primerNombre;

	@Column(name="segundo_nombre")
	private String segundoNombre;

	//bi-directional many-to-one association to TbQoCotizador
	@OneToMany(mappedBy="tbQoCliente")
	private List<TbQoCotizador> tbQoCotizadors;

	//bi-directional many-to-one association to TbQoRiesgoAcumulado
	@OneToMany(mappedBy="tbQoCliente")
	private List<TbQoRiesgoAcumulado> tbQoRiesgoAcumulados;

	//bi-directional many-to-one association to TqQoNegociacion
	@OneToMany(mappedBy="tbQoCliente")
	private List<TqQoNegociacion> tqQoNegociacions;

	public TbQoCliente() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActividadEconomica() {
		return this.actividadEconomica;
	}

	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public BigDecimal getCargasFamiliares() {
		return this.cargasFamiliares;
	}

	public void setCargasFamiliares(BigDecimal cargasFamiliares) {
		this.cargasFamiliares = cargasFamiliares;
	}

	public String getCedulaCliente() {
		return this.cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public BigDecimal getEdad() {
		return this.edad;
	}

	public void setEdad(BigDecimal edad) {
		this.edad = edad;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getLugarNacimiento() {
		return this.lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNivelEducacion() {
		return this.nivelEducacion;
	}

	public void setNivelEducacion(String nivelEducacion) {
		this.nivelEducacion = nivelEducacion;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public List<TbQoCotizador> getTbQoCotizadors() {
		return this.tbQoCotizadors;
	}

	public void setTbQoCotizadors(List<TbQoCotizador> tbQoCotizadors) {
		this.tbQoCotizadors = tbQoCotizadors;
	}

	public TbQoCotizador addTbQoCotizador(TbQoCotizador tbQoCotizador) {
		getTbQoCotizadors().add(tbQoCotizador);
		tbQoCotizador.setTbQoCliente(this);

		return tbQoCotizador;
	}

	public TbQoCotizador removeTbQoCotizador(TbQoCotizador tbQoCotizador) {
		getTbQoCotizadors().remove(tbQoCotizador);
		tbQoCotizador.setTbQoCliente(null);

		return tbQoCotizador;
	}

	public List<TbQoRiesgoAcumulado> getTbQoRiesgoAcumulados() {
		return this.tbQoRiesgoAcumulados;
	}

	public void setTbQoRiesgoAcumulados(List<TbQoRiesgoAcumulado> tbQoRiesgoAcumulados) {
		this.tbQoRiesgoAcumulados = tbQoRiesgoAcumulados;
	}

	public TbQoRiesgoAcumulado addTbQoRiesgoAcumulado(TbQoRiesgoAcumulado tbQoRiesgoAcumulado) {
		getTbQoRiesgoAcumulados().add(tbQoRiesgoAcumulado);
		tbQoRiesgoAcumulado.setTbQoCliente(this);

		return tbQoRiesgoAcumulado;
	}

	public TbQoRiesgoAcumulado removeTbQoRiesgoAcumulado(TbQoRiesgoAcumulado tbQoRiesgoAcumulado) {
		getTbQoRiesgoAcumulados().remove(tbQoRiesgoAcumulado);
		tbQoRiesgoAcumulado.setTbQoCliente(null);

		return tbQoRiesgoAcumulado;
	}

	public List<TqQoNegociacion> getTqQoNegociacions() {
		return this.tqQoNegociacions;
	}

	public void setTqQoNegociacions(List<TqQoNegociacion> tqQoNegociacions) {
		this.tqQoNegociacions = tqQoNegociacions;
	}

	public TqQoNegociacion addTqQoNegociacion(TqQoNegociacion tqQoNegociacion) {
		getTqQoNegociacions().add(tqQoNegociacion);
		tqQoNegociacion.setTbQoCliente(this);

		return tqQoNegociacion;
	}

	public TqQoNegociacion removeTqQoNegociacion(TqQoNegociacion tqQoNegociacion) {
		getTqQoNegociacions().remove(tqQoNegociacion);
		tqQoNegociacion.setTbQoCliente(null);

		return tqQoNegociacion;
	}

}