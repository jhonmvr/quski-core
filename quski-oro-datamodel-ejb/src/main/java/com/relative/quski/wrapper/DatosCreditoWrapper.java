package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;

import com.relative.quski.model.TbQoCreditoNegociacion;

public class DatosCreditoWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 private  TbQoCreditoNegociacion credito;
	 private  Date 	 fechaCuota;
	 private  String pesoFunda;
	 private  String numeroFunda;
	 private  String totalPesoBrutoFunda;
	 private  String tipoCuenta;
	 private  String numeroCuenta;
	 private  String tipoCliente;
	 private  String firmanteOperacion;
	 private  String identificacionCodeudor;
	 private  String nombreCompletoCodeudor;
	 private  Date fechaNacimientoCodeudor;
	 private  String identificacionApoderado;
	 private  String nombreCompletoApoderado;
	 private  Date fechaNacimientoApoderado;

	 private  String usuario;
	 private  Long idAgencia;
	 
	 
	public TbQoCreditoNegociacion getCredito() {
		return credito;
	}
	public void setCredito(TbQoCreditoNegociacion credito) {
		this.credito = credito;
	}
	public Date getFechaCuota() {
		return fechaCuota;
	}
	public void setFechaCuota(Date fechaCuota) {
		this.fechaCuota = fechaCuota;
	}
	public String getPesoFunda() {
		return pesoFunda;
	}
	public void setPesoFunda(String pesoFunda) {
		this.pesoFunda = pesoFunda;
	}
	public String getNumeroFunda() {
		return numeroFunda;
	}
	public void setNumeroFunda(String numeroFunda) {
		this.numeroFunda = numeroFunda;
	}
	public String getTotalPesoBrutoFunda() {
		return totalPesoBrutoFunda;
	}
	public void setTotalPesoBrutoFunda(String totalPesoBrutoFunda) {
		this.totalPesoBrutoFunda = totalPesoBrutoFunda;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getFirmanteOperacion() {
		return firmanteOperacion;
	}
	public Date getFechaNacimientoCodeudor() {
		return fechaNacimientoCodeudor;
	}
	public void setFechaNacimientoCodeudor(Date fechaNacimientoCodeudor) {
		this.fechaNacimientoCodeudor = fechaNacimientoCodeudor;
	}
	public Date getFechaNacimientoApoderado() {
		return fechaNacimientoApoderado;
	}
	public void setFechaNacimientoApoderado(Date fechaNacimientoApoderado) {
		this.fechaNacimientoApoderado = fechaNacimientoApoderado;
	}
	public void setFirmanteOperacion(String firmanteOperacion) {
		this.firmanteOperacion = firmanteOperacion;
	}
	public String getIdentificacionCodeudor() {
		return identificacionCodeudor;
	}
	public void setIdentificacionCodeudor(String identificacionCodeudor) {
		this.identificacionCodeudor = identificacionCodeudor;
	}
	public String getNombreCompletoCodeudor() {
		return nombreCompletoCodeudor;
	}
	public void setNombreCompletoCodeudor(String nombreCompletoCodeudor) {
		this.nombreCompletoCodeudor = nombreCompletoCodeudor;
	}

	public String getIdentificacionApoderado() {
		return identificacionApoderado;
	}
	public void setIdentificacionApoderado(String identificacionApoderado) {
		this.identificacionApoderado = identificacionApoderado;
	}
	public String getNombreCompletoApoderado() {
		return nombreCompletoApoderado;
	}
	public void setNombreCompletoApoderado(String nombreCompletoApoderado) {
		this.nombreCompletoApoderado = nombreCompletoApoderado;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Long getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

}
