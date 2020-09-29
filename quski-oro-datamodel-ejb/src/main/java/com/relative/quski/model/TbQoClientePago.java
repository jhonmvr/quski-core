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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.ProcesoEnum;


/**
 * The persistent class for the tb_qo_cliente_pago database table.
 * 
 */
@Entity
@Table(name="tb_qo_cliente_pago")
public class TbQoClientePago implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5670664066700465760L;

	@Id
	@SequenceGenerator(name="TB_QO_CLIENTE_PAGO_ID_GENERATOR", sequenceName="SEQ_CLIENTE_PAGO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_CLIENTE_PAGO_ID_GENERATOR")
	private Long id;

	private String cedula;

	@Column(name="codigo_cuenta_mupi")
	private String codigoCuentaMupi;

	@Column(name="codigo_operacion")
	private String codigoOperacion;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	
	@Enumerated(EnumType.STRING)
	private ProcesoEnum tipo;

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

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCodigoCuentaMupi() {
		return this.codigoCuentaMupi;
	}

	public void setCodigoCuentaMupi(String codigoCuentaMupi) {
		this.codigoCuentaMupi = codigoCuentaMupi;
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

	public ProcesoEnum getTipo() {
		return this.tipo;
	}
	
	public void setTipo(ProcesoEnum tipo) {
		this.tipo = tipo;
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