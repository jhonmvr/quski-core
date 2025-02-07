package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_qo_cliente_pago database table.
 * 
 */
@Entity
@Table(name="tb_qo_cliente_pago")
public class TbQoClientePago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_CLIENTE_PAGO_ID_GENERATOR", sequenceName="SEQ_CLIENTE_PAGO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_CLIENTE_PAGO_ID_GENERATOR")
	private Long id;

	private String cedula;

	@Column(name="codigo_cuenta_mupi")
	private String codigoCuentaMupi;

	@Column(name="codigo_operacion")
	private String codigoOperacion;
	
	@Column(name="tipo_pago_proceso")
	private String tipoPagoProceso;
	
	@Column(name="mail_asesor")
	private String mailAsesor;
	
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;


	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="nombre_cliente")
	private String nombreCliente;
	

	@Column(name="observacion_aprobador")
	private String observacionAprobador;
	
	private String observacion;

	private String tipo;
	
	private String asesor;
	
	private String aprobador;
	
	private String codigo;
	
	@Column(name="id_agencia")
	private Long idAgencia;

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

	@Column(name="monto_credito")
	private BigDecimal montoCredito;

	@Column(name="plazo_credito")
	private String plazoCredito;

	@Column(name="numero_cuenta_cliente")
	private String numeroCuentaCliente;

	@Column(name="nombre_asesor")
	private String nombreAsesor;
	
	@Column(name="codigo_operacion_mupi")
	private String codigoOperacionMupi;
	
	public String getCodigoOperacionMupi() {
		return codigoOperacionMupi;
	}

	public void setCodigoOperacionMupi(String codigoOperacionMupi) {
		this.codigoOperacionMupi = codigoOperacionMupi;
	}

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

	public String getObservacionAprobador() {
		return observacionAprobador;
	}

	public void setObservacionAprobador(String observacionAprobador) {
		this.observacionAprobador = observacionAprobador;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getAsesor() {
		return asesor;
	}

	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}

	public String getAprobador() {
		return aprobador;
	}

	public void setAprobador(String aprobador) {
		this.aprobador = aprobador;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getTipoPagoProceso() {
		return tipoPagoProceso;
	}

	public void setTipoPagoProceso(String tipoPagoProceso) {
		this.tipoPagoProceso = tipoPagoProceso;
	}

	public String getMailAsesor() {
		return mailAsesor;
	}

	public void setMailAsesor(String mailAsesor) {
		this.mailAsesor = mailAsesor;
	}

	public BigDecimal getMontoCredito() {
		return montoCredito;
	}

	public void setMontoCredito(BigDecimal montoCredito) {
		this.montoCredito = montoCredito;
	}

	public String getPlazoCredito() {
		return plazoCredito;
	}

	public void setPlazoCredito(String plazoCredito) {
		this.plazoCredito = plazoCredito;
	}

	public String getNumeroCuentaCliente() {
		return numeroCuentaCliente;
	}

	public void setNumeroCuentaCliente(String numeroCuentaCliente) {
		this.numeroCuentaCliente = numeroCuentaCliente;
	}

	public String getNombreAsesor() {
		return nombreAsesor;
	}

	public void setNombreAsesor(String nombreAsesor) {
		this.nombreAsesor = nombreAsesor;
	}
	
	

}