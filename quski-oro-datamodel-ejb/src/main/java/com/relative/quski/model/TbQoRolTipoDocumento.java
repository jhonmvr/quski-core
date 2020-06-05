package com.relative.quski.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcessEnum;


/**
 * The persistent class for the tb_qo_tipo_joya database table.
 * 
 */
@Entity
@Table(name="tb_qo_rol_tipo_documento")
public class TbQoRolTipoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TbQoRolTipoDocumento", sequenceName="seq_rol_tipo_documento")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TbQoRolTipoDocumento")
	private Long id;

	@Column(name = "id_tipo_documento")
	private Long idTipoDocumento;

	@Column(name = "id_rol")
	private String idRol;
	
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	
	@Enumerated(EnumType.STRING)
	private ProcessEnum proceso;
	
	@Column(name="estado_operacion")
	@Enumerated(EnumType.STRING)
	private EstadoOperacionEnum estadoOperacion;

	

	public TbQoRolTipoDocumento() {
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}



	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}



	public String getIdRol() {
		return idRol;
	}



	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}



	public EstadoEnum getEstado() {
		return estado;
	}



	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}



	public ProcessEnum getProceso() {
		return proceso;
	}



	public void setProceso(ProcessEnum proceso) {
		this.proceso = proceso;
	}



	public EstadoOperacionEnum getEstadoOperacion() {
		return estadoOperacion;
	}



	public void setEstadoOperacion(EstadoOperacionEnum estadoOperacion) {
		this.estadoOperacion = estadoOperacion;
	}

	

}