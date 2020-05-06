package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

import com.relative.quski.enums.EstadoEnum;

public class DetalleCreditoWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public DetalleCreditoWrapper() {
		
	}
	private String plazoCredito;
	private Double montoPreaprobado;
	private Double recibirCliente;
	private Double costoNuevaOperacion;
	private Double costoCustodia;
	private Double costoTransporte;
	private Double costoCredito;
	private Double costoSeguro;
	private Double costoResguardo;
	private Double costoEstimado;
	private Double valorCuota;
	private EstadoEnum estado;
	public String getPlazoCredito() {
		return plazoCredito;
	}
	public void setPlazoCredito(String plazoCredito) {
		this.plazoCredito = plazoCredito;
	}
	public Double getMontoPreaprobado() {
		return montoPreaprobado;
	}
	public void setMontoPreaprobado(Double montoPreaprobado) {
		this.montoPreaprobado = montoPreaprobado;
	}
	public Double getRecibirCliente() {
		return recibirCliente;
	}
	public void setRecibirCliente(Double recibirCliente) {
		this.recibirCliente = recibirCliente;
	}
	public Double getCostoNuevaOperacion() {
		return costoNuevaOperacion;
	}
	public void setCostoNuevaOperacion(Double costoNuevaOperacion) {
		this.costoNuevaOperacion = costoNuevaOperacion;
	}
	public Double getCostoCustodia() {
		return costoCustodia;
	}
	public void setCostoCustodia(Double costoCustodia) {
		this.costoCustodia = costoCustodia;
	}
	public Double getCostoTransporte() {
		return costoTransporte;
	}
	public void setCostoTransporte(Double costoTransporte) {
		this.costoTransporte = costoTransporte;
	}
	public Double getCostoCredito() {
		return costoCredito;
	}
	public void setCostoCredito(Double costoCredito) {
		this.costoCredito = costoCredito;
	}
	public Double getCostoSeguro() {
		return costoSeguro;
	}
	public void setCostoSeguro(Double costoSeguro) {
		this.costoSeguro = costoSeguro;
	}
	public Double getCostoResguardo() {
		return costoResguardo;
	}
	public void setCostoResguardo(Double costoResguardo) {
		this.costoResguardo = costoResguardo;
	}
	public Double getCostoEstimado() {
		return costoEstimado;
	}
	public void setCostoEstimado(Double costoEstimado) {
		this.costoEstimado = costoEstimado;
	}
	public Double getValorCuota() {
		return valorCuota;
	}
	public void setValorCuota(Double valorCuota) {
		this.valorCuota = valorCuota;
	}
	public EstadoEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}
	
	

}
