package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

public class DetalleCreditoWrapper implements Serializable {

	public DetalleCreditoWrapper() {
		
	}
	private String plazoCredito;
	private BigDecimal montoPreaprobado;
	private BigDecimal recibirCliente;
	private BigDecimal costoNuevaOperacion;
	private BigDecimal costoCustodia;
	private BigDecimal costoTransporte;
	private BigDecimal costoCredito;
	private BigDecimal costoSeguro;
	private BigDecimal costoResguardo;
	private BigDecimal costoEstimado;
	private BigDecimal valorCuota;
	public String getPlazoCredito() {
		return plazoCredito;
	}
	public void setPlazoCredito(String plazoCredito) {
		this.plazoCredito = plazoCredito;
	}
	public BigDecimal getMontoPreaprobado() {
		return montoPreaprobado;
	}
	public void setMontoPreaprobado(BigDecimal montoPreaprobado) {
		this.montoPreaprobado = montoPreaprobado;
	}
	public BigDecimal getRecibirCliente() {
		return recibirCliente;
	}
	public void setRecibirCliente(BigDecimal recibirCliente) {
		this.recibirCliente = recibirCliente;
	}
	public BigDecimal getCostoNuevaOperacion() {
		return costoNuevaOperacion;
	}
	public void setCostoNuevaOperacion(BigDecimal costoNuevaOperacion) {
		this.costoNuevaOperacion = costoNuevaOperacion;
	}
	public BigDecimal getCostoCustodia() {
		return costoCustodia;
	}
	public void setCostoCustodia(BigDecimal costoCustodia) {
		this.costoCustodia = costoCustodia;
	}
	public BigDecimal getCostoTransporte() {
		return costoTransporte;
	}
	public void setCostoTransporte(BigDecimal costoTransporte) {
		this.costoTransporte = costoTransporte;
	}
	public BigDecimal getCostoCredito() {
		return costoCredito;
	}
	public void setCostoCredito(BigDecimal costoCredito) {
		this.costoCredito = costoCredito;
	}
	public BigDecimal getCostoSeguro() {
		return costoSeguro;
	}
	public void setCostoSeguro(BigDecimal costoSeguro) {
		this.costoSeguro = costoSeguro;
	}
	public BigDecimal getCostoResguardo() {
		return costoResguardo;
	}
	public void setCostoResguardo(BigDecimal costoResguardo) {
		this.costoResguardo = costoResguardo;
	}
	public BigDecimal getCostoEstimado() {
		return costoEstimado;
	}
	public void setCostoEstimado(BigDecimal costoEstimado) {
		this.costoEstimado = costoEstimado;
	}
	public BigDecimal getValorCuota() {
		return valorCuota;
	}
	public void setValorCuota(BigDecimal valorCuota) {
		this.valorCuota = valorCuota;
	}
	

}
