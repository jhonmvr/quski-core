package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tb_mi_parametro database table.
 * 
 */
@Entity
@Table(name="tb_mi_parametro")
public class TbMiParametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MI_PARAMETRO_ID_GENERATOR", sequenceName="SEG_TB_MI_PARAMETRO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_PARAMETRO_ID_GENERATOR")
	private Long id;

	private byte[] archivo;

	@Column(name="caracteristica_dos")
	private String caracteristicaDos;

	@Column(name="caracteritica_uno")
	private String caracteriticaUno;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private String nombre;

	private BigDecimal orden;

	private String tipo;

	private String valor;

	public TbMiParametro() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getArchivo() {
		return this.archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getCaracteristicaDos() {
		return this.caracteristicaDos;
	}

	public void setCaracteristicaDos(String caracteristicaDos) {
		this.caracteristicaDos = caracteristicaDos;
	}

	public String getCaracteriticaUno() {
		return this.caracteriticaUno;
	}

	public void setCaracteriticaUno(String caracteriticaUno) {
		this.caracteriticaUno = caracteriticaUno;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getOrden() {
		return this.orden;
	}

	public void setOrden(BigDecimal orden) {
		this.orden = orden;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}