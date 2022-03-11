package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoReferido;

public class ClienteYReferidoWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClienteYReferidoWrapper() {
		
	}
    private TbQoCliente cliente;
    private TbQoReferido referido;
    private Boolean bandera;
    private String nombreApoderado;
    private String identificacionApoderado;
    private Date fechaNacimientoApoderado;
    private Date fechaNacimientoCodeudor;
    private String tipoCliente;
    private String nombreCodeudor;
    private String identificacionCodeudor;
    private Long idCreditoNegociacion;
    private List<CalculadoraOpcionWrapper> opcionCredito;
    
	public TbQoCliente getCliente() {
		return cliente;
	}
	public void setCliente(TbQoCliente cliente) {
		this.cliente = cliente;
	}
	public TbQoReferido getReferido() {
		return referido;
	}
	public void setReferido(TbQoReferido referido) {
		this.referido = referido;
	}
	public Boolean getBandera() {
		return bandera;
	}
	public void setBandera(Boolean bandera) {
		this.bandera = bandera;
	}
	public String getNombreApoderado() {
		return nombreApoderado;
	}
	public void setNombreApoderado(String nombreApoderado) {
		this.nombreApoderado = nombreApoderado;
	}
	public String getIdentificacionApoderado() {
		return identificacionApoderado;
	}
	public void setIdentificacionApoderado(String identificacionApoderado) {
		this.identificacionApoderado = identificacionApoderado;
	}
	public Date getFechaNacimientoApoderado() {
		return fechaNacimientoApoderado;
	}
	public void setFechaNacimientoApoderado(Date fechaNacimientoApoderado) {
		this.fechaNacimientoApoderado = fechaNacimientoApoderado;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getNombreCodeudor() {
		return nombreCodeudor;
	}
	public void setNombreCodeudor(String nombreCodeudor) {
		this.nombreCodeudor = nombreCodeudor;
	}
	public String getIdentificacionCodeudor() {
		return identificacionCodeudor;
	}
	public void setIdentificacionCodeudor(String identificacionCodeudor) {
		this.identificacionCodeudor = identificacionCodeudor;
	}
	public Long getIdCreditoNegociacion() {
		return idCreditoNegociacion;
	}
	public void setIdCreditoNegociacion(Long idCreditoNegociacion) {
		this.idCreditoNegociacion = idCreditoNegociacion;
	}
	public Date getFechaNacimientoCodeudor() {
		return fechaNacimientoCodeudor;
	}
	public void setFechaNacimientoCodeudor(Date fechaNacimientoCodeudor) {
		this.fechaNacimientoCodeudor = fechaNacimientoCodeudor;
	}
	public List<CalculadoraOpcionWrapper> getOpcionCredito() {
		return opcionCredito;
	}
	public void setOpcionCredito(List<CalculadoraOpcionWrapper> opcionCredito) {
		this.opcionCredito = opcionCredito;
	}
    
    
	
}
