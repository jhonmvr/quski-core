package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tb_qo_verificacion_telefonica database table.
 * 
 */
@Entity
@Table(name="tb_qo_verificacion_telefonica")
public class TbQoVerificacionTelefonica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_VERIFICACION_TELEFONICA_ID_GENERATOR", sequenceName="SEQ_VERIFICACION_TELEFONICA",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_VERIFICACION_TELEFONICA_ID_GENERATOR")
	private Long id;
	
	private String aprobador;

	private String asesor;

	@Column(name="cedula_cliente")
	private String cedulaCliente;

	private String codigo;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	

	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name="fecha_creacion")
	private Date fechaCreacion;


	@Column(name="id_agencia")
	private BigDecimal idAgencia;

	@Column(name="nombre_cliente")
	private String nombreCliente;

	public TbQoVerificacionTelefonica() {
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAprobador() {
		return this.aprobador;
	}

	public void setAprobador(String aprobador) {
		this.aprobador = aprobador;
	}

	public String getAsesor() {
		return this.asesor;
	}

	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}

	public String getCedulaCliente() {
		return this.cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public BigDecimal getIdAgencia() {
		return this.idAgencia;
	}

	public void setIdAgencia(BigDecimal idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getNombreCliente() {
		return this.nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

}