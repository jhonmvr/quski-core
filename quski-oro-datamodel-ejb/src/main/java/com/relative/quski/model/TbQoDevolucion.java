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
	
	@Column(name="usuario_solicitud")
	private String usuarioSolicitud;

	private String aprobador;

	private String asesor;

	@Column(name="cedula_cliente")
	private String cedulaCliente;
	
	@Column(name="observacion_cancelacion")
	private String observacionCancelacion;
	
	@Column(name="es_migrado")
	private Boolean esMigrado;

	@Column(name="code_detalle_credito")
	private String codeDetalleCredito;

	@Column(name="code_detalle_garantia")
	private String codeDetalleGarantia;
	
	@Column(name="correo_asesor")
	private String correoAsesor;
	
	@Column(name="correo_cliente")
	private String correoCliente;

	@Column(name="code_herederos")
	private String codeHerederos;

	private String codigo;

	@Column(name="codigo_operacion")
	private String codigoOperacion;
	
	@Column(name="genero")
	private String genero;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Column(name="estado_civil")
	private String estadoCivil;


	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Column(name="fecha_entrega")
	private Date fechaEntrega;


	@Column(name="fecha_creacion")
	private Date fechaCreacion;


	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	@Column(name="id_agencia")
	private Long idAgencia;
	
	@Column(name="nombre_agencia_solicitud")
	private String nombreAgenciaSolicitud;
	
	@Column(name="id_agencia_entrega")
	private Long agenciaEntregaId;

	@Column(name="lugar_nacimiento")
	private String lugarNacimiento;

	private String nacionalidad;

	@Column(name="nivel_educacion")
	private String nivelEducacion;

	@Column(name="nombre_cliente")
	private String nombreCliente;

	@Column(name="observaciones")
	private String observaciones;

	@Column(name="separacion_bienes")
	private String separacionBienes;

	@Column(name="tipo_cliente")
	private String tipoCliente;

	@Column(name="fecha_aprobacion_solicitud")
	private Date fechaAprobacionSolicitud;
	
	@Column(name="fecha_efectiva")
	private Date fechaEfectiva;

	@Column(name="fecha_arribo")
	private Date fechaArribo;
	
	@Column(name="funda_actual")
	private String fundaActual;
	
	@Column(name="funda_madre")
	private String fundaMadre;
	
	@Column(name="codigo_operacion_madre")
	private String codigoOperacionMadre;

	@Column(name="valor_custodia_aprox")
	private BigDecimal valorCustodiaAprox;
	

	@Column(name="arribo")
	private Boolean arribo;
	
	@Column(name="devuelto")
	private Boolean devuelto;
	
	@Column(name="observacion_aprobador")
	private String observacionAprobador;

	@Column(name="valor_avaluo")
	private BigDecimal valorAvaluo;
	
	@Column(name="peso_bruto")
	private BigDecimal pesoBruto;
	
	@Column(name="ciudad_tevcol")
	private String ciudadTevcol;
	
	@Column(name="nombre_apoderado")
	private String nombreApoderado;
	
	@Column(name="cedula_apoderado")
	private String cedulaApoderado;

	@Column(name="monto_credito")
	private BigDecimal montoCredito;

	@Column(name="plazo_credito")
	private String plazoCredito;

	@Column(name="tipo_credito")
	private String tipoCredito;

	@Column(name="numero_cuenta_cliente")
	private String numeroCuentaCliente;

	@Column(name="nombre_asesor")
	private String nombreAsesor;

	@Column(name="ciudad_entrega")
	private String ciudadEntrega;
	
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

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getNombreAgenciaSolicitud() {
		return nombreAgenciaSolicitud;
	}

	public void setNombreAgenciaSolicitud(String nombreAgenciaSolicitud) {
		this.nombreAgenciaSolicitud = nombreAgenciaSolicitud;
	}



	public Long getAgenciaEntregaId() {
		return agenciaEntregaId;
	}

	public void setAgenciaEntregaId(Long agenciaEntregaId) {
		this.agenciaEntregaId = agenciaEntregaId;
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

	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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

	public Date getFechaAprobacionSolicitud() {
		return fechaAprobacionSolicitud;
	}

	public void setFechaAprobacionSolicitud(Date fechaAprobacionSolicitud) {
		this.fechaAprobacionSolicitud = fechaAprobacionSolicitud;
	}

	public Date getFechaArribo() {
		return fechaArribo;
	}

	public void setFechaArribo(Date fechaArribo) {
		this.fechaArribo = fechaArribo;
	}

	public String getFundaActual() {
		return fundaActual;
	}

	public void setFundaActual(String fundaActual) {
		this.fundaActual = fundaActual;
	}

	public String getFundaMadre() {
		return fundaMadre;
	}

	public void setFundaMadre(String fundaMadre) {
		this.fundaMadre = fundaMadre;
	}

	public String getCodigoOperacionMadre() {
		return codigoOperacionMadre;
	}

	public void setCodigoOperacionMadre(String codigoOperacionMadre) {
		this.codigoOperacionMadre = codigoOperacionMadre;
	}

	public Boolean getArribo() {
		return arribo;
	}

	public void setArribo(Boolean arribo) {
		this.arribo = arribo;
	}

	public BigDecimal getValorAvaluo() {
		return valorAvaluo;
	}

	public void setValorAvaluo(BigDecimal valorAvaluo) {
		this.valorAvaluo = valorAvaluo;
	}

	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public Boolean getDevuelto() {
		return devuelto;
	}

	public void setDevuelto(Boolean devuelto) {
		this.devuelto = devuelto;
	}

	public String getObservacionAprobador() {
		return observacionAprobador;
	}

	public void setObservacionAprobador(String observacionAprobador) {
		this.observacionAprobador = observacionAprobador;
	}

	public Date getFechaEfectiva() {
		return fechaEfectiva;
	}

	public void setFechaEfectiva(Date fechaEfectiva) {
		this.fechaEfectiva = fechaEfectiva;
	}

	public String getCiudadTevcol() {
		return ciudadTevcol;
	}

	public void setCiudadTevcol(String ciudadTevcol) {
		this.ciudadTevcol = ciudadTevcol;
	}

	public String getNombreApoderado() {
		return nombreApoderado;
	}

	public void setNombreApoderado(String nombreApoderado) {
		this.nombreApoderado = nombreApoderado;
	}

	public String getCedulaApoderado() {
		return cedulaApoderado;
	}

	public void setCedulaApoderado(String cedulaApoderado) {
		this.cedulaApoderado = cedulaApoderado;
	}

	public String getCiudadEntrega() {
		return ciudadEntrega;
	}

	public void setCiudadEntrega(String ciudadEntrega) {
		this.ciudadEntrega = ciudadEntrega;
	}

	public String getObservacionCancelacion() {
		return observacionCancelacion;
	}

	public void setObservacionCancelacion(String observacionCancelacion) {
		this.observacionCancelacion = observacionCancelacion;
	}

	public Boolean getEsMigrado() {
		return esMigrado;
	}

	public void setEsMigrado(Boolean esMigrado) {
		this.esMigrado = esMigrado;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getUsuarioSolicitud() {
		return usuarioSolicitud;
	}

	public void setUsuarioSolicitud(String usuarioSolicitud) {
		this.usuarioSolicitud = usuarioSolicitud;
	}

	public String getCorreoAsesor() {
		return correoAsesor;
	}

	public void setCorreoAsesor(String correoAsesor) {
		this.correoAsesor = correoAsesor;
	}

	public String getCorreoCliente() {
		return correoCliente;
	}

	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

	public BigDecimal getMontoCredito() {
		return montoCredito;
	}

	public void setMontoCredito(BigDecimal montoCredito) {
		this.montoCredito = montoCredito;
	}

	public String getPlazoCredito() {
		return plazoCredito;
	}

	public void setPlazoCredito(String plazoCredito) {
		this.plazoCredito = plazoCredito;
	}

	public String getTipoCredito() {
		return tipoCredito;
	}

	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	public String getNumeroCuentaCliente() {
		return numeroCuentaCliente;
	}

	public void setNumeroCuentaCliente(String numeroCuentaCliente) {
		this.numeroCuentaCliente = numeroCuentaCliente;
	}

	public String getNombreAsesor() {
		return nombreAsesor;
	}

	public void setNombreAsesor(String nombreAsesor) {
		this.nombreAsesor = nombreAsesor;
	}



	
}