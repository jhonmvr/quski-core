package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class RubroOperacionWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idRubro;
    private String rubro;
    private String codigoQuski;
    private Long numeroCuota;
    private BigDecimal proyectado;
    private BigDecimal calculado;
    private BigDecimal cobrado;
    private String estado;
    
	public String getCodigoQuski() {
		return codigoQuski;
	}
	public void setCodigoQuski(String codigoQuski) {
		this.codigoQuski = codigoQuski;
	}
	public BigDecimal getCobrado() {
		return cobrado;
	}
	public void setCobrado(BigDecimal cobrado) {
		this.cobrado = cobrado;
	}
	public Long getIdRubro() {
		return idRubro;
	}
	public void setIdRubro(Long idRubro) {
		this.idRubro = idRubro;
	}
	public String getRubro() {
		return rubro;
	}
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}
	public Long getNumeroCuota() {
		return numeroCuota;
	}
	public void setNumeroCuota(Long numeroCuota) {
		this.numeroCuota = numeroCuota;
	}
	public BigDecimal getProyectado() {
		return proyectado;
	}
	public void setProyectado(BigDecimal proyectado) {
		this.proyectado = proyectado;
	}
	public BigDecimal getCalculado() {
		return calculado;
	}
	public void setCalculado(BigDecimal calculado) {
		this.calculado = calculado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    

}
