package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;


/**
 * The persistent class for the tb_qo_direccion_cliente database table.
 * 
 */
@Entity
@Table(name="tb_qo_direccion_cliente")
public class TbQoDireccionCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_DIRECCION_CLIENTE_ID_GENERATOR", sequenceName="SEQ_DIRECCION_CLIENTE",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_DIRECCION_CLIENTE_ID_GENERATOR")
	private Long id;

	private String barrio;

	@Column(name="calle_principal")
	private String callePrincipal;

	@Column(name="calle_segundaria")
	private String calleSegundaria;
	
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Column(name="direccion_envio_correspondencia")
	private Boolean direccionEnvioCorrespondencia;

	@Column(name="direccion_legal")
	private Boolean direccionLegal;

	private String numeracion;

	@Column(name="referencia_ubicacion")
	private String referenciaUbicacion;
	
	@Column(name="division_politica")
	private Long divisionPolitica;

	private String sector;

	@Column(name="tipo_direccion")
	private String tipoDireccion;

	@Column(name="tipo_vivienda")
	private String tipoVivienda;

	//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;
	
	@Column(name="id_softbank")
	private Long idSoftbank;

	public TbQoDireccionCliente() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBarrio() {
		return this.barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public Long getDivisionPolitica() {
		return divisionPolitica;
	}

	public void setDivisionPolitica(Long divisionPolitica) {
		this.divisionPolitica = divisionPolitica;
	}

	public String getCallePrincipal() {
		return this.callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getCalleSegundaria() {
		return this.calleSegundaria;
	}

	public void setCalleSegundaria(String calleSegundaria) {
		this.calleSegundaria = calleSegundaria;
	}

	public Boolean getDireccionEnvioCorrespondencia() {
		return this.direccionEnvioCorrespondencia;
	}

	public void setDireccionEnvioCorrespondencia(Boolean direccionEnvioCorrespondencia) {
		this.direccionEnvioCorrespondencia = direccionEnvioCorrespondencia;
	}

	public Boolean getDireccionLegal() {
		return this.direccionLegal;
	}

	public void setDireccionLegal(Boolean direccionLegal) {
		this.direccionLegal = direccionLegal;
	}

	public String getNumeracion() {
		return this.numeracion;
	}

	public void setNumeracion(String numeracion) {
		this.numeracion = numeracion;
	}

	public String getReferenciaUbicacion() {
		return this.referenciaUbicacion;
	}

	public void setReferenciaUbicacion(String referenciaUbicacion) {
		this.referenciaUbicacion = referenciaUbicacion;
	}

	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getTipoDireccion() {
		return this.tipoDireccion;
	}

	public void setTipoDireccion(String tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}

	public String getTipoVivienda() {
		return this.tipoVivienda;
	}

	public Long getIdSoftbank() {
		return idSoftbank;
	}

	public void setIdSoftbank(Long idSoftbank) {
		this.idSoftbank = idSoftbank;
	}

	public void setTipoVivienda(String tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}

	public TbQoCliente getTbQoCliente() {
		return this.tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

 

}