package com.relative.quski.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tb_mi_tipo_oro database table.
 * 
 */
@Entity
@Table(name="tb_qo_credito_negociacion")
public class TbQoCreditoNegociacion implements Serializable {
private static final long serialVersionUID = 1L;
	
@Id
@SequenceGenerator(name="TB_QO_CREDITO_NEGOCIACION_ID_GENERATOR", sequenceName="SEQ_CREDITO_NEGOCIACION",allocationSize=1)
@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_CREDITO_NEGOCIACION_ID_GENERATOR")
	private Long id;
	@Column(name="plazo_credito")
	private String plazoCredito;
	@Column(name="monto_preaprobado")
	private BigDecimal montoPreaprobado;
	@Column(name="recibir_cliente")
	private BigDecimal recibirCliente;
	@Column(name="costo_nueva_operacion")
	private BigDecimal costoNuevaOperacion;
	@Column(name="costo_custodia")
	private BigDecimal costoCustodia;
	@Column(name="costo_transporte")
	private BigDecimal costoTransporte;
	@Column(name="costo_credito")
	private BigDecimal costoCredito;
	@Column(name="costo_seguro")
	private BigDecimal costoSeguro;
	@Column(name="costo_resguardado")
	private BigDecimal costoResguardo;
	@Column(name="costo_estimado")
	private BigDecimal costoEstimado;
	@Column(name="valor_cuota")
	private BigDecimal valorCuota;
	@Column(name="estado")
	private String estado;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	@Column(name = "fecha_vencimiento")
	private Date fechaVencimiento;
	@Column(name="joyas_seleccionadas")
	private String joyasSeleccionadas;
	@ManyToOne
	@JoinColumn(name="id_negociacion")
	private TbQoNegociacion tbQoNegociacion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlazoCredito() {
		return plazoCredito;
	}
	public void setPlazoCredito(String plazoCredito) {
		this.plazoCredito = plazoCredito;
	}
	public BigDecimal getMontoPreaprobado() {
		return montoPreaprobado;
	}
	public void setMontoPreaprobado(BigDecimal montoPreaprobado) {
		this.montoPreaprobado = montoPreaprobado;
	}
	public BigDecimal getRecibirCliente() {
		return recibirCliente;
	}
	public void setRecibirCliente(BigDecimal recibirCliente) {
		this.recibirCliente = recibirCliente;
	}
	public BigDecimal getCostoNuevaOperacion() {
		return costoNuevaOperacion;
	}
	public void setCostoNuevaOperacion(BigDecimal costoNuevaOperacion) {
		this.costoNuevaOperacion = costoNuevaOperacion;
	}
	public BigDecimal getCostoCustodia() {
		return costoCustodia;
	}
	public void setCostoCustodia(BigDecimal costoCustodia) {
		this.costoCustodia = costoCustodia;
	}
	public BigDecimal getCostoTransporte() {
		return costoTransporte;
	}
	public void setCostoTransporte(BigDecimal costoTransporte) {
		this.costoTransporte = costoTransporte;
	}
	public BigDecimal getCostoCredito() {
		return costoCredito;
	}
	public void setCostoCredito(BigDecimal costoCredito) {
		this.costoCredito = costoCredito;
	}
	public BigDecimal getCostoSeguro() {
		return costoSeguro;
	}
	public void setCostoSeguro(BigDecimal costoSeguro) {
		this.costoSeguro = costoSeguro;
	}
	public BigDecimal getCostoResguardo() {
		return costoResguardo;
	}
	public void setCostoResguardo(BigDecimal costoResguardo) {
		this.costoResguardo = costoResguardo;
	}
	public BigDecimal getCostoEstimado() {
		return costoEstimado;
	}
	public void setCostoEstimado(BigDecimal costoEstimado) {
		this.costoEstimado = costoEstimado;
	}
	public BigDecimal getValorCuota() {
		return valorCuota;
	}
	public void setValorCuota(BigDecimal valorCuota) {
		this.valorCuota = valorCuota;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
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
	public TbQoNegociacion getTbQoNegociacion() {
		return tbQoNegociacion;
	}
	public void setTbQoNegociacion(TbQoNegociacion tbQoNegociacion) {
		this.tbQoNegociacion = tbQoNegociacion;
	}
	public String getJoyasSeleccionadas() {
		return joyasSeleccionadas;
	}
	public void setJoyasSeleccionadas(String joyasSeleccionadas) {
		this.joyasSeleccionadas = joyasSeleccionadas;
	}

	
}
