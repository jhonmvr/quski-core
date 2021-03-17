package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the tb_qo_proceso database table.
 * 
 */
@Entity
@Table(name="tb_qo_proceso")
public class TbQoProceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_PROCESO_ID_GENERATOR", sequenceName="SEQ_PROCESO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_PROCESO_ID_GENERATOR")
	private Long id;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="estado_proceso")
	private EstadoProcesoEnum estadoProceso;


	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="id_proceso")
	private BigDecimal idProceso;

	@Column(name="id_referencia")
	private Long idReferencia;

	@Enumerated(EnumType.STRING)
	private ProcesoEnum proceso;
	
	@Column(name="hora_aprobador")
	private Timestamp horaAprobador;

	private String usuario;

	public TbQoProceso() {
	}
	public TbQoProceso( Long idReferencia) {
		this.idReferencia = idReferencia;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public EstadoProcesoEnum getEstadoProceso() {
		return this.estadoProceso;
	}

	public void setEstadoProceso(EstadoProcesoEnum estadoProceso) {
		this.estadoProceso = estadoProceso;
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

	public BigDecimal getIdProceso() {
		return this.idProceso;
	}

	public void setIdProceso(BigDecimal idProceso) {
		this.idProceso = idProceso;
	}
	public Long getIdReferencia() {
		return idReferencia;
	}
	public void setIdReferencia(Long idReferencia) {
		this.idReferencia = idReferencia;
	}
	public ProcesoEnum getProceso() {
		return this.proceso;
	}

	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Timestamp getHoraAprobador() {
		return horaAprobador;
	}
	public void setHoraAprobador(Timestamp horaAprobador) {
		this.horaAprobador = horaAprobador;
	}

}