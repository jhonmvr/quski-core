package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_qo_tipo_archivo database table.
 * 
 */
@Entity
@Table(name="tb_qo_tipo_archivo")
public class TbQoTipoArchivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_TIPO_ARCHIVO_ID_GENERATOR", sequenceName="SEQ_TIPO_ARCHIVO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_TIPO_ARCHIVO_ID_GENERATOR")
	private Long id;

	private String descripcion;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	private String plantilla;

	@Column(name="plantilla_dos")
	private String plantillaDos;

	@Column(name="plantilla_tres")
	private String plantillaTres;

	@Column(name="plantilla_uno")
	private String plantillaUno;

	@Column(name="tipo_archivo")
	private String tipoArchivo;

	@Column(name="tipo_plantilla")
	private String tipoPlantilla;

	//bi-directional many-to-one association to TbQoArchivoCliente
	@OneToMany(mappedBy="tbQoTipoArchivo")
	private List<TbQoArchivoCliente> tbQoArchivoClientes;

	public TbQoTipoArchivo() {
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

	public String getPlantilla() {
		return this.plantilla;
	}

	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}

	public String getPlantillaDos() {
		return this.plantillaDos;
	}

	public void setPlantillaDos(String plantillaDos) {
		this.plantillaDos = plantillaDos;
	}

	public String getPlantillaTres() {
		return this.plantillaTres;
	}

	public void setPlantillaTres(String plantillaTres) {
		this.plantillaTres = plantillaTres;
	}

	public String getPlantillaUno() {
		return this.plantillaUno;
	}

	public void setPlantillaUno(String plantillaUno) {
		this.plantillaUno = plantillaUno;
	}

	public String getTipoArchivo() {
		return this.tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public String getTipoPlantilla() {
		return this.tipoPlantilla;
	}

	public void setTipoPlantilla(String tipoPlantilla) {
		this.tipoPlantilla = tipoPlantilla;
	}

	public List<TbQoArchivoCliente> getTbQoArchivoClientes() {
		return this.tbQoArchivoClientes;
	}

	public void setTbQoArchivoClientes(List<TbQoArchivoCliente> tbQoArchivoClientes) {
		this.tbQoArchivoClientes = tbQoArchivoClientes;
	}

	public TbQoArchivoCliente addTbQoArchivoCliente(TbQoArchivoCliente tbQoArchivoCliente) {
		getTbQoArchivoClientes().add(tbQoArchivoCliente);
		tbQoArchivoCliente.setTbQoTipoArchivo(this);

		return tbQoArchivoCliente;
	}

	public TbQoArchivoCliente removeTbQoArchivoCliente(TbQoArchivoCliente tbQoArchivoCliente) {
		getTbQoArchivoClientes().remove(tbQoArchivoCliente);
		tbQoArchivoCliente.setTbQoTipoArchivo(null);

		return tbQoArchivoCliente;
	}

}