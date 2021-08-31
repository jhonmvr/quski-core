package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class CatalogoTablaAmortizacionWrapper implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2944859038807620286L;
		private String codigo;
		private String periodoPlazo;
		private String periodicidadPlazo;
		private String tipoOferta;
		private Long plazo;
		private Long idTipoTablaAmortizacion;
		private String nombreTipoTablaAmortizacion;
		private Long frecuenciaPago;
		private Long numeroCuotas;
		private Long porcentajeAmortizacion;
		
		public CatalogoTablaAmortizacionWrapper(String codigo, String periodoPlazo, String periodicidadPlazo,
				String tipoOferta, Long plazo, Long idTipoTablaAmortizacion, String nombreTipoTablaAmortizacion,
				Long frecuenciaPago, Long numeroCuotas, Long porcentajeAmortizacion) {
			super();
			this.codigo = codigo;
			this.periodoPlazo = periodoPlazo;
			this.periodicidadPlazo = periodicidadPlazo;
			this.tipoOferta = tipoOferta;
			this.plazo = plazo;
			this.idTipoTablaAmortizacion = idTipoTablaAmortizacion;
			this.nombreTipoTablaAmortizacion = nombreTipoTablaAmortizacion;
			this.frecuenciaPago = frecuenciaPago;
			this.numeroCuotas = numeroCuotas;
			this.porcentajeAmortizacion = porcentajeAmortizacion;
		}
		
		public String getCodigo() {
			return codigo;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
		public String getPeriodoPlazo() {
			return periodoPlazo;
		}
		public void setPeriodoPlazo(String periodoPlazo) {
			this.periodoPlazo = periodoPlazo;
		}
		public String getPeriodicidadPlazo() {
			return periodicidadPlazo;
		}
		public void setPeriodicidadPlazo(String periodicidadPlazo) {
			this.periodicidadPlazo = periodicidadPlazo;
		}
		public String getTipoOferta() {
			return tipoOferta;
		}
		public void setTipoOferta(String tipoOferta) {
			this.tipoOferta = tipoOferta;
		}
		public Long getPlazo() {
			return plazo;
		}
		public void setPlazo(Long plazo) {
			this.plazo = plazo;
		}
		public Long getIdTipoTablaAmortizacion() {
			return idTipoTablaAmortizacion;
		}
		public void setIdTipoTablaAmortizacion(Long idTipoTablaAmortizacion) {
			this.idTipoTablaAmortizacion = idTipoTablaAmortizacion;
		}
		public String getNombreTipoTablaAmortizacion() {
			return nombreTipoTablaAmortizacion;
		}
		public void setNombreTipoTablaAmortizacion(String nombreTipoTablaAmortizacion) {
			this.nombreTipoTablaAmortizacion = nombreTipoTablaAmortizacion;
		}
		public Long getFrecuenciaPago() {
			return frecuenciaPago;
		}
		public void setFrecuenciaPago(Long frecuenciaPago) {
			this.frecuenciaPago = frecuenciaPago;
		}
		public Long getNumeroCuotas() {
			return numeroCuotas;
		}
		public void setNumeroCuotas(Long numeroCuotas) {
			this.numeroCuotas = numeroCuotas;
		}
		public Long getPorcentajeAmortizacion() {
			return porcentajeAmortizacion;
		}
		public void setPorcentajeAmortizacion(Long porcentajeAmortizacion) {
			this.porcentajeAmortizacion = porcentajeAmortizacion;
		}
}
