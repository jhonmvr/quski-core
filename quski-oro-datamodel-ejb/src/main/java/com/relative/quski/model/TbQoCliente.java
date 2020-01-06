package com.relative.quski.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TB_QO_CLIENTE")

public class TbQoCliente implements Serializable {
	private static final Long serialVersionUID=1L;
	@Id
	private Long id;
	
	@Column(name="CEDULA_CLIENTE")
	private String cedulaCliente;
	
	@Column(name="PRIMER_NOMBRE")
	private String primerNombre;
	
	@Column(name="SEGUNDO_NOMBRE")
	private String segundoNombre;
	
	@Column(name="APELLIDO_PATERNO")
	private String apellidoPaterno;
	
	@Column(name="APELLIDO_MATERNO")
	private String apellidoMaterno;
	
	@Column(name="GENERO")
	 private String genero;
	
	@Column(name="ESTADO_CIVIL")
	private String estadoCivil;
	
	@Column(name="CARGAS_FAMILIARES")
	private Number cargasFamiliares;
	
	@Column(name="FECHA_NACIMIENTO")
	private Date fechaNacimiento;
	
	@Column(name="LUGAR_NACIMIENTO")
	private String lugarNacimiento;
	
	@Column(name="NACIONALIDAD")
	private String nacionalidad;
	
	@Column(name="NIVEL_EDUCACION")
	private String nivelEducacion;
	
	@Column(name="ACTIVIDAD_ECONOMICA")
	private String actividadEconomica;
	
	@Column(name="EDAD")
	private Number edad;
	
	@Column(name="FECHA_CREACION")
	private Timestamp fechaCreacion;
	
	@Column(name="FECHA_ACTUALIZACION")
	private Timestamp fechaActualizacion;
	
	@Column(name="ESTADO")
	private String estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombe) {
		this.segundoNombre = segundoNombe;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Number getCargasFamiliares() {
		return cargasFamiliares;
	}

	public void setCargasFamiliares(Number cargasFamiliares) {
		this.cargasFamiliares = cargasFamiliares;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNivelEducacion() {
		return nivelEducacion;
	}

	public void setNivelEducacion(String nivelEducacion) {
		this.nivelEducacion = nivelEducacion;
	}

	public String getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	public Number getEdad() {
		return edad;
	}

	public void setEdad(Number edad) {
		this.edad = edad;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp timestamp) {
		this.fechaCreacion = timestamp;
	}

	public Timestamp getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp timestamp) {
		this.fechaActualizacion = timestamp;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}