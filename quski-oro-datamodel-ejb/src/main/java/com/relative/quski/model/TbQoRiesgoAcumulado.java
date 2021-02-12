package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tb_qo_riesgo_acumulado database table.
 * 
 */
@Entity
@Table(name="tb_qo_riesgo_acumulado")
public class TbQoRiesgoAcumulado implements Serializable {
	public TbQoRiesgoAcumulado(TbQoCliente tbQoCliente) {
		super();
		this.tbQoCliente = tbQoCliente;
	}
	public TbQoRiesgoAcumulado(TbQoNegociacion tbQoNegociacion) {
		super();
		this.tbQoNegociacion = tbQoNegociacion;
	}
	public TbQoRiesgoAcumulado(TbQoCotizador tbQoCotizador) {
		super();
		this.tbQoCotizador = tbQoCotizador;
	}
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "TB_QO_RIESGO_ACUMULADO_ID_GENERATOR", sequenceName = "SEQ_RIESGO_ACUMULADO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QO_RIESGO_ACUMULADO_ID_GENERATOR")
	private Long id;
	
	@Column(name="codigo_cartera_quski")
	private String codigoCarteraQuski;

	@Column(name="dias_mora_actual")
	private BigDecimal diasMoraActual;

	@Column(name="es_demandada")
	private Boolean esDemandada;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Column(name="estado_operacion")
	private String estadoOperacion;

	@Column(name="estado_primera_cuota_vigente")
	private String estadoPrimeraCuotaVigente;


	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name="fecha_creacion")
	private Date fechaCreacion;


	@Column(name="fecha_efectiva")
	private Date fechaEfectiva;


	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;



	@Column(name="id_moneda")
	private BigDecimal idMoneda;

	@Column(name="id_softbank")
	private BigDecimal idSoftbank;

	@Column(name="interes_mora")
	private BigDecimal interesMora;

	@Column(name="nombre_producto")
	private String nombreProducto;

	@Column(name="numero_cuotas_faltantes")
	private BigDecimal numeroCuotasFaltantes;

	@Column(name="numero_cuotas_totales")
	private BigDecimal numeroCuotasTotales;

	@Column(name="numero_garantias_reales")
	private BigDecimal numeroGarantiasReales;

	@Column(name="numero_operacion")
	private String numeroOperacion;

	@Column(name="primera_cuota_vigente")
	private BigDecimal primeraCuotaVigente;

	private String referencia;

	private BigDecimal saldo;

	@Column(name="tipo_operacion")
	private String tipoOperacion;

	@Column(name="valor_al_dia")
	private BigDecimal valorAlDia;

	@Column(name="valor_al_dia_mas_cuota_actual")
	private BigDecimal valorAlDiaMasCuotaActual;

	@Column(name="valor_cancela_prestamo")
	private BigDecimal valorCancelaPrestamo;

	@Column(name="valor_proyectado_cuota_actual")
	private BigDecimal valorProyectadoCuotaActual;

	//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;

	//bi-directional many-to-one association to TbQoNegociacion
	@ManyToOne
	@JoinColumn(name="id_negociacion")
	private TbQoNegociacion tbQoNegociacion;	
	//bi-directional many-to-one association to TbQoNegociacion
	@ManyToOne
	@JoinColumn(name="id_cotizador")
	private TbQoCotizador tbQoCotizador;

	public TbQoRiesgoAcumulado() {
	}

	public String getCodigoCarteraQuski() {
		return this.codigoCarteraQuski;
	}

	public void setCodigoCarteraQuski(String codigoCarteraQuski) {
		this.codigoCarteraQuski = codigoCarteraQuski;
	}

	public BigDecimal getDiasMoraActual() {
		return this.diasMoraActual;
	}

	public void setDiasMoraActual(BigDecimal diasMoraActual) {
		this.diasMoraActual = diasMoraActual;
	}

	public Boolean getEsDemandada() {
		return this.esDemandada;
	}

	public void setEsDemandada(Boolean esDemandada) {
		this.esDemandada = esDemandada;
	}

	public EstadoEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public String getEstadoOperacion() {
		return this.estadoOperacion;
	}

	public void setEstadoOperacion(String estadoOperacion) {
		this.estadoOperacion = estadoOperacion;
	}

	public String getEstadoPrimeraCuotaVigente() {
		return this.estadoPrimeraCuotaVigente;
	}

	public void setEstadoPrimeraCuotaVigente(String estadoPrimeraCuotaVigente) {
		this.estadoPrimeraCuotaVigente = estadoPrimeraCuotaVigente;
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

	public Date getFechaEfectiva() {
		return this.fechaEfectiva;
	}

	public void setFechaEfectiva(Date fechaEfectiva) {
		this.fechaEfectiva = fechaEfectiva;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getIdMoneda() {
		return this.idMoneda;
	}

	public void setIdMoneda(BigDecimal idMoneda) {
		this.idMoneda = idMoneda;
	}

	public BigDecimal getIdSoftbank() {
		return this.idSoftbank;
	}

	public void setIdSoftbank(BigDecimal idSoftbank) {
		this.idSoftbank = idSoftbank;
	}

	public BigDecimal getInteresMora() {
		return this.interesMora;
	}

	public void setInteresMora(BigDecimal interesMora) {
		this.interesMora = interesMora;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public BigDecimal getNumeroCuotasFaltantes() {
		return this.numeroCuotasFaltantes;
	}

	public void setNumeroCuotasFaltantes(BigDecimal numeroCuotasFaltantes) {
		this.numeroCuotasFaltantes = numeroCuotasFaltantes;
	}

	public BigDecimal getNumeroCuotasTotales() {
		return this.numeroCuotasTotales;
	}

	public void setNumeroCuotasTotales(BigDecimal numeroCuotasTotales) {
		this.numeroCuotasTotales = numeroCuotasTotales;
	}

	public BigDecimal getNumeroGarantiasReales() {
		return this.numeroGarantiasReales;
	}

	public void setNumeroGarantiasReales(BigDecimal numeroGarantiasReales) {
		this.numeroGarantiasReales = numeroGarantiasReales;
	}

	public String getNumeroOperacion() {
		return this.numeroOperacion;
	}

	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	public BigDecimal getPrimeraCuotaVigente() {
		return this.primeraCuotaVigente;
	}

	public void setPrimeraCuotaVigente(BigDecimal primeraCuotaVigente) {
		this.primeraCuotaVigente = primeraCuotaVigente;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public BigDecimal getSaldo() {
		return this.saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getTipoOperacion() {
		return this.tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public BigDecimal getValorAlDia() {
		return this.valorAlDia;
	}

	public void setValorAlDia(BigDecimal valorAlDia) {
		this.valorAlDia = valorAlDia;
	}

	public BigDecimal getValorAlDiaMasCuotaActual() {
		return this.valorAlDiaMasCuotaActual;
	}

	public void setValorAlDiaMasCuotaActual(BigDecimal valorAlDiaMasCuotaActual) {
		this.valorAlDiaMasCuotaActual = valorAlDiaMasCuotaActual;
	}

	public BigDecimal getValorCancelaPrestamo() {
		return this.valorCancelaPrestamo;
	}

	public void setValorCancelaPrestamo(BigDecimal valorCancelaPrestamo) {
		this.valorCancelaPrestamo = valorCancelaPrestamo;
	}

	public BigDecimal getValorProyectadoCuotaActual() {
		return this.valorProyectadoCuotaActual;
	}

	public void setValorProyectadoCuotaActual(BigDecimal valorProyectadoCuotaActual) {
		this.valorProyectadoCuotaActual = valorProyectadoCuotaActual;
	}

	public TbQoCliente getTbQoCliente() {
		return this.tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

	public TbQoNegociacion getTbQoNegociacion() {
		return this.tbQoNegociacion;
	}

	public void setTbQoNegociacion(TbQoNegociacion tbQoNegociacion) {
		this.tbQoNegociacion = tbQoNegociacion;
	}
	public TbQoCotizador getTbQoCotizador() {
		return tbQoCotizador;
	}
	public void setTbQoCotizador(TbQoCotizador tbQoCotizador) {
		this.tbQoCotizador = tbQoCotizador;
	}

}