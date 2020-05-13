package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.TipoPlantillaEnum;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_qo_tipo_documento database table.
 * 
 */
@Entity
@Table(name="tb_qo_tipo_documento")
public class TbQoTipoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_TIPO_DOCUMENTO_ID_GENERATOR", sequenceName="SEG_TB_QO_TIPO_DOCUMENTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_TIPO_DOCUMENTO_ID_GENERATOR")
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

	@Column(name="tipo_documento")
	private String tipoDocumento;

	@Column(name="tipo_plantilla")
	private String tipoPlantilla;

	//bi-directional many-to-one association to TbQoDocumentoHabilitante
	@OneToMany(mappedBy="tbQoTipoDocumento")
	private List<TbQoDocumentoHabilitante> tbQoDocumentoHabilitantes;

	public TbQoTipoDocumento() {
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

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTipoPlantilla() {
		return tipoPlantilla;
	}

	public void setTipoPlantilla(String tipoPlantilla) {
		this.tipoPlantilla = tipoPlantilla;
	}

	public List<TbQoDocumentoHabilitante> getTbQoDocumentoHabilitantes() {
		return this.tbQoDocumentoHabilitantes;
	}

	public void setTbQoDocumentoHabilitantes(List<TbQoDocumentoHabilitante> tbQoDocumentoHabilitantes) {
		this.tbQoDocumentoHabilitantes = tbQoDocumentoHabilitantes;
	}

	public TbQoDocumentoHabilitante addTbQoDocumentoHabilitante(TbQoDocumentoHabilitante tbQoDocumentoHabilitante) {
		getTbQoDocumentoHabilitantes().add(tbQoDocumentoHabilitante);
		tbQoDocumentoHabilitante.setTbQoTipoDocumento(this);

		return tbQoDocumentoHabilitante;
	}

	public TbQoDocumentoHabilitante removeTbQoDocumentoHabilitante(TbQoDocumentoHabilitante tbQoDocumentoHabilitante) {
		getTbQoDocumentoHabilitantes().remove(tbQoDocumentoHabilitante);
		tbQoDocumentoHabilitante.setTbQoTipoDocumento(null);

		return tbQoDocumentoHabilitante;
	}

}