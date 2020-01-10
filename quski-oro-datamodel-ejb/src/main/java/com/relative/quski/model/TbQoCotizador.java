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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.relative.quski.enums.EstadoEnum;


@Entity
@Table(name="tb_qo_cotizador")
public class TbQoCotizador implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_COTIZADOR_ID_GENERATOR", sequenceName="SEQ_COTIZADOR",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_COTIZADOR_ID_GENERATOR")
	private Long id;
	@Column(name="grado_interes")
	private String gradoInteres;
	@Column(name="motivo_de_desestimiento")
	private String motivoDesestimiento;
	@Column(name="aprobacion_mupi")
	private String aprobacionMupi;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	@Column(name="estado")
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	
//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;

//bi-directional many-to-one association to TbMiMovimientoCaja
	@OneToMany(mappedBy="tbQoCotizador")
	private List<TbQoPrecioOro> tbQoPrecioOro;

//bi-directional many-to-one association to TbMiMovimientoCaja
	@OneToMany(mappedBy="tbQoCotizador")
	private List<TbQoVariableCrediticia> tbQoVariableCrediticia;

		
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGradoInteres() {
		return gradoInteres;
	}
	public void setGradoInteres(String gradoInteres) {
		this.gradoInteres = gradoInteres;
	}
	public String getMotivoDesestimiento() {
		return motivoDesestimiento;
	}
	public void setMotivoDesestimiento(String motivoDesestimiento) {
		this.motivoDesestimiento = motivoDesestimiento;
	}
	public String getAprobacionMupi() {
		return aprobacionMupi;
	}
	public void setAprobacionMupi(String aprobacionMupi) {
		this.aprobacionMupi = aprobacionMupi;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public EstadoEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}
	public TbQoCliente getTbQoCliente() {
		return tbQoCliente;
	}
	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}
	public List<TbQoPrecioOro> getTbQoPrecioOro() {
		return tbQoPrecioOro;
	}
	public void setTbQoPrecioOro(List<TbQoPrecioOro> tbQoPrecioOro) {
		this.tbQoPrecioOro = tbQoPrecioOro;
	}
	public List<TbQoVariableCrediticia> getTbQoVariableCrediticia() {
		return tbQoVariableCrediticia;
	}
	public void setTbQoVariableCrediticia(List<TbQoVariableCrediticia> tbQoVariableCrediticia) {
		this.tbQoVariableCrediticia = tbQoVariableCrediticia;
	}


}
