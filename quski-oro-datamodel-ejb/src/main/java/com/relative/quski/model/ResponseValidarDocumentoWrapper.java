package com.relative.quski.model;

import java.io.Serializable;
import java.util.List;

public class ResponseValidarDocumentoWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7907306751617154689L;
	private Long status_code;
	private List<ValidacionDocumento> datos;
	private String numero_prestamo;
	private String mensaje;
	private String aciertos;
	
	public Long getStatus_code() {
		return status_code;
	}

	public void setStatus_code(Long status_code) {
		this.status_code = status_code;
	}

	public List<ValidacionDocumento> getDatos() {
		return datos;
	}

	public void setDatos(List<ValidacionDocumento> datos) {
		this.datos = datos;
	}

	public String getNumero_prestamo() {
		return numero_prestamo;
	}

	public void setNumero_prestamo(String numero_prestamo) {
		this.numero_prestamo = numero_prestamo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getAciertos() {
		return aciertos;
	}

	public void setAciertos(String aciertos) {
		this.aciertos = aciertos;
	}

	public class ValidacionDocumento implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 380691528198926392L;

		private Boolean coincidencia;

		private String confianza;

		private String item;

		private String valor;

		public Boolean getCoincidencia() {
			return coincidencia;
		}

		public void setCoincidencia(Boolean coincidencia) {
			this.coincidencia = coincidencia;
		}

		public String getConfianza() {
			return confianza;
		}

		public void setConfianza(String confianza) {
			this.confianza = confianza;
		}

		public String getItem() {
			return item;
		}

		public void setItem(String item) {
			this.item = item;
		}

		public String getValor() {
			return valor;
		}

		public void setValor(String valor) {
			this.valor = valor;
		}
		
		
	}

}
