package com.relative.quski.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.TipoCreditoNegociacionEnum;

/**
 * The persistent class for the tb_qo_credito_negociacion database table.
 * 
 */
@Entity
@Table(name = "tb_qo_credito_negociacion")
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

	@Column(name = "costo_transporte")
	private BigDecimal costoTransporte;
	
	@Column(name="costo_valoracion")
	private BigDecimal costoValoracion;
	
	@Column(name="costo_tasacion")
	private String costoTasacion;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_vencimiento")
	private Date fechaVencimiento;
	
	@Column(name="forma_pago_custodia")
	private String formaPagoCustodia;

	@Column(name="forma_pago_resguardo")
	private String formaPagoResguardo;

	@Column(name="forma_pago_seguro")
	private String formaPagoSeguro;

	@Column(name="forma_pago_solca")
	private String formaPagoSolca;

	@Column(name="forma_pago_tasacion")
	private String formaPagoTasacion;

	@Column(name="forma_pago_transporte")
	private String formaPagoTransporte;

	@Column(name="forma_pago_valoracion")
	private String formaPagoValoracion;

	private String codigo;

	@Column(name = "joyas_seleccionadas")
	private String joyasSeleccionadas;

	@Column(name = "monto_preaprobado")
	private BigDecimal montoPreaprobado;
	
	@Column(name = "monto_solicitado")
	private BigDecimal montoSolicitado;
	
	@Column(name = "monto_diferido")
	private BigDecimal montoDiferido;
	


	@Column(name="monto_desembolso_ballon")
	private BigDecimal montoDesembolsoBallon;
	
	@Column(name="neto_al_cliente")
	private BigDecimal netoAlCliente;

	@Column(name = "plazo_credito")
	private BigDecimal plazoCredito;
	
	@Column(name="riesgo_total_cliente")
	private BigDecimal riesgoTotalCliente;
	


	@Column(name = "solca")
	private BigDecimal solca;
	

	public BigDecimal getMontoDiferido() {
		return montoDiferido;
	}

	public void setMontoDiferido(BigDecimal montoDiferido) {
		this.montoDiferido = montoDiferido;
	}

	public BigDecimal getMontoSolicitado() {
		return montoSolicitado;
	}

	public void setMontoSolicitado(BigDecimal montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}

	@Column(name = "recibir_cliente")
	private BigDecimal recibirCliente;

	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoCreditoNegociacionEnum tipo;

	@Column(name = "valor_cuota")
	private BigDecimal valorCuota;

	@Column(name = "id_agencia")
	private int idAgencia;

	// bi-directional many-to-one association to TbQoNegociacion
	@ManyToOne
	@JoinColumn(name = "id_negociacion")
	private TbQoNegociacion tbQoNegociacion;

	// bi-directional many-to-one association to TbQoTasacion
	@OneToMany(mappedBy = "tbQoCreditoNegociacion")
	private List<TbQoTasacion> tbQoTasacions;

	public TbQoCreditoNegociacion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getNetoAlCliente() {
		return netoAlCliente;
	}

	public void setNetoAlCliente(BigDecimal netoAlCliente) {
		this.netoAlCliente = netoAlCliente;
	}

	public BigDecimal getRiesgoTotalCliente() {
		return riesgoTotalCliente;
	}

	public void setRiesgoTotalCliente(BigDecimal riesgoTotalCliente) {
		this.riesgoTotalCliente = riesgoTotalCliente;
	}
	

	public BigDecimal getCostoValoracion() {
		return costoValoracion;
	}

	public void setCostoValoracion(BigDecimal costoValoracion) {
		this.costoValoracion = costoValoracion;
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
	
	

	public String getCostoTasacion() {
		return costoTasacion;
	}

	public void setCostoTasacion(String costoTasacion) {
		this.costoTasacion = costoTasacion;
	}

	public BigDecimal getCostoTransporte() {
		return this.costoTransporte;
	}

	public void setCostoTransporte(BigDecimal costoTransporte) {
		this.costoTransporte = costoTransporte;
	}

	public EstadoEnum getEstado() {
		return this.estado;
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

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getFormaPagoCustodia() {
		return formaPagoCustodia;
	}

	public void setFormaPagoCustodia(String formaPagoCustodia) {
		this.formaPagoCustodia = formaPagoCustodia;
	}

	public String getFormaPagoResguardo() {
		return formaPagoResguardo;
	}

	public void setFormaPagoResguardo(String formaPagoResguardo) {
		this.formaPagoResguardo = formaPagoResguardo;
	}

	public String getFormaPagoSeguro() {
		return formaPagoSeguro;
	}

	public void setFormaPagoSeguro(String formaPagoSeguro) {
		this.formaPagoSeguro = formaPagoSeguro;
	}

	public String getFormaPagoSolca() {
		return formaPagoSolca;
	}

	public void setFormaPagoSolca(String formaPagoSolca) {
		this.formaPagoSolca = formaPagoSolca;
	}

	public String getFormaPagoTasacion() {
		return formaPagoTasacion;
	}

	public void setFormaPagoTasacion(String formaPagoTasacion) {
		this.formaPagoTasacion = formaPagoTasacion;
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

	public int getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
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
	public BigDecimal getMontoDesembolsoBallon() {
		return montoDesembolsoBallon;
	}

	public void setMontoDesembolsoBallon(BigDecimal montoDesembolsoBallon) {
		this.montoDesembolsoBallon = montoDesembolsoBallon;
	}

	public BigDecimal getPlazoCredito() {
		return this.plazoCredito;
	}

	public void setPlazoCredito(BigDecimal plazoCredito) {
		this.plazoCredito = plazoCredito;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getRecibirCliente() {
		return this.recibirCliente;
	}

	public void setRecibirCliente(BigDecimal recibirCliente) {
		this.recibirCliente = recibirCliente;
	}
	

	public BigDecimal getSolca() {
		return solca;
	}

	public void setSolca(BigDecimal solca) {
		this.solca = solca;
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

	public int getTbQoAgencia() {
		return this.idAgencia;
	}

	public void setTbQoAgencia(int tbQoAgencia) {
		this.idAgencia = tbQoAgencia;
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
	
	

}