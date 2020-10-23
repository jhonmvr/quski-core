package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tb_qo_devolucion database table.
 * 
 */
@Entity
@Table(name="tb_qo_devolucion")
public class TbQoDevolucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_DEVOLUCION_ID_GENERATOR", sequenceName="SEQ_DEVOLUCION",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_DEVOLUCION_ID_GENERATOR")
	private Long id;

	@Column(name="agencia_entrega")
	private String agenciaEntrega;

	private String aprobador;

	private String asesor;

	@Column(name="cedula_cliente")
	private String cedulaCliente;

	@Column(name="code_detalle_credito")
	private String codeDetalleCredito;

	@Column(name="code_detalle_garantia")
	private String codeDetalleGarantia;

	@Column(name="code_herederos")
	private String codeHerederos;

	private String codigo;

	@Column(name="codigo_operacion")
	private String codigoOperacion;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

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

	@Column(name="id_agencia")
	private BigDecimal idAgencia;

	@Column(name="lugar_nacimiento")
	private String lugarNacimiento;

	private String nacionalidad;

	@Column(name="nivel_educacion")
	private String nivelEducacion;

	@Column(name="nombre_cliente")
	private String nombreCliente;

	private String observaciones;

	@Column(name="separacion_bienes")
	private String separacionBienes;

	@Column(name="tipo_cliente")
	private String tipoCliente;

	@Column(name="valor_custodia_aprox")
	private BigDecimal valorCustodiaAprox;

	public TbQoDevolucion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgenciaEntrega() {
		return this.agenciaEntrega;
	}

	public void setAgenciaEntrega(String agenciaEntrega) {
		this.agenciaEntrega = agenciaEntrega;
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

	public String getCodeDetalleCredito() {
		return this.codeDetalleCredito;
	}

	public void setCodeDetalleCredito(String codeDetalleCredito) {
		this.codeDetalleCredito = codeDetalleCredito;
	}

	public String getCodeDetalleGarantia() {
		return this.codeDetalleGarantia;
	}

	public void setCodeDetalleGarantia(String codeDetalleGarantia) {
		this.codeDetalleGarantia = codeDetalleGarantia;
	}

	public String getCodeHerederos() {
		return this.codeHerederos;
	}

	public void setCodeHerederos(String codeHerederos) {
		this.codeHerederos = codeHerederos;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoOperacion() {
		return this.codigoOperacion;
	}

	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public EstadoEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoEnum estado) {
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

	public BigDecimal getIdAgencia() {
		return this.idAgencia;
	}

	public void setIdAgencia(BigDecimal idAgencia) {
		this.idAgencia = idAgencia;
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

	public String getNombreCliente() {
		return this.nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getSeparacionBienes() {
		return this.separacionBienes;
	}

	public void setSeparacionBienes(String separacionBienes) {
		this.separacionBienes = separacionBienes;
	}

	public String getTipoCliente() {
		return this.tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public BigDecimal getValorCustodiaAprox() {
		return this.valorCustodiaAprox;
	}

	public void setValorCustodiaAprox(BigDecimal valorCustodiaAprox) {
		this.valorCustodiaAprox = valorCustodiaAprox;
	}

}