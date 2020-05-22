package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

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
	@SequenceGenerator(name="TB_MI_PARAMETRO_ID_GENERATOR", sequenceName="SEQ_PARAMETRO",initialValue = 1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MI_PARAMETRO_ID_GENERATOR")
	private Long id;

	private Object archivo;

	@Column(name="caracteristica_dos")
	private String caracteristicaDos;

	@Column(name="caracteritica_uno")
	private String caracteriticaUno;
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private String nombre;

	private Long orden;

	private String tipo;

	private String valor;

	public TbMiParametro() {
	}

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Object getArchivo() {
		return this.archivo;
	}

	public void setArchivo(Object archivo) {
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

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

}