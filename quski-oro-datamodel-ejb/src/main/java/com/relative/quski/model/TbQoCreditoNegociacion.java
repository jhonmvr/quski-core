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
	
	@Column(name="tabla_amortizacion")
	private String tablaAmortizacion;
	
	@Column(name="monto_financiado")
	private BigDecimal montoFinanciado;

	private String codigo;

	@Column(name="destino_operacion")
	private String destinoOperacion;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Column(name="estado_softbank")
	private String estadoSoftbank;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name="pago_dia")
	private Date pagoDia;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_efectiva")
	private Date fechaEfectiva;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	@Column(name="id_agencia")
	private Long idAgencia;
	
	@Column(name="numero_funda")
	private Long numeroFunda;
	
	@Column(name="peso_funda")
	private String pesoFunda;
	
	@Column(name="descripcion_producto")
	private String descripcionProducto;

	@Column(name="monto_desembolso")
	private BigDecimal montoDesembolso;

	@Column(name="monto_diferido")
	private BigDecimal montoDiferido;

	@Column(name="monto_preaprobado")
	private BigDecimal montoPreaprobado;

	@Column(name="monto_solicitado")
	private BigDecimal montoSolicitado;

	@Column(name="neto_al_cliente")
	private BigDecimal netoAlCliente;

	@Column(name="plazo_credito")
	private Long plazoCredito;

	@Column(name="riesgo_total_cliente")
	private BigDecimal riesgoTotalCliente;

	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoCreditoNegociacionEnum tipo;

	@Column(name="tipo_cartera_quski")
	private String tipoCarteraQuski;

	@Column(name="total_costo_nueva_operacion")
	private BigDecimal totalCostoNuevaOperacion;

	@Column(name="valor_cuota")
	private BigDecimal valorCuota;
	
	@Column(name="total_peso_bruto_garantia")
	private BigDecimal totalPesoBrutoGarantia;
	
	@Column(name="total_peso_bruto_con_funda")
	private BigDecimal totalPesoBrutoConFunda;
	
	@Column(name="total_peso_neto")
	private BigDecimal totalPesoNeto;
	
	@Column(name="tota_peso_neto_con_funda")
	private BigDecimal totaPesoNetoConFunda;
	
	@Column(name="codigo_tipo_funda")
	private String codigoTipoFunda;
	
	@Column(name="total_valor_avaluo")
	private BigDecimal totalValorAvaluo;
	
	@Column(name="total_valor_comercial")
	private BigDecimal totalValorComercial;
	
	@Column(name="total_valor_realizacion")
	private BigDecimal totalValorRealizacion;
	
	@Column(name="uri_imagen_sin_funda")
	private String uriImagenSinFunda;
	
	@Column(name="numero_cuenta")
	private String numeroCuenta;
	
	@Column(name="uri_imagen_con_funda")
	private String uriImagenConFunda;
	
	@Column(name="identificacion_codeudor")
	private  String identificacionCodeudor;
	
	@Column(name="nombre_completo_codeudor")
	private  String nombreCompletoCodeudor;
	
	@Column(name="fecha_nacimiento_codeudor")
	private  Date   fechaNacimientoCodeudor;

	@Column(name="identificacion_apoderado")
	private  String identificacionApoderado;
	
	@Column(name="nombre_completo_apoderado")
	private  String nombreCompletoApoderado;
	
	@Column(name="fecha_nacimiento_apoderado")
	private  Date   fechaNacimientoApoderado;
	
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

	public String getPesoFunda() {
		return pesoFunda;
	}

	public void setPesoFunda(String pesoFunda) {
		this.pesoFunda = pesoFunda;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getDestinoOperacion() {
		return this.destinoOperacion;
	}

	public void setDestinoOperacion(String destinoOperacion) {
		this.destinoOperacion = destinoOperacion;
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

	public BigDecimal getMontoDesembolso() {
		return this.montoDesembolso;
	}

	public void setMontoDesembolso(BigDecimal montoDesembolso) {
		this.montoDesembolso = montoDesembolso;
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

	public BigDecimal getNetoAlCliente() {
		return this.netoAlCliente;
	}

	public void setNetoAlCliente(BigDecimal netoAlCliente) {
		this.netoAlCliente = netoAlCliente;
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

	public BigDecimal getTotalCostoNuevaOperacion() {
		return this.totalCostoNuevaOperacion;
	}

	public void setTotalCostoNuevaOperacion(BigDecimal totalCostoNuevaOperacion) {
		this.totalCostoNuevaOperacion = totalCostoNuevaOperacion;
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

	public BigDecimal getTotalPesoBrutoGarantia() {
		return totalPesoBrutoGarantia;
	}

	public void setTotalPesoBrutoGarantia(BigDecimal totalPesoBrutoGarantia) {
		this.totalPesoBrutoGarantia = totalPesoBrutoGarantia;
	}

	public BigDecimal getTotalPesoBrutoConFunda() {
		return totalPesoBrutoConFunda;
	}

	public void setTotalPesoBrutoConFunda(BigDecimal totalPesoBrutoConFunda) {
		this.totalPesoBrutoConFunda = totalPesoBrutoConFunda;
	}

	public BigDecimal getTotalPesoNeto() {
		return totalPesoNeto;
	}

	public void setTotalPesoNeto(BigDecimal totalPesoNeto) {
		this.totalPesoNeto = totalPesoNeto;
	}

	public BigDecimal getTotaPesoNetoConFunda() {
		return totaPesoNetoConFunda;
	}

	public void setTotaPesoNetoConFunda(BigDecimal totaPesoNetoConFunda) {
		this.totaPesoNetoConFunda = totaPesoNetoConFunda;
	}

	public String getCodigoTipoFunda() {
		return codigoTipoFunda;
	}

	public void setCodigoTipoFunda(String codigoTipoFunda) {
		this.codigoTipoFunda = codigoTipoFunda;
	}

	public BigDecimal getTotalValorAvaluo() {
		return totalValorAvaluo;
	}

	public void setTotalValorAvaluo(BigDecimal totalValorAvaluo) {
		this.totalValorAvaluo = totalValorAvaluo;
	}

	public BigDecimal getTotalValorComercial() {
		return totalValorComercial;
	}

	public void setTotalValorComercial(BigDecimal totalValorComercial) {
		this.totalValorComercial = totalValorComercial;
	}

	public BigDecimal getTotalValorRealizacion() {
		return totalValorRealizacion;
	}

	public void setTotalValorRealizacion(BigDecimal totalValorRealizacion) {
		this.totalValorRealizacion = totalValorRealizacion;
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

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

}