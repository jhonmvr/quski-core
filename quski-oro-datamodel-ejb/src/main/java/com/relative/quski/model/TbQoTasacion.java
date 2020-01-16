package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tq_qo_tasacion database table.
 * 
 */
@Entity
@Table(name="tb_qo_tasacion")
//@NamedQuery(name="TqQoTasacion.findAll", query="SELECT t FROM TqQoTasacion t")
public class TbQoTasacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_TASACION_ID_GENERATOR", sequenceName="SEQ_TASACION", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_TASACION_ID_GENERATOR")
	private Long id;

	private String descripcion;

	@Column(name="descuento_peso_piedra")
	private BigDecimal descuentoPesoPiedra;

	@Column(name="descuento_suelda")
	private BigDecimal descuentoSuelda;

	@Column(name="estado_joya")
	private String estadoJoya;
	
	@Column(name="tipo_joya")
	private String tipoJoya;

	@Column(name="numero_piezas")
	private Integer numeroPiezas;
	@Column(name="peso_bruto")
	private BigDecimal pesoBruto;
	@Column(name="peso_neto")
	private BigDecimal pesoNeto;
	@Column(name="valor_avaluo")
	private BigDecimal valorAvaluo;
	@Column(name="valor_comercial")
	private BigDecimal valorComercial;
	@Column(name="valor_oro")
	private BigDecimal valorOro;
	@Column(name="valor_realizacion")
	private BigDecimal valorRealizacion;
	//bi-directional many-to-one association to TbQoTipoOro
	@ManyToOne
	@JoinColumn(name="id_tipo_oro")
	private TbQoTipoOro tbQoTipoOro;
	/*//bi-directional many-to-one association to TbTipoJoya
	@ManyToOne
	@JoinColumn(name="tipo_joya")
	private TbTipoJoya tbTipoJoya;
*/
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	

	//bi-directional many-to-one association to TqQoNegociacion
	@ManyToOne
	@JoinColumn(name="id_credito_negociacion")
	private TbQoCreditoNegociacion tbQoCreditoNegociacion;

	public TbQoTasacion() {
	}	
	
	public String getTipoJoya() {
		return tipoJoya;
	}

	public void setTipoJoya(String tipoJoya) {
		this.tipoJoya = tipoJoya;
	}



	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getDescuentoPesoPiedra() {
		return this.descuentoPesoPiedra;
	}

	public void setDescuentoPesoPiedra(BigDecimal descuentoPesoPiedra) {
		this.descuentoPesoPiedra = descuentoPesoPiedra;
	}

	public BigDecimal getDescuentoSuelda() {
		return this.descuentoSuelda;
	}

	public void setDescuentoSuelda(BigDecimal descuentoSuelda) {
		this.descuentoSuelda = descuentoSuelda;
	}

	


	public String getEstadoJoya() {
		return this.estadoJoya;
	}

	public void setEstadoJoya(String estadoJoya) {
		this.estadoJoya = estadoJoya;
	}

	public Integer getNumeroPiezas() {
		return this.numeroPiezas;
	}

	public void setNumeroPiezas(Integer numeroPiezas) {
		this.numeroPiezas = numeroPiezas;
	}

	public BigDecimal getPesoBruto() {
		return this.pesoBruto;
	}

	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public BigDecimal getPesoNeto() {
		return this.pesoNeto;
	}

	public void setPesoNeto(BigDecimal pesoNeto) {
		this.pesoNeto = pesoNeto;
	}

	public BigDecimal getValorAvaluo() {
		return this.valorAvaluo;
	}

	public void setValorAvaluo(BigDecimal valorAvaluo) {
		this.valorAvaluo = valorAvaluo;
	}

	public BigDecimal getValorComercial() {
		return this.valorComercial;
	}

	public void setValorComercial(BigDecimal valorComercial) {
		this.valorComercial = valorComercial;
	}

	public BigDecimal getValorOro() {
		return this.valorOro;
	}

	public void setValorOro(BigDecimal valorOro) {
		this.valorOro = valorOro;
	}

	public BigDecimal getValorRealizacion() {
		return this.valorRealizacion;
	}

	public void setValorRealizacion(BigDecimal valorRealizacion) {
		this.valorRealizacion = valorRealizacion;
	}



	public TbQoTipoOro getTbQoTipoOro() {
		return tbQoTipoOro;
	}

	public void setTbQoTipoOro(TbQoTipoOro tbQoTipoOro) {
		this.tbQoTipoOro = tbQoTipoOro;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public TbQoCreditoNegociacion getTbQoCreditoNegociacion() {
		return tbQoCreditoNegociacion;
	}

	public void setTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		this.tbQoCreditoNegociacion = tbQoCreditoNegociacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	

}