package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoDetalleCredito;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.model.TbQoVariablesCrediticia;

public class CotizadorWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public CotizadorWrapper() {
		
	}
	private Long idCotizador;
	private Long idVariableCrediticia;
	private Long idPrecioOro;
	private String aprobacionMupi;
	private String codigoCotizacion;
	private EstadoEnum estado;
	private Date fechaActualizacion;
	private Date fechaCreacion;
	private String gradoInteres;
	private String motivoDeDesestimiento;
	private TbQoCliente tbQoCliente;
	private List<TbQoPrecioOro> tbQoPrecioOros;
	private List<TbQoVariablesCrediticia> tbQoVariablesCrediticias;
	private List<TbQoDetalleCredito> tbQoDetalleCreditos;
	private List<TbQoDocumentoHabilitante> tbQoDocumentoHabilitantes;
	
	
	public CotizadorWrapper(Long idVariableCrediticia, Long idPrecioOro) {
		super();
		this.idVariableCrediticia = idVariableCrediticia;
		this.idPrecioOro = idPrecioOro;
	}
 
	public Long getIdCotizador() {
		return idCotizador;
	}

	public void setIdCotizador(Long idCotizador) {
		this.idCotizador = idCotizador;
	}

	public Long getIdVariableCrediticia() {
		return idVariableCrediticia;
	}

	public void setIdVariableCrediticia(Long idVariableCrediticia) {
		this.idVariableCrediticia = idVariableCrediticia;
	}

	public Long getIdPrecioOro() {
		return idPrecioOro;
	}

	public void setIdPrecioOro(Long idPrecioOro) {
		this.idPrecioOro = idPrecioOro;
	}

	public String getAprobacionMupi() {
		return aprobacionMupi;
	}
	public void setAprobacionMupi(String aprobacionMupi) {
		this.aprobacionMupi = aprobacionMupi;
	}
	public String getCodigoCotizacion() {
		return codigoCotizacion;
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
	public String getGradoInteres() {
		return gradoInteres;
	}
	public void setGradoInteres(String gradoInteres) {
		this.gradoInteres = gradoInteres;
	}
	public String getMotivoDeDesestimiento() {
		return motivoDeDesestimiento;
	}
	public void setMotivoDeDesestimiento(String motivoDeDesestimiento) {
		this.motivoDeDesestimiento = motivoDeDesestimiento;
	}
	public TbQoCliente getTbQoCliente() {
		return tbQoCliente;
	}
	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}
	public List<TbQoPrecioOro> getTbQoPrecioOros() {
		return tbQoPrecioOros;
	}
	public void setTbQoPrecioOros(List<TbQoPrecioOro> tbQoPrecioOros) {
		this.tbQoPrecioOros = tbQoPrecioOros;
	}
	public List<TbQoVariablesCrediticia> getTbQoVariablesCrediticias() {
		return tbQoVariablesCrediticias;
	}
	public void setTbQoVariablesCrediticias(List<TbQoVariablesCrediticia> tbQoVariablesCrediticias) {
		this.tbQoVariablesCrediticias = tbQoVariablesCrediticias;
	}
	public List<TbQoDetalleCredito> getTbQoDetalleCreditos() {
		return tbQoDetalleCreditos;
	}
	public void setTbQoDetalleCreditos(List<TbQoDetalleCredito> tbQoDetalleCreditos) {
		this.tbQoDetalleCreditos = tbQoDetalleCreditos;
	}
	public List<TbQoDocumentoHabilitante> getTbQoDocumentoHabilitantes() {
		return tbQoDocumentoHabilitantes;
	}
	public void setTbQoDocumentoHabilitantes(List<TbQoDocumentoHabilitante> tbQoDocumentoHabilitantes) {
		this.tbQoDocumentoHabilitantes = tbQoDocumentoHabilitantes;
	}
	

	
}
