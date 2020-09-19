package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_qo_cliente_pago database table.
 * 
 */
@Entity
@Table(name="tb_qo_cliente_pago")
@NamedQuery(name="TbQoClientePago.findAll", query="SELECT t FROM TbQoClientePago t")
public class TbQoClientePago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_CLIENTE_PAGO_IDPAGO_GENERATOR", sequenceName="SEQ_CLIENTE_PAGO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_CLIENTE_PAGO_IDPAGO_GENERATOR")
	@Column(name="id_pago")
	private Long idPago;

	private String cedula;

	@Column(name="codigo_operacion")
	private String codigoOperacion;

	@Column(name="codigo_operacion_mupi")
	private String codigoOperacionMupi;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="nombre_cliente")
	private String nombreCliente;

	private String observacion;

	@Column(name="tipo_credito")
	private String tipoCredito;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="valor_depositado")
	private BigDecimal valorDepositado;

	@Column(name="valor_precancelado")
	private BigDecimal valorPrecancelado;

	//bi-directional many-to-one association to TbQoRegistrarPago
	@OneToMany(mappedBy="tbQoClientePago")
	private List<TbQoRegistrarPago> tbQoRegistrarPagos;

	public TbQoClientePago() {
	}

	public Long getIdPago() {
		return this.idPago;
	}

	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCodigoOperacion() {
		return this.codigoOperacion;
	}

	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public String getCodigoOperacionMupi() {
		return this.codigoOperacionMupi;
	}

	public void setCodigoOperacionMupi(String codigoOperacionMupi) {
		this.codigoOperacionMupi = codigoOperacionMupi;
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

	public String getNombreCliente() {
		return this.nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTipoCredito() {
		return this.tipoCredito;
	}

	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	public String getUsuarioActualizacion() {
		return this.usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public BigDecimal getValorDepositado() {
		return this.valorDepositado;
	}

	public void setValorDepositado(BigDecimal valorDepositado) {
		this.valorDepositado = valorDepositado;
	}

	public BigDecimal getValorPrecancelado() {
		return this.valorPrecancelado;
	}

	public void setValorPrecancelado(BigDecimal valorPrecancelado) {
		this.valorPrecancelado = valorPrecancelado;
	}

	public List<TbQoRegistrarPago> getTbQoRegistrarPagos() {
		return this.tbQoRegistrarPagos;
	}

	public void setTbQoRegistrarPagos(List<TbQoRegistrarPago> tbQoRegistrarPagos) {
		this.tbQoRegistrarPagos = tbQoRegistrarPagos;
	}

	public TbQoRegistrarPago addTbQoRegistrarPago(TbQoRegistrarPago tbQoRegistrarPago) {
		getTbQoRegistrarPagos().add(tbQoRegistrarPago);
		tbQoRegistrarPago.setTbQoClientePago(this);

		return tbQoRegistrarPago;
	}

	public TbQoRegistrarPago removeTbQoRegistrarPago(TbQoRegistrarPago tbQoRegistrarPago) {
		getTbQoRegistrarPagos().remove(tbQoRegistrarPago);
		tbQoRegistrarPago.setTbQoClientePago(null);

		return tbQoRegistrarPago;
	}

}