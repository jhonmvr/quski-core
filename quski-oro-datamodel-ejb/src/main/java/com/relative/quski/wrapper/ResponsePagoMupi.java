package com.relative.quski.wrapper;
import java.io.Serializable;
public class ResponsePagoMupi implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5477571595490668414L;
	private Boolean existe_error;
	private Boolean pago_aplicado;
	private Long codigo_servicio;
	private String numero_prestamo;
	private String mensaje;
	public Boolean getExisteError() {
		return existe_error;
	}
	public void setExisteError(Boolean existeError) {
		this.existe_error = existeError;
	}
	public Boolean getPagoAplicado() {
		return pago_aplicado;
	}
	public void setPagoAplicado(Boolean pagoAplicado) {
		this.pago_aplicado = pagoAplicado;
	}
	public Long getCodigoServicio() {
		return codigo_servicio;
	}
	public void setCodigoServicio(Long codigoServicio) {
		this.codigo_servicio = codigoServicio;
	}
	public String getNumeroPrestamo() {
		return numero_prestamo;
	}
	public void setNumeroPrestamo(String numeroPrestamo) {
		this.numero_prestamo = numeroPrestamo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}
