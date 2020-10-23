package com.relative.quski.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

/**
 * The persistent class for the tb_qo_detalle_credito database table.
 * 
 */
@Entity
@Table(name = "tb_qo_devolucion")
public class TbQoDevolucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TB_QO_DEVOLUCION_ID_GENERATOR", sequenceName = "SEQ_DEVOLUCION", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QO_DEVOLUCION_ID_GENERATOR")
	private Long id;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "asesor")
	private String asesor;

	@Column(name = "aprobador")
	private BigDecimal aprobador;
	
	@Column(name = "id_agencia")
	private Long idAgencia;

	@Column(name = "nombre_cliente")
	private BigDecimal nombreCliente;

	@Column(name = "cedula_cliente")
	private BigDecimal cedulaCliente;

	@Column(name = "codigo_operacion")
	private BigDecimal codigoOperacion;

	@Column(name = "nivel_educacion")
	private BigDecimal nivelEducacion;

	@Column(name = "estado_civil")
	private BigDecimal estadoCivil;

	@Column(name = "separacion_bienes")
	private BigDecimal separacionBienes;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fechaNacimiento")
	private Date fechaNacimiento;

	@Column(name = "nacionalidad")
	private String nacionalidad;

	@Column(name = "lugar_nacimiento")
	private String lugarNacimiento;
	
	@Column(name = "tipo_cliente")
	private String tipoCliente;
	
	@Column(name = "observaciones")
	private String observaciones;

	@Column(name = "agencia_entrega")
	private String agenciaEntrega;

	@Column(name = "valor_custodia")
	private BigDecimal valorCustodia;

	@Column(name = "code_herederos")
	private String codeHerederos;
	
	@Column(name = "code_detalle_credito")
	private String codeDetalleCredito;
	
	@Column(name = "code_detalle_garantia")
	private String codeDetalleGarantia;
	
	public TbQoDevolucion() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getAsesor() {
		return asesor;
	}

	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}

	public BigDecimal getAprobador() {
		return aprobador;
	}

	public void setAprobador(BigDecimal aprobador) {
		this.aprobador = aprobador;
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public BigDecimal getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(BigDecimal nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public BigDecimal getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(BigDecimal cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public BigDecimal getCodigoOperacion() {
		return codigoOperacion;
	}

	public void setCodigoOperacion(BigDecimal codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public BigDecimal getNivelEducacion() {
		return nivelEducacion;
	}

	public void setNivelEducacion(BigDecimal nivelEducacion) {
		this.nivelEducacion = nivelEducacion;
	}

	public BigDecimal getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(BigDecimal estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public BigDecimal getSeparacionBienes() {
		return separacionBienes;
	}

	public void setSeparacionBienes(BigDecimal separacionBienes) {
		this.separacionBienes = separacionBienes;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	

	public BigDecimal getValorCustodia() {
		return valorCustodia;
	}

	public void setValorCustodia(BigDecimal valorCustodia) {
		this.valorCustodia = valorCustodia;
	}

	public String getAgenciaEntrega() {
		return agenciaEntrega;
	}

	public void setAgenciaEntrega(String agenciaEntrega) {
		this.agenciaEntrega = agenciaEntrega;
	}

	public String getCodeHerederos() {
		return codeHerederos;
	}

	public void setCodeHerederos(String codeHerederos) {
		this.codeHerederos = codeHerederos;
	}

	public String getCodeDetalleCredito() {
		return codeDetalleCredito;
	}

	public void setCodeDetalleCredito(String codeDetalleCredito) {
		this.codeDetalleCredito = codeDetalleCredito;
	}

	public String getCodeDetalleGarantia() {
		return codeDetalleGarantia;
	}

	public void setCodeDetalleGarantia(String codeDetalleGarantia) {
		this.codeDetalleGarantia = codeDetalleGarantia;
	}



	

}