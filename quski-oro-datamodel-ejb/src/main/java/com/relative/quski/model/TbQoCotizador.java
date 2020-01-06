package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.model.TbQoDetalleCredito;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.model.TbQoVariableCrediticia;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_qo_cotizador database table.
 * 
 */
@Entity
@Table(name="tb_qo_cotizador")
@NamedQuery(name="TbQoCotizador.findAll", query="SELECT t FROM TbQoCotizador t")
public class TbQoCotizador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_COTIZADOR_ID_GENERATOR", sequenceName="SEQ_COTIZADOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_COTIZADOR_ID_GENERATOR")
	private Long id;

	@Column(name="aprobacion_mupi")
	private String aprobacionMupi;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="grado_interes")
	private String gradoInteres;

	@Column(name="motivo_de_desestimiento")
	private String motivoDeDesestimiento;

	//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="tb_qo_cliente_id")
	private TbQoCliente tbQoCliente;

	//bi-directional many-to-one association to TbQoDetalleCredito
	@OneToMany(mappedBy="tbQoCotizador")
	private List<TbQoDetalleCredito> tbQoDetalleCreditos;

	//bi-directional many-to-one association to TbQoPrecioOro
	@OneToMany(mappedBy="tbQoCotizador")
	private List<TbQoPrecioOro> tbQoPrecioOros;

	//bi-directional many-to-one association to TbQoVariablesCrediticia
	@OneToMany(mappedBy="tbQoCotizador")
	private List<TbQoVariableCrediticia> tbQoVariablesCrediticias;

	public TbQoCotizador() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAprobacionMupi() {
		return this.aprobacionMupi;
	}

	public void setAprobacionMupi(String aprobacionMupi) {
		this.aprobacionMupi = aprobacionMupi;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
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

	public String getGradoInteres() {
		return this.gradoInteres;
	}

	public void setGradoInteres(String gradoInteres) {
		this.gradoInteres = gradoInteres;
	}

	public String getMotivoDeDesestimiento() {
		return this.motivoDeDesestimiento;
	}

	public void setMotivoDeDesestimiento(String motivoDeDesestimiento) {
		this.motivoDeDesestimiento = motivoDeDesestimiento;
	}

	public TbQoCliente getTbQoCliente() {
		return this.tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

	public List<TbQoDetalleCredito> getTbQoDetalleCreditos() {
		return this.tbQoDetalleCreditos;
	}

	public void setTbQoDetalleCreditos(List<TbQoDetalleCredito> tbQoDetalleCreditos) {
		this.tbQoDetalleCreditos = tbQoDetalleCreditos;
	}

	public TbQoDetalleCredito addTbQoDetalleCredito(TbQoDetalleCredito tbQoDetalleCredito) {
		getTbQoDetalleCreditos().add(tbQoDetalleCredito);
		tbQoDetalleCredito.setTbQoCotizador(this);

		return tbQoDetalleCredito;
	}

	public TbQoDetalleCredito removeTbQoDetalleCredito(TbQoDetalleCredito tbQoDetalleCredito) {
		getTbQoDetalleCreditos().remove(tbQoDetalleCredito);
		tbQoDetalleCredito.setTbQoCotizador(null);

		return tbQoDetalleCredito;
	}

	public List<TbQoPrecioOro> getTbQoPrecioOros() {
		return this.tbQoPrecioOros;
	}

	public void setTbQoPrecioOros(List<TbQoPrecioOro> tbQoPrecioOros) {
		this.tbQoPrecioOros = tbQoPrecioOros;
	}

	public TbQoPrecioOro addTbQoPrecioOro(TbQoPrecioOro tbQoPrecioOro) {
		getTbQoPrecioOros().add(tbQoPrecioOro);
		tbQoPrecioOro.setTbQoCotizador(this);

		return tbQoPrecioOro;
	}

	public TbQoPrecioOro removeTbQoPrecioOro(TbQoPrecioOro tbQoPrecioOro) {
		getTbQoPrecioOros().remove(tbQoPrecioOro);
		tbQoPrecioOro.setTbQoCotizador(null);

		return tbQoPrecioOro;
	}

	public List<TbQoVariableCrediticia> getTbQoVariablesCrediticias() {
		return this.tbQoVariablesCrediticias;
	}

	public void setTbQoVariablesCrediticias(List<TbQoVariableCrediticia> tbQoVariablesCrediticias) {
		this.tbQoVariablesCrediticias = tbQoVariablesCrediticias;
	}

	public TbQoVariableCrediticia addTbQoVariablesCrediticia(TbQoVariableCrediticia tbQoVariablesCrediticia) {
		getTbQoVariablesCrediticias().add(tbQoVariablesCrediticia);
		tbQoVariablesCrediticia.setTbQoCotizador(this);

		return tbQoVariablesCrediticia;
	}

	public TbQoVariableCrediticia removeTbQoVariablesCrediticia(TbQoVariableCrediticia tbQoVariablesCrediticia) {
		getTbQoVariablesCrediticias().remove(tbQoVariablesCrediticia);
		tbQoVariablesCrediticia.setTbQoCotizador(null);

		return tbQoVariablesCrediticia;
	}

}