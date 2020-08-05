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
@Table(name = "tb_qo_detalle_credito")
public class TbQoDetalleCredito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TB_QO_DETALLE_CREDITO_ID_GENERATOR", sequenceName = "SEQ_DETALLE_CREDITO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QO_DETALLE_CREDITO_ID_GENERATOR")
	private Long id;

	@Column(name = "costo_credito")
	private BigDecimal costoCredito;

	@Column(name = "costo_custodia")
	private BigDecimal costoCustodia;

	@Column(name = "costo_estimado")
	private BigDecimal costoEstimado;

	@Column(name = "costo_nueva_operacion")
	private BigDecimal costoNuevaOperacion;

	@Column(name = "costo_resguardado")
	private BigDecimal costoResguardado;

	@Column(name = "costo_seguro")
	private BigDecimal costoSeguro;

	@Column(name = "costo_tasacion")
	private BigDecimal costoTasacion;

	@Column(name = "costo_transporte")
	private BigDecimal costoTransporte;

	@Column(name = "costo_valoracion")
	private BigDecimal costoValoracion;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Column(name = "monto_preaprobado")
	private BigDecimal montoPreaprobado;

	@Column(name = "plazo")
	private BigDecimal plazo;

	@Column(name = "recibir_cliente")
	private BigDecimal recibirCliente;
	
	@Column(name = "periodo_plazo")
	private String periodoPlazo;

	private BigDecimal solca;

	@Column(name = "valor_cuota")
	private BigDecimal valorCuota;

	// bi-directional many-to-one association to TbQoCotizador
	@ManyToOne
	@JoinColumn(name = "id_cotizador")
	private TbQoCotizador tbQoCotizador;

	public TbQoDetalleCredito() {
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

	public EstadoEnum getEstado() {
		return estado;
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

	public BigDecimal getMontoPreaprobado() {
		return this.montoPreaprobado;
	}

	public void setMontoPreaprobado(BigDecimal montoPreaprobado) {
		this.montoPreaprobado = montoPreaprobado;
	}

	public BigDecimal getPlazo() {
		return this.plazo;
	}

	public void setPlazo(BigDecimal plazo) {
		this.plazo = plazo;
	}

	public String getPeriodoPlazo() {
		return periodoPlazo;
	}

	public void setPeriodoPlazo(String periodoPlazo) {
		this.periodoPlazo = periodoPlazo;
	}

	public BigDecimal getRecibirCliente() {
		return this.recibirCliente;
	}

	public void setRecibirCliente(BigDecimal recibirCliente) {
		this.recibirCliente = recibirCliente;
	}

	public BigDecimal getValorCuota() {
		return this.valorCuota;
	}

	public void setValorCuota(BigDecimal valorCuota) {
		this.valorCuota = valorCuota;
	}

	public TbQoCotizador getTbQoCotizador() {
		return this.tbQoCotizador;
	}

	public void setTbQoCotizador(TbQoCotizador tbQoCotizador) {
		this.tbQoCotizador = tbQoCotizador;
	}

	public BigDecimal getCostoTasacion() {
		return costoTasacion;
	}

	public void setCostoTasacion(BigDecimal costoTasacion) {
		this.costoTasacion = costoTasacion;
	}

	public BigDecimal getCostoValoracion() {
		return costoValoracion;
	}

	public void setCostoValoracion(BigDecimal costoValoracion) {
		this.costoValoracion = costoValoracion;
	}

	public BigDecimal getSolca() {
		return solca;
	}

	public void setSolca(BigDecimal solca) {
		this.solca = solca;
	}

}