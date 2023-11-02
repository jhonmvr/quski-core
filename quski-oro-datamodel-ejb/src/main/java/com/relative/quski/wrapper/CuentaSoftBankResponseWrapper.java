package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class CuentaSoftBankResponseWrapper implements Serializable{
	private String identificacion;
	private List<CuentaBancaria>  cuentasBancariasCliente;
	

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public List<CuentaBancaria> getCuentasBancariasCliente() {
		return cuentasBancariasCliente;
	}

	public void setCuentasBancariasCliente(List<CuentaBancaria> cuentasBancariasCliente) {
		this.cuentasBancariasCliente = cuentasBancariasCliente;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2182628768883136848L;

	public class CuentaBancaria implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 339923903086889104L;
		private Long id;
		private Long idBanco;
		private String cuenta;
		private Boolean esAhorros;
		private Boolean esNueva;
		private Boolean activo;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getIdBanco() {
			return idBanco;
		}
		public void setIdBanco(Long idBanco) {
			this.idBanco = idBanco;
		}
		public String getCuenta() {
			return cuenta;
		}
		public void setCuenta(String cuenta) {
			this.cuenta = cuenta;
		}
		public Boolean getEsAhorros() {
			return esAhorros;
		}
		public void setEsAhorros(Boolean esAhorros) {
			this.esAhorros = esAhorros;
		}
		public Boolean getEsNueva() {
			return esNueva;
		}
		public void setEsNueva(Boolean esNueva) {
			this.esNueva = esNueva;
		}
		public Boolean getActivo() {
			return activo;
		}
		public void setActivo(Boolean activo) {
			this.activo = activo;
		}
		
	}
}
