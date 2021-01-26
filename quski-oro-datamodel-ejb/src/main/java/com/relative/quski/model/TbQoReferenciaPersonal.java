package com.relative.quski.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.quski.enums.EstadoEnum;

/**
 * The persistent class for the tb_qo_referencia_personal database table.
 * 
 */
@Entity
@Table(name = "tb_qo_referencia_personal")
@NamedQuery(name = ".findAll", query = "SELECT t FROM TbQoReferenciaPersonal t")
public class TbQoReferenciaPersonal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TB_QO_REFERENCIA_PERSONAL_ID_GENERATOR", sequenceName = "SEQ_REFERENCIA_PERSONAL", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QO_REFERENCIA_PERSONAL_ID_GENERATOR")
	private Long id;

	private String direccion;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;


	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	private String nombres;
	
	private String apellidos;

	private String parentesco;

	@Column(name = "telefono_fijo")
	private String telefonoFijo;

	@Column(name = "telefono_movil")
	private String telefonoMovil;
	
	@Column(name="id_softbank")
	private Long idSoftbank;

	// bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private TbQoCliente tbQoCliente;

	public TbQoReferenciaPersonal() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public EstadoEnum getEstado() {
		return estado;
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

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getParentesco() {
		return this.parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getTelefonoFijo() {
		return this.telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getTelefonoMovil() {
		return this.telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public TbQoCliente getTbQoCliente() {
		return this.tbQoCliente;
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

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

}