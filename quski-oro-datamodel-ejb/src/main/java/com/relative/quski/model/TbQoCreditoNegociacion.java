package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.TipoCreditoNegociacionEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_qo_credito_negociacion database table.
 * 
 */
@Entity
@Table(name="tb_qo_credito_negociacion")
public class TbQoCreditoNegociacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TB_QO_CREDITO_NEGOCIACION_ID_GENERATOR", sequenceName = "SEQ_CREDITO_NEGOCIACION", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QO_CREDITO_NEGOCIACION_ID_GENERATOR")
	private Long id;

	@Column(name="a_pagar_cliente")
	private BigDecimal aPagarCliente;

	@Column(name="a_recibir_cliente")
	private BigDecimal aRecibirCliente;
	
	@Column(name="deuda_inicial")
	private BigDecimal deudaInicial;
	
	@Column(name="codigo_cash")
	private String codigoCash;
	
	@Column(name="codigo_devuelto")
	private String codigoDevuelto;
	
	@Column(name="descripcion_devuelto")
	private String descripcionDevuelto;
	
	@Column(name="tabla_amortizacion")
	private String tablaAmortizacion;
	
	private String cobertura;
	
	@Column(name="monto_financiado")
	private BigDecimal montoFinanciado;

	private String codigo;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Column(name="estado_softbank")
	private String estadoSoftbank;


	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	

	@Column(name="pago_dia")
	private Date pagoDia;


	@Column(name="fecha_efectiva")
	private Date fechaEfectiva;


	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	@Column(name="id_agencia")
	private Long idAgencia;
	
	@Column(name="numero_operacion")
	private String numeroOperacion;
	
	@Column(name="numero_operacion_madre")
	private String numeroOperacionMadre;
	
	@Column(name="total_interes_vencimiento")
	private BigDecimal totalInteresVencimiento;
	
	@Column(name="total_costo_nueva_operacion")
	private BigDecimal totalCostoNuevaOperacion;
	
	@Column(name="numero_funda")
	private Long numeroFunda;
	
	@Column(name="descripcion_producto")
	private String descripcionProducto;

	@Column(name="monto_diferido")
	private BigDecimal montoDiferido;

	@Column(name="monto_preaprobado")
	private BigDecimal montoPreaprobado;

	@Column(name="monto_solicitado")
	private BigDecimal montoSolicitado;

	@Column(name="plazo_credito")
	private Long plazoCredito;

	@Column(name="riesgo_total_cliente")
	private BigDecimal riesgoTotalCliente;

	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoCreditoNegociacionEnum tipo;

	@Column(name="tipo_cartera_quski")
	private String tipoCarteraQuski;

	@Column(name="valor_cuota")
	private BigDecimal valorCuota;
	
	@Column(name="codigo_tipo_funda")
	private String codigoTipoFunda;
	
	@Column(name="uri_imagen_sin_funda")
	private String uriImagenSinFunda;
	
	@Column(name="firmante_operacion")
	private String firmanteOperacion;
	
	@Column(name="tipo_cliente")
	private String tipoCliente;
	
	@Column(name="numero_cuenta")
	private String numeroCuenta;
	
	@Column(name="uri_imagen_con_funda")
	private String uriImagenConFunda;
	
	// bi-directional many-to-one association to TbQoNegociacion
	@ManyToOne
	@JoinColumn(name = "id_negociacion")
	private TbQoNegociacion tbQoNegociacion;

	// bi-directional many-to-one association to TbQoTasacion
	@OneToMany(mappedBy = "tbQoCreditoNegociacion")
	private List<TbQoTasacion> tbQoTasacions;
	
	// bi-directional many-to-one association to TbQoTasacion
	@OneToMany(mappedBy = "tbQoCreditoNegociacion")
	private List<TbQoRubro> tbQoRubros;

	@Column(name="costo_custodia")
	private BigDecimal costoCustodia;

	@Column(name="costo_fideicomiso")
	private BigDecimal costoFideicomiso;

	@Column(name="costo_seguro")
	private BigDecimal costoSeguro;

	@Column(name="costo_tasacion")
	private BigDecimal costoTasacion;

	@Column(name="costo_transporte")
	private BigDecimal costoTransporte;

	@Column(name="costo_valoracion")
	private BigDecimal costoValoracion;

	private BigDecimal cuota;

	@Column(name="custodia_devengada")
	private BigDecimal custodiaDevengada;

	@Column(name="dividendo_flujo_planeado")
	private BigDecimal dividendoFlujoPlaneado;

	@Column(name="dividendo_prorrateo")
	private BigDecimal dividendoProrrateo;

	@Column(name="forma_pago_capital")
	private String formaPagoCapital;

	@Column(name="forma_pago_custodia")
	private String formaPagoCustodia;

	@Column(name="forma_pago_custodia_devengada")
	private String formaPagoCustodiaDevengada;

	@Column(name="forma_pago_fideicomiso")
	private String formaPagoFideicomiso;

	@Column(name="forma_pago_gasto_cobranza")
	private String formaPagoGastoCobranza;

	@Column(name="forma_pago_impuesto_solca")
	private String formaPagoImpuestoSolca;

	@Column(name="forma_pago_interes")
	private String formaPagoInteres;

	@Column(name="forma_pago_mora")
	private String formaPagoMora;

	@Column(name="forma_pago_seguro")
	private String formaPagoSeguro;

	@Column(name="forma_pago_tasador")
	private String formaPagoTasador;

	@Column(name="forma_pago_transporte")
	private String formaPagoTransporte;

	@Column(name="forma_pago_valoracion")
	private String formaPagoValoracion;

	@Column(name="gasto_cobranza")
	private BigDecimal gastoCobranza;



	@Column(name="impuesto_solca")
	private BigDecimal impuestoSolca;

	@Column(name="monto_desembolso")
	private BigDecimal montoDesembolso;

	@Column(name="monto_previo_desembolso")
	private BigDecimal montoPrevioDesembolso;


	@Column(name="neto_al_cliente")
	private BigDecimal netoAlCliente;


	@Column(name="periodicidad_plazo")
	private String periodicidadPlazo;

	@Column(name="periodo_plazo")
	private String periodoPlazo;

	@Column(name="peso_funda")
	private String pesoFunda;


	@Column(name="porcentaje_flujo_planeado")
	private BigDecimal porcentajeFlujoPlaneado;


	@Column(name="saldo_capital_renov")
	private BigDecimal saldoCapitalRenov;

	@Column(name="saldo_interes")
	private BigDecimal saldoInteres;

	@Column(name="saldo_mora")
	private BigDecimal saldoMora;


	@Column(name="tipo_oferta")
	private String tipoOferta;

	@Column(name="total_costos_operacion_anterior")
	private BigDecimal totalCostosOperacionAnterior;

	@Column(name="total_gastos_nueva_operacion")
	private BigDecimal totalGastosNuevaOperacion;

	@Column(name="numero_coutas")
	private Long numeroCuotas;

	@Column(name="valor_a_pagar")
	private BigDecimal valorAPagar;

	@Column(name="valor_a_recibir")
	private BigDecimal valorARecibir;
	
	@Column(name="identificacion_codeudor")
	private String identificacionCodeudor;
	
	@Column(name="nombre_completo_codeudor")
	private String nombreCompletoCodeudor;
	
	@Column(name="fecha_nacimiento_codeudor")
	private Date fechaNacimientoCodeudor;
	
	@Column(name="identificacion_apoderado")
	private String identificacionApoderado;
	
	@Column(name="nombre_completo_apoderado")
	private String nombreCompletoApoderado;
	
	@Column(name="fecha_nacimiento_apoderado")
	private Date fechaNacimientoApoderado;
	
	@Column(name="excepcion_operativa")
	private String excepcionOperativa;
	
	@Column(name="fecha_regularizacion")
	private Date fechaRegularizacion;

	
	public TbQoCreditoNegociacion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public TipoCreditoNegociacionEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoCreditoNegociacionEnum tipo) {
		this.tipo = tipo;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getNumeroFunda() {
		return numeroFunda;
	}

	public void setNumeroFunda(Long numeroFunda) {
		this.numeroFunda = numeroFunda;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public String getEstadoSoftbank() {
		return this.estadoSoftbank;
	}

	public void setEstadoSoftbank(String estadoSoftbank) {
		this.estadoSoftbank = estadoSoftbank;
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
	
	public BigDecimal getMontoDiferido() {
		return this.montoDiferido;
	}

	public void setMontoDiferido(BigDecimal montoDiferido) {
		this.montoDiferido = montoDiferido;
	}

	public BigDecimal getMontoPreaprobado() {
		return this.montoPreaprobado;
	}

	public void setMontoPreaprobado(BigDecimal montoPreaprobado) {
		this.montoPreaprobado = montoPreaprobado;
	}

	public BigDecimal getMontoSolicitado() {
		return this.montoSolicitado;
	}

	public void setMontoSolicitado(BigDecimal montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}
	
	public Long getPlazoCredito() {
		return this.plazoCredito;
	}

	public void setPlazoCredito(Long plazoCredito) {
		this.plazoCredito = plazoCredito;
	}

	public BigDecimal getRiesgoTotalCliente() {
		return this.riesgoTotalCliente;
	}

	public void setRiesgoTotalCliente(BigDecimal riesgoTotalCliente) {
		this.riesgoTotalCliente = riesgoTotalCliente;
	}

	public String getTipoCarteraQuski() {
		return this.tipoCarteraQuski;
	}

	public void setTipoCarteraQuski(String tipoCarteraQuski) {
		this.tipoCarteraQuski = tipoCarteraQuski;
	}

	public BigDecimal getValorCuota() {
		return this.valorCuota;
	}

	public void setValorCuota(BigDecimal valorCuota) {
		this.valorCuota = valorCuota;
	}
	public TbQoNegociacion getTbQoNegociacion() {
		return this.tbQoNegociacion;
	}

	public void setTbQoNegociacion(TbQoNegociacion tbQoNegociacion) {
		this.tbQoNegociacion = tbQoNegociacion;
	}

	public List<TbQoTasacion> getTbQoTasacions() {
		return this.tbQoTasacions;
	}

	public void setTbQoTasacions(List<TbQoTasacion> tbQoTasacions) {
		this.tbQoTasacions = tbQoTasacions;
	}

	public TbQoTasacion addTbQoTasacion(TbQoTasacion tbQoTasacion) {
		getTbQoTasacions().add(tbQoTasacion);
		tbQoTasacion.setTbQoCreditoNegociacion(this);

		return tbQoTasacion;
	}

	public TbQoTasacion removeTbQoTasacion(TbQoTasacion tbQoTasacion) {
		getTbQoTasacions().remove(tbQoTasacion);
		tbQoTasacion.setTbQoCreditoNegociacion(null);

		return tbQoTasacion;
	}

	public List<TbQoRubro> getTbQoRubros() {
		return this.tbQoRubros;
	}

	public String getCodigoCash() {
		return codigoCash;
	}

	public void setCodigoCash(String codigoCash) {
		this.codigoCash = codigoCash;
	}

	public String getCodigoDevuelto() {
		return codigoDevuelto;
	}

	public void setCodigoDevuelto(String codigoDevuelto) {
		this.codigoDevuelto = codigoDevuelto;
	}

	public String getDescripcionDevuelto() {
		return descripcionDevuelto;
	}

	public void setDescripcionDevuelto(String descripcionDevuelto) {
		this.descripcionDevuelto = descripcionDevuelto;
	}

	public void setTbQoRubros(List<TbQoRubro> tbQoRubros) {
		this.tbQoRubros = tbQoRubros;
	}

	public TbQoRubro addTbQoRubro(TbQoRubro tbQoRubro) {
		getTbQoRubros().add(tbQoRubro);
		tbQoRubro.setTbQoCreditoNegociacion(this);
		return tbQoRubro;
	}

	public TbQoRubro removeTbQoRubro(TbQoRubro tbQoRubro) {
		getTbQoRubros().remove(tbQoRubro);
		tbQoRubro.setTbQoCreditoNegociacion(null);
		return tbQoRubro;
	}

	public Date getPagoDia() {
		return pagoDia;
	}

	public void setPagoDia(Date pagoDia) {
		this.pagoDia = pagoDia;
	}
	
	public String getCodigoTipoFunda() {
		return codigoTipoFunda;
	}

	public void setCodigoTipoFunda(String codigoTipoFunda) {
		this.codigoTipoFunda = codigoTipoFunda;
	}

	public String getUriImagenSinFunda() {
		return uriImagenSinFunda;
	}

	public void setUriImagenSinFunda(String uriImagenSinFunda) {
		this.uriImagenSinFunda = uriImagenSinFunda;
	}

	public String getUriImagenConFunda() {
		return uriImagenConFunda;
	}

	public void setUriImagenConFunda(String uriImagenConFunda) {
		this.uriImagenConFunda = uriImagenConFunda;
	}

	public BigDecimal getaPagarCliente() {
		return aPagarCliente;
	}

	public void setaPagarCliente(BigDecimal aPagarCliente) {
		this.aPagarCliente = aPagarCliente;
	}

	public BigDecimal getaRecibirCliente() {
		return aRecibirCliente;
	}

	public void setaRecibirCliente(BigDecimal aRecibirCliente) {
		this.aRecibirCliente = aRecibirCliente;
	}

	public String getTablaAmortizacion() {
		return tablaAmortizacion;
	}

	public void setTablaAmortizacion(String tablaAmortizacion) {
		this.tablaAmortizacion = tablaAmortizacion;
	}

	public BigDecimal getMontoFinanciado() {
		return montoFinanciado;
	}

	public void setMontoFinanciado(BigDecimal montoFinanciado) {
		this.montoFinanciado = montoFinanciado;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	public BigDecimal getTotalCostoNuevaOperacion() {
		return totalCostoNuevaOperacion;
	}

	public void setTotalCostoNuevaOperacion(BigDecimal totalCostoNuevaOperacion) {
		this.totalCostoNuevaOperacion = totalCostoNuevaOperacion;
	}

	public BigDecimal getTotalInteresVencimiento() {
		return totalInteresVencimiento;
	}

	public void setTotalInteresVencimiento(BigDecimal totalInteresVencimiento) {
		this.totalInteresVencimiento = totalInteresVencimiento;
	}

	public BigDecimal getDeudaInicial() {
		return deudaInicial;
	}

	public void setDeudaInicial(BigDecimal deudaInicial) {
		this.deudaInicial = deudaInicial;
	}

	public BigDecimal getCostoCustodia() {
		return costoCustodia;
	}

	public void setCostoCustodia(BigDecimal costoCustodia) {
		this.costoCustodia = costoCustodia;
	}

	public BigDecimal getCostoFideicomiso() {
		return costoFideicomiso;
	}

	public void setCostoFideicomiso(BigDecimal costoFideicomiso) {
		this.costoFideicomiso = costoFideicomiso;
	}

	public BigDecimal getCostoSeguro() {
		return costoSeguro;
	}

	public void setCostoSeguro(BigDecimal costoSeguro) {
		this.costoSeguro = costoSeguro;
	}

	public BigDecimal getCostoTasacion() {
		return costoTasacion;
	}

	public void setCostoTasacion(BigDecimal costoTasacion) {
		this.costoTasacion = costoTasacion;
	}

	public BigDecimal getCostoTransporte() {
		return costoTransporte;
	}

	public void setCostoTransporte(BigDecimal costoTransporte) {
		this.costoTransporte = costoTransporte;
	}

	public BigDecimal getCostoValoracion() {
		return costoValoracion;
	}

	public void setCostoValoracion(BigDecimal costoValoracion) {
		this.costoValoracion = costoValoracion;
	}

	public BigDecimal getCuota() {
		return cuota;
	}

	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}

	public BigDecimal getCustodiaDevengada() {
		return custodiaDevengada;
	}

	public void setCustodiaDevengada(BigDecimal custodiaDevengada) {
		this.custodiaDevengada = custodiaDevengada;
	}

	public BigDecimal getDividendoFlujoPlaneado() {
		return dividendoFlujoPlaneado;
	}

	public void setDividendoFlujoPlaneado(BigDecimal dividendoFlujoPlaneado) {
		this.dividendoFlujoPlaneado = dividendoFlujoPlaneado;
	}

	public BigDecimal getDividendoProrrateo() {
		return dividendoProrrateo;
	}

	public void setDividendoProrrateo(BigDecimal dividendoProrrateo) {
		this.dividendoProrrateo = dividendoProrrateo;
	}

	public String getFormaPagoCapital() {
		return formaPagoCapital;
	}

	public void setFormaPagoCapital(String formaPagoCapital) {
		this.formaPagoCapital = formaPagoCapital;
	}

	public String getFormaPagoCustodia() {
		return formaPagoCustodia;
	}

	public void setFormaPagoCustodia(String formaPagoCustodia) {
		this.formaPagoCustodia = formaPagoCustodia;
	}

	public String getFormaPagoCustodiaDevengada() {
		return formaPagoCustodiaDevengada;
	}

	public void setFormaPagoCustodiaDevengada(String formaPagoCustodiaDevengada) {
		this.formaPagoCustodiaDevengada = formaPagoCustodiaDevengada;
	}

	public String getFormaPagoFideicomiso() {
		return formaPagoFideicomiso;
	}

	public void setFormaPagoFideicomiso(String formaPagoFideicomiso) {
		this.formaPagoFideicomiso = formaPagoFideicomiso;
	}

	public String getFormaPagoGastoCobranza() {
		return formaPagoGastoCobranza;
	}

	public void setFormaPagoGastoCobranza(String formaPagoGastoCobranza) {
		this.formaPagoGastoCobranza = formaPagoGastoCobranza;
	}

	public String getFormaPagoImpuestoSolca() {
		return formaPagoImpuestoSolca;
	}

	public void setFormaPagoImpuestoSolca(String formaPagoImpuestoSolca) {
		this.formaPagoImpuestoSolca = formaPagoImpuestoSolca;
	}

	public String getFormaPagoInteres() {
		return formaPagoInteres;
	}

	public void setFormaPagoInteres(String formaPagoInteres) {
		this.formaPagoInteres = formaPagoInteres;
	}

	public String getFormaPagoMora() {
		return formaPagoMora;
	}

	public void setFormaPagoMora(String formaPagoMora) {
		this.formaPagoMora = formaPagoMora;
	}

	public String getFormaPagoSeguro() {
		return formaPagoSeguro;
	}

	public void setFormaPagoSeguro(String formaPagoSeguro) {
		this.formaPagoSeguro = formaPagoSeguro;
	}

	public String getFormaPagoTasador() {
		return formaPagoTasador;
	}

	public void setFormaPagoTasador(String formaPagoTasador) {
		this.formaPagoTasador = formaPagoTasador;
	}

	public String getFormaPagoTransporte() {
		return formaPagoTransporte;
	}

	public void setFormaPagoTransporte(String formaPagoTransporte) {
		this.formaPagoTransporte = formaPagoTransporte;
	}

	public String getFormaPagoValoracion() {
		return formaPagoValoracion;
	}

	public void setFormaPagoValoracion(String formaPagoValoracion) {
		this.formaPagoValoracion = formaPagoValoracion;
	}

	public BigDecimal getGastoCobranza() {
		return gastoCobranza;
	}

	public void setGastoCobranza(BigDecimal gastoCobranza) {
		this.gastoCobranza = gastoCobranza;
	}

	public BigDecimal getImpuestoSolca() {
		return impuestoSolca;
	}

	public void setImpuestoSolca(BigDecimal impuestoSolca) {
		this.impuestoSolca = impuestoSolca;
	}

	public BigDecimal getMontoDesembolso() {
		return montoDesembolso;
	}

	public void setMontoDesembolso(BigDecimal montoDesembolso) {
		this.montoDesembolso = montoDesembolso;
	}

	public BigDecimal getMontoPrevioDesembolso() {
		return montoPrevioDesembolso;
	}

	public void setMontoPrevioDesembolso(BigDecimal montoPrevioDesembolso) {
		this.montoPrevioDesembolso = montoPrevioDesembolso;
	}

	public BigDecimal getNetoAlCliente() {
		return netoAlCliente;
	}

	public void setNetoAlCliente(BigDecimal netoAlCliente) {
		this.netoAlCliente = netoAlCliente;
	}

	public String getPeriodicidadPlazo() {
		return periodicidadPlazo;
	}

	public void setPeriodicidadPlazo(String periodicidadPlazo) {
		this.periodicidadPlazo = periodicidadPlazo;
	}

	public String getPeriodoPlazo() {
		return periodoPlazo;
	}

	public void setPeriodoPlazo(String periodoPlazo) {
		this.periodoPlazo = periodoPlazo;
	}

	public String getPesoFunda() {
		return pesoFunda;
	}

	public void setPesoFunda(String pesoFunda) {
		this.pesoFunda = pesoFunda;
	}

	public BigDecimal getPorcentajeFlujoPlaneado() {
		return porcentajeFlujoPlaneado;
	}

	public void setPorcentajeFlujoPlaneado(BigDecimal porcentajeFlujoPlaneado) {
		this.porcentajeFlujoPlaneado = porcentajeFlujoPlaneado;
	}

	public BigDecimal getSaldoCapitalRenov() {
		return saldoCapitalRenov;
	}

	public void setSaldoCapitalRenov(BigDecimal saldoCapitalRenov) {
		this.saldoCapitalRenov = saldoCapitalRenov;
	}

	public BigDecimal getSaldoInteres() {
		return saldoInteres;
	}

	public void setSaldoInteres(BigDecimal saldoInteres) {
		this.saldoInteres = saldoInteres;
	}

	public BigDecimal getSaldoMora() {
		return saldoMora;
	}

	public void setSaldoMora(BigDecimal saldoMora) {
		this.saldoMora = saldoMora;
	}

	public String getTipoOferta() {
		return tipoOferta;
	}

	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	public BigDecimal getTotalCostosOperacionAnterior() {
		return totalCostosOperacionAnterior;
	}

	public void setTotalCostosOperacionAnterior(BigDecimal totalCostosOperacionAnterior) {
		this.totalCostosOperacionAnterior = totalCostosOperacionAnterior;
	}

	public BigDecimal getTotalGastosNuevaOperacion() {
		return totalGastosNuevaOperacion;
	}

	public void setTotalGastosNuevaOperacion(BigDecimal totalGastosNuevaOperacion) {
		this.totalGastosNuevaOperacion = totalGastosNuevaOperacion;
	}

	public BigDecimal getValorAPagar() {
		return valorAPagar;
	}

	public void setValorAPagar(BigDecimal valorAPagar) {
		this.valorAPagar = valorAPagar;
	}

	public BigDecimal getValorARecibir() {
		return valorARecibir;
	}

	public void setValorARecibir(BigDecimal valorARecibir) {
		this.valorARecibir = valorARecibir;
	}

	public String getFirmanteOperacion() {
		return firmanteOperacion;
	}

	public void setFirmanteOperacion(String firmanteOperacion) {
		this.firmanteOperacion = firmanteOperacion;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Long getNumeroCuotas() {
		return numeroCuotas;
	}

	public void setNumeroCuotas(Long numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	public String getCobertura() {
		return cobertura;
	}

	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}

	public String getNumeroOperacionMadre() {
		return numeroOperacionMadre;
	}

	public void setNumeroOperacionMadre(String numeroOperacionMadre) {
		this.numeroOperacionMadre = numeroOperacionMadre;
	}

	public String getIdentificacionCodeudor() {
		return identificacionCodeudor;
	}

	public void setIdentificacionCodeudor(String identificacionCodeudor) {
		this.identificacionCodeudor = identificacionCodeudor;
	}

	public String getNombreCompletoCodeudor() {
		return nombreCompletoCodeudor;
	}

	public void setNombreCompletoCodeudor(String nombreCompletoCodeudor) {
		this.nombreCompletoCodeudor = nombreCompletoCodeudor;
	}

	public Date getFechaNacimientoCodeudor() {
		return fechaNacimientoCodeudor;
	}

	public void setFechaNacimientoCodeudor(Date fechaNacimientoCodeudor) {
		this.fechaNacimientoCodeudor = fechaNacimientoCodeudor;
	}

	public String getIdentificacionApoderado() {
		return identificacionApoderado;
	}

	public void setIdentificacionApoderado(String identificacionApoderado) {
		this.identificacionApoderado = identificacionApoderado;
	}

	public String getNombreCompletoApoderado() {
		return nombreCompletoApoderado;
	}

	public void setNombreCompletoApoderado(String nombreCompletoApoderado) {
		this.nombreCompletoApoderado = nombreCompletoApoderado;
	}

	public Date getFechaNacimientoApoderado() {
		return fechaNacimientoApoderado;
	}

	public void setFechaNacimientoApoderado(Date fechaNacimientoApoderado) {
		this.fechaNacimientoApoderado = fechaNacimientoApoderado;
	}

	public String getExcepcionOperativa() {
		return excepcionOperativa;
	}

	public void setExcepcionOperativa(String excepcionOperativa) {
		this.excepcionOperativa = excepcionOperativa;
	}

	public Date getFechaRegularizacion() {
		return fechaRegularizacion;
	}

	public void setFechaRegularizacion(Date fechaRegularizacion) {
		this.fechaRegularizacion = fechaRegularizacion;
	}
}