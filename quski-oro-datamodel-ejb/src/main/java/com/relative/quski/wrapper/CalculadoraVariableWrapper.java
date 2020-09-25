package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraVariableWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

    private Integer orden;
    private String codigo;
    private String nombre;
    private String valor;
    
    public static List<CalculadoraVariableWrapper> generateMockup() {
    	List<CalculadoraVariableWrapper> tmp = new  ArrayList<>();
    	CalculadoraVariableWrapper o = new CalculadoraVariableWrapper(); 
    	o.setCodigo( "TipoCliente" );
    	o.setNombre( "Tipo Cliente" );
    	o.setOrden( 1 );
    	o.setValor( "Q" );
    	CalculadoraVariableWrapper a = new CalculadoraVariableWrapper(); 
    	a.setCodigo( "PerfilExterno" );
    	a.setNombre( "Perfil Externo" );
    	a.setOrden( 2 );
    	a.setValor( "5" );
    	tmp.add(o);
    	tmp.add(a);
    	return tmp;
    }
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
    
    
}
