/**
 * 
 */
package com.relative.quski.wrapper;

import java.io.Serializable;
import com.relative.quski.model.*;

/**
 * @autho
 *
 */
public class ExcepcionServiciosFlujoVariableWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private OpcionAndGarantiasWrapper opcionAndGarantiasWrapper;
	private String numeroOperacion;
	private String idNegociacion;
	private String asesor;
	private String idAgencia;
	private String numeroOperacionMadre;
	private String autorizacion;
	private String nombreAgencia;
	private String telefonoAsesor;
	private String nombreAsesor;
	private String correoAsesor;
	private TbQoExcepcionOperativa ex;
	
	
	public ExcepcionServiciosFlujoVariableWrapper() {
		super();
	}

	public ExcepcionServiciosFlujoVariableWrapper(OpcionAndGarantiasWrapper opcionAndGarantiasWrapper,
			String numeroOperacion, String idNegociacion, String asesor, String idAgencia, String numeroOperacionMadre,
			String autorizacion, String nombreAgencia, String telefonoAsesor, String nombreAsesor, String correoAsesor,
			TbQoExcepcionOperativa ex) {
		super();
		this.opcionAndGarantiasWrapper = opcionAndGarantiasWrapper;
		this.numeroOperacion = numeroOperacion;
		this.idNegociacion = idNegociacion;
		this.asesor = asesor;
		this.idAgencia = idAgencia;
		this.numeroOperacionMadre = numeroOperacionMadre;
		this.autorizacion = autorizacion;
		this.nombreAgencia = nombreAgencia;
		this.telefonoAsesor = telefonoAsesor;
		this.nombreAsesor = nombreAsesor;
		this.correoAsesor = correoAsesor;
		this.ex = ex;
	}

	public OpcionAndGarantiasWrapper getOpcionAndGarantiasWrapper() {
		return opcionAndGarantiasWrapper;
	}

	public void setOpcionAndGarantiasWrapper(OpcionAndGarantiasWrapper opcionAndGarantiasWrapper) {
		this.opcionAndGarantiasWrapper = opcionAndGarantiasWrapper;
	}

	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	public String getIdNegociacion() {
		return idNegociacion;
	}

	public void setIdNegociacion(String idNegociacion) {
		this.idNegociacion = idNegociacion;
	}

	public String getAsesor() {
		return asesor;
	}

	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}

	public String getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(String idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getNumeroOperacionMadre() {
		return numeroOperacionMadre;
	}

	public void setNumeroOperacionMadre(String numeroOperacionMadre) {
		this.numeroOperacionMadre = numeroOperacionMadre;
	}

	public String getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public String getNombreAgencia() {
		return nombreAgencia;
	}

	public void setNombreAgencia(String nombreAgencia) {
		this.nombreAgencia = nombreAgencia;
	}

	public String getTelefonoAsesor() {
		return telefonoAsesor;
	}

	public void setTelefonoAsesor(String telefonoAsesor) {
		this.telefonoAsesor = telefonoAsesor;
	}

	public String getNombreAsesor() {
		return nombreAsesor;
	}

	public void setNombreAsesor(String nombreAsesor) {
		this.nombreAsesor = nombreAsesor;
	}

	public String getCorreoAsesor() {
		return correoAsesor;
	}

	public void setCorreoAsesor(String correoAsesor) {
		this.correoAsesor = correoAsesor;
	}

	public TbQoExcepcionOperativa getEx() {
		return ex;
	}

	public void setEx(TbQoExcepcionOperativa ex) {
		this.ex = ex;
	}
	
	
	
	

}
