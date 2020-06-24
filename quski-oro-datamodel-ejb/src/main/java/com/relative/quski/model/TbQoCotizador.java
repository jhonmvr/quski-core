package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_qo_cotizador database table.
 * 
 */
@Entity
@Table(name="tb_qo_cotizador")
public class TbQoCotizador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_COTIZADOR_ID_GENERATOR", sequenceName="SEQ_COTIZADOR",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_COTIZADOR_ID_GENERATOR")
	private Long id;

	@Column(name="aprobacion_mupi")
	private String aprobacionMupi;

	@Column(name="codigo_cotizacion")
	private String codigoCotizacion;
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

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
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;

	//bi-directional many-to-one association to TbQoDetalleCredito
	@OneToMany(mappedBy="tbQoCotizador")
	private List<TbQoDetalleCredito> tbQoDetalleCreditos;

	//bi-directional many-to-one association to TbQoDocumentoHabilitante
	@OneToMany(mappedBy="tbQoCotizador")
	private List<TbQoDocumentoHabilitante> tbQoDocumentoHabilitantes;

	//bi-directional many-to-one association to TbQoPrecioOro
	@OneToMany(mappedBy="tbQoCotizador")
	private List<TbQoPrecioOro> tbQoPrecioOros;

	//bi-directional many-to-one association to TbQoVariablesCrediticia
	@OneToMany(mappedBy="tbQoCotizador")
	private List<TbQoVariablesCrediticia> tbQoVariablesCrediticias;

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

	public String getCodigoCotizacion() {
		return this.codigoCotizacion;
	}

	public void setCodigoCotizacion(String codigoCotizacion) {
		this.codigoCotizacion = codigoCotizacion;
	}

 

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
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

	public List<TbQoDocumentoHabilitante> getTbQoDocumentoHabilitantes() {
		return this.tbQoDocumentoHabilitantes;
	}

	public void setTbQoDocumentoHabilitantes(List<TbQoDocumentoHabilitante> tbQoDocumentoHabilitantes) {
		this.tbQoDocumentoHabilitantes = tbQoDocumentoHabilitantes;
	}

	public TbQoDocumentoHabilitante addTbQoDocumentoHabilitante(TbQoDocumentoHabilitante tbQoDocumentoHabilitante) {
		getTbQoDocumentoHabilitantes().add(tbQoDocumentoHabilitante);
		tbQoDocumentoHabilitante.setTbQoCotizador(this);

		return tbQoDocumentoHabilitante;
	}

	public TbQoDocumentoHabilitante removeTbQoDocumentoHabilitante(TbQoDocumentoHabilitante tbQoDocumentoHabilitante) {
		getTbQoDocumentoHabilitantes().remove(tbQoDocumentoHabilitante);
		tbQoDocumentoHabilitante.setTbQoCotizador(null);

		return tbQoDocumentoHabilitante;
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

	public List<TbQoVariablesCrediticia> getTbQoVariablesCrediticias() {
		return this.tbQoVariablesCrediticias;
	}

	public void setTbQoVariablesCrediticias(List<TbQoVariablesCrediticia> tbQoVariablesCrediticias) {
		this.tbQoVariablesCrediticias = tbQoVariablesCrediticias;
	}

	public TbQoVariablesCrediticia addTbQoVariablesCrediticia(TbQoVariablesCrediticia tbQoVariablesCrediticia) {
		getTbQoVariablesCrediticias().add(tbQoVariablesCrediticia);
		tbQoVariablesCrediticia.setTbQoCotizador(this);

		return tbQoVariablesCrediticia;
	}

	public TbQoVariablesCrediticia removeTbQoVariablesCrediticia(TbQoVariablesCrediticia tbQoVariablesCrediticia) {
		getTbQoVariablesCrediticias().remove(tbQoVariablesCrediticia);
		tbQoVariablesCrediticia.setTbQoCotizador(null);

		return tbQoVariablesCrediticia;
	}

}