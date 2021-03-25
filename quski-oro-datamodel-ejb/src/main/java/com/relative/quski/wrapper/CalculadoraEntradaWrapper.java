package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraEntradaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;


	private CalculadoraParametrosRiesgoWrapper parametroRiesgo;
	private List<CalculadoraGarantiaWrapper> garantias;
	private CalculadoraDescuentosOperacionWrapper descuentosOperacion;
	
	@SuppressWarnings("static-access")
	public void generateMockup() { 
		List<CalculadoraGarantiaWrapper> listMockGarantia  = new ArrayList<>();
		CalculadoraParametrosRiesgoWrapper mockRiesgo = new CalculadoraParametrosRiesgoWrapper();
		CalculadoraGarantiaWrapper mockGarantia = new CalculadoraGarantiaWrapper();
		CalculadoraDescuentosOperacionWrapper mockDescuentos = new CalculadoraDescuentosOperacionWrapper();
		
		mockRiesgo.generateMockup();
		mockGarantia.generateMockup();
		mockDescuentos.generateMockup();
		
		listMockGarantia.add(mockGarantia);
		
		this.setParametroRiesgo(mockRiesgo);
		this.setDescuentosOperacion(mockDescuentos);
		this.setGarantias(listMockGarantia);
	}
	public CalculadoraParametrosRiesgoWrapper getParametroRiesgo() {
		return parametroRiesgo;
	}
	public void setParametroRiesgo(CalculadoraParametrosRiesgoWrapper parametroRiesgo) {
		this.parametroRiesgo = parametroRiesgo;
	}
	public List<CalculadoraGarantiaWrapper> getGarantias() {
		return garantias;
	}
	public void setGarantias(List<CalculadoraGarantiaWrapper> garantias) {
		this.garantias = garantias;
	}
	public CalculadoraDescuentosOperacionWrapper getDescuentosOperacion() {
		return descuentosOperacion;
	}
	public void setDescuentosOperacion(CalculadoraDescuentosOperacionWrapper descuentosOperacion) {
		this.descuentosOperacion = descuentosOperacion;
	}
	
	
}
