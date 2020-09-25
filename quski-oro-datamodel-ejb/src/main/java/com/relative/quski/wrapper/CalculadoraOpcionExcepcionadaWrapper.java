package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraOpcionExcepcionadaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	 private Integer plazo; 
     private Double montoCredito; 
     private Double riesgoAcumulado;
     private Double valorDesembolso;
     private Double cuota;
     
 	public static List<CalculadoraOpcionExcepcionadaWrapper> generateMockupOpcion() {
 		List<CalculadoraOpcionExcepcionadaWrapper> result = new ArrayList<>();

 		CalculadoraOpcionExcepcionadaWrapper tmp = new CalculadoraOpcionExcepcionadaWrapper();
 		tmp.setPlazo( 60 );
 		tmp.setMontoCredito( 480.80 );
 		tmp.setRiesgoAcumulado( 1114.22 );
		tmp.setValorDesembolso( 427.82 );
		tmp.setCuota( 493.67 );
		result.add( tmp );
 		CalculadoraOpcionExcepcionadaWrapper tmp2 = new CalculadoraOpcionExcepcionadaWrapper();
 		tmp2.setPlazo( 90 );
 		tmp2.setMontoCredito( 480.80 );
 		tmp2.setRiesgoAcumulado( 1114.22 );
		tmp2.setValorDesembolso( 421.50 );
		tmp2.setCuota( 500.10 );
		result.add( tmp2 );
 		CalculadoraOpcionExcepcionadaWrapper tmp3 = new CalculadoraOpcionExcepcionadaWrapper();
 		tmp3.setPlazo( 120 );
 		tmp3.setMontoCredito( 480.80 );
 		tmp3.setRiesgoAcumulado( 1114.22 );
		tmp3.setValorDesembolso( 421.12 );
		tmp3.setCuota( 506.54 );
		result.add( tmp3 );
 		CalculadoraOpcionExcepcionadaWrapper tmp4 = new CalculadoraOpcionExcepcionadaWrapper();
 		tmp4.setPlazo( 6 );
 		tmp4.setMontoCredito( 480.80 );
 		tmp4.setRiesgoAcumulado( 1114.22 );
		tmp4.setValorDesembolso( 385.31 );
		tmp4.setCuota( 83.93 );
		result.add( tmp4 );
 		CalculadoraOpcionExcepcionadaWrapper tmp5 = new CalculadoraOpcionExcepcionadaWrapper();
 		tmp5.setPlazo( 9 );
 		tmp5.setMontoCredito( 480.80 );
 		tmp5.setRiesgoAcumulado( 1114.22 );
		tmp5.setValorDesembolso( 367.69 );
		tmp5.setCuota( 57.06 );
		result.add( tmp5 );
 		CalculadoraOpcionExcepcionadaWrapper tmp6 = new CalculadoraOpcionExcepcionadaWrapper();
 		tmp6.setPlazo( 12 );
 		tmp6.setMontoCredito( 480.80 );
 		tmp6.setRiesgoAcumulado( 1114.22 );
		tmp6.setValorDesembolso( 344.28 );
		tmp6.setCuota( 43.64 );
		result.add( tmp6 );
		return result;
	}
	public Integer getPlazo() {
		return plazo;
	}
	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}
	public Double getMontoCredito() {
		return montoCredito;
	}
	public void setMontoCredito(Double montoCredito) {
		this.montoCredito = montoCredito;
	}
	public Double getRiesgoAcumulado() {
		return riesgoAcumulado;
	}
	public void setRiesgoAcumulado(Double riesgoAcumulado) {
		this.riesgoAcumulado = riesgoAcumulado;
	}
	public Double getValorDesembolso() {
		return valorDesembolso;
	}
	public void setValorDesembolso(Double valorDesembolso) {
		this.valorDesembolso = valorDesembolso;
	}
	public Double getCuota() {
		return cuota;
	}
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
}
