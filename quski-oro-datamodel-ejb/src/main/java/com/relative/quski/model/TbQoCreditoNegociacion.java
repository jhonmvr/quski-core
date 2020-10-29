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

	@Column(name="joyas_seleccionadas")
	private String joyasSeleccionadas;

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

	public BigDecimal getAPagarCliente() {
		return this.aPagarCliente;
	}

	public void setAPagarCliente(BigDecimal aPagarCliente) {
		this.aPagarCliente = aPagarCliente;
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

	public BigDecimal getARecibirCliente() {
		return this.aRecibirCliente;
	}

	public void setARecibirCliente(BigDecimal aRecibirCliente) {
		this.aRecibirCliente = aRecibirCliente;
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

	public String getJoyasSeleccionadas() {
		return this.joyasSeleccionadas;
	}

	public void setJoyasSeleccionadas(String joyasSeleccionadas) {
		this.joyasSeleccionadas = joyasSeleccionadas;
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

}