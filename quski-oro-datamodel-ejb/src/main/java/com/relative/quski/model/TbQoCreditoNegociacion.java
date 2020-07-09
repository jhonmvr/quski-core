package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

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
	@SequenceGenerator(name="TB_QO_CREDITO_NEGOCIACION_ID_GENERATOR", sequenceName="SEQ_CREDITO_NEGOCIACION",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_CREDITO_NEGOCIACION_ID_GENERATOR")
	private Long id;

	@Column(name="costo_credito")
	private BigDecimal costoCredito;

	@Column(name="costo_custodia")
	private BigDecimal costoCustodia;

	@Column(name="costo_estimado")
	private BigDecimal costoEstimado;

	@Column(name="costo_nueva_operacion")
	private BigDecimal costoNuevaOperacion;

	@Column(name="costo_resguardado")
	private BigDecimal costoResguardado;

	@Column(name="costo_seguro")
	private BigDecimal costoSeguro;

	@Column(name="costo_transporte")
	private BigDecimal costoTransporte;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;
	
	// Ususario Asesor que creo la negociacion.
	@Column(name="id_usuario")
	private String idUsuario;

	@Column(name="joyas_seleccionadas")
	private String joyasSeleccionadas;

	@Column(name="monto_preaprobado")
	private BigDecimal montoPreaprobado;

	@Column(name="plazo_credito")
	private String plazoCredito;

	@Column(name="recibir_cliente")
	private BigDecimal recibirCliente;

	@Column(name="tipo")
	@Enumerated(EnumType.STRING)
	private TipoCreditoNegociacionEnum tipo;

	@Column(name="valor_cuota")
	private BigDecimal valorCuota;

	//bi-directional many-to-one association to TbQoAgencia
	@ManyToOne
	@JoinColumn(name="id_agencia")
	private TbQoAgencia tbQoAgencia;

	//bi-directional many-to-one association to TbQoNegociacion
	@ManyToOne
	@JoinColumn(name="id_negociacion")
	private TbQoNegociacion tbQoNegociacion;

	//bi-directional many-to-one association to TbQoNegociacionCalculo
	@OneToMany(mappedBy="tbQoCreditoNegociacion")
	private List<TbQoNegociacionCalculo> tbQoNegociacionCalculos;

	//bi-directional many-to-one association to TbQoReasignacionActividad
	@OneToMany(mappedBy="tbQoCreditoNegociacion")
	private List<TbQoReasignacionActividad> tbQoReasignacionActividads;

	//bi-directional many-to-one association to TbQoTasacion
	@OneToMany(mappedBy="tbQoCreditoNegociacion")
	private List<TbQoTasacion> tbQoTasacions;

	public TbQoCreditoNegociacion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCostoCredito() {
		return this.costoCredito;
	}

	public void setCostoCredito(BigDecimal costoCredito) {
		this.costoCredito = costoCredito;
	}

	public BigDecimal getCostoCustodia() {
		return this.costoCustodia;
	}

	public void setCostoCustodia(BigDecimal costoCustodia) {
		this.costoCustodia = costoCustodia;
	}

	public BigDecimal getCostoEstimado() {
		return this.costoEstimado;
	}

	public void setCostoEstimado(BigDecimal costoEstimado) {
		this.costoEstimado = costoEstimado;
	}

	public BigDecimal getCostoNuevaOperacion() {
		return this.costoNuevaOperacion;
	}

	public void setCostoNuevaOperacion(BigDecimal costoNuevaOperacion) {
		this.costoNuevaOperacion = costoNuevaOperacion;
	}

	public BigDecimal getCostoResguardado() {
		return this.costoResguardado;
	}

	public void setCostoResguardado(BigDecimal costoResguardado) {
		this.costoResguardado = costoResguardado;
	}

	public BigDecimal getCostoSeguro() {
		return this.costoSeguro;
	}

	public void setCostoSeguro(BigDecimal costoSeguro) {
		this.costoSeguro = costoSeguro;
	}

	public BigDecimal getCostoTransporte() {
		return this.costoTransporte;
	}

	public void setCostoTransporte(BigDecimal costoTransporte) {
		this.costoTransporte = costoTransporte;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
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

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getJoyasSeleccionadas() {
		return this.joyasSeleccionadas;
	}

	public void setJoyasSeleccionadas(String joyasSeleccionadas) {
		this.joyasSeleccionadas = joyasSeleccionadas;
	}

	public BigDecimal getMontoPreaprobado() {
		return this.montoPreaprobado;
	}

	public void setMontoPreaprobado(BigDecimal montoPreaprobado) {
		this.montoPreaprobado = montoPreaprobado;
	}

	public String getPlazoCredito() {
		return this.plazoCredito;
	}

	public void setPlazoCredito(String plazoCredito) {
		this.plazoCredito = plazoCredito;
	}

	public BigDecimal getRecibirCliente() {
		return this.recibirCliente;
	}

	public void setRecibirCliente(BigDecimal recibirCliente) {
		this.recibirCliente = recibirCliente;
	}

	public TipoCreditoNegociacionEnum getTipo() {
		return tipo;
	}


	public void setTipo(TipoCreditoNegociacionEnum tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValorCuota() {
		return this.valorCuota;
	}

	public void setValorCuota(BigDecimal valorCuota) {
		this.valorCuota = valorCuota;
	}

	public TbQoAgencia getTbQoAgencia() {
		return this.tbQoAgencia;
	}

	public void setTbQoAgencia(TbQoAgencia tbQoAgencia) {
		this.tbQoAgencia = tbQoAgencia;
	}

	public TbQoNegociacion getTbQoNegociacion() {
		return this.tbQoNegociacion;
	}

	public void setTbQoNegociacion(TbQoNegociacion tbQoNegociacion) {
		this.tbQoNegociacion = tbQoNegociacion;
	}

	public List<TbQoNegociacionCalculo> getTbQoNegociacionCalculos() {
		return this.tbQoNegociacionCalculos;
	}

	public void setTbQoNegociacionCalculos(List<TbQoNegociacionCalculo> tbQoNegociacionCalculos) {
		this.tbQoNegociacionCalculos = tbQoNegociacionCalculos;
	}

	public TbQoNegociacionCalculo addTbQoNegociacionCalculo(TbQoNegociacionCalculo tbQoNegociacionCalculo) {
		getTbQoNegociacionCalculos().add(tbQoNegociacionCalculo);
		tbQoNegociacionCalculo.setTbQoCreditoNegociacion(this);

		return tbQoNegociacionCalculo;
	}

	public TbQoNegociacionCalculo removeTbQoNegociacionCalculo(TbQoNegociacionCalculo tbQoNegociacionCalculo) {
		getTbQoNegociacionCalculos().remove(tbQoNegociacionCalculo);
		tbQoNegociacionCalculo.setTbQoCreditoNegociacion(null);

		return tbQoNegociacionCalculo;
	}

	public List<TbQoReasignacionActividad> getTbQoReasignacionActividads() {
		return this.tbQoReasignacionActividads;
	}

	public void setTbQoReasignacionActividads(List<TbQoReasignacionActividad> tbQoReasignacionActividads) {
		this.tbQoReasignacionActividads = tbQoReasignacionActividads;
	}

	public TbQoReasignacionActividad addTbQoReasignacionActividad(TbQoReasignacionActividad tbQoReasignacionActividad) {
		getTbQoReasignacionActividads().add(tbQoReasignacionActividad);
		tbQoReasignacionActividad.setTbQoCreditoNegociacion(this);

		return tbQoReasignacionActividad;
	}

	public TbQoReasignacionActividad removeTbQoReasignacionActividad(TbQoReasignacionActividad tbQoReasignacionActividad) {
		getTbQoReasignacionActividads().remove(tbQoReasignacionActividad);
		tbQoReasignacionActividad.setTbQoCreditoNegociacion(null);

		return tbQoReasignacionActividad;
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

}