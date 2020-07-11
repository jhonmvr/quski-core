package com.relative.quski.service;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.integracion.calculadora.cliente.consumer.ClienteCalculadoraConsumer;
import com.relative.integracion.calculadora.cliente.consumer.ClienteCalculadoraOfertaGarantiaConsumer;
import com.relative.integracion.calculadora.cliente.consumer.ClienteConsultaOfertaConsumer;
import com.relative.integracion.calculadora.cliente.wrapper.Informacion;
import com.relative.integracion.calculadora.garantia.wrapper.EnvioInformacionPersonaOfertaWrapper;
import com.relative.integracion.calculadora.oferta.wrapper.SimularResponse;

@Stateless
public class IntegracionServiceImp {
	
	/**
	 * Metodo que se encarga de extraer la informacion de clientes desde el servicio Quski Clientes
	 * @param tipoIdentificacion tipo de indetifiacion, los valores a tomar son:<br>
	 * 1. C cedula<br>
	 * 2. R ruc
	 * @param identificacion Identifiacion del cliente (cedula, ruc)
	 * @param tipoConsulta Tipo de consulta a realizar:<br>
	 * 1. CE: Consulta con excepción
	 * 2  CC: Consulta con variables internas
	 * 3  TD: Consulta sin variables interna
	 * @param calificacion calificacion del cliente
	 * @return Objeto con la informacion retornado si el cliente tiene datos, o el error generado.
	 * @author BRAYAN MONGE - Relative Engine
	 * @throws RelativeException
	 */
	public Informacion getClienteInformacion( String tipoIdentificacion,  String identificacion,String tipoConsulta,  String calificacion) throws RelativeException {
		return ClienteCalculadoraConsumer.callCalculadoraClienteObject(tipoIdentificacion, identificacion, tipoConsulta,calificacion);
	}
	
	/**
	 * Metodo que se encarga de extraer la informacion de la Oferta desde el servicio Quski Clientes
	 * @param perfilRiesgo
	 * @param origenOperacion
	 * @param riesgoTotal
	 * @param fechaNacimiento
	 * @param perfilPreferencia
	 * @param agenciaOriginacion
	 * @param identificacionCliente
	 * @param calificacionMupi
	 * @param coberturaExcepcionada
	 * @param tipoJoya
	 * @param descripcion
	 * @param estadoJoya
	 * @param tipoOroKilataje
	 * @param pesoGr
	 * @param tienePiedras
	 * @param detallePiedras
	 * @param descuentoPesoPiedras
	 * @param pesoNeto
	 * @param precioOro
	 * @param valorAplicableCredito
	 * @param valorRealizacion
	 * @param numeroPiezas
	 * @param descuentoSuelda
	 * @returnObjeto con la informacion retornado con la oferta, o el error generado.
	 * @author BRAYAN MONGE - Relative Engine
	 * @throws RelativeException
	 */


	public SimularResponse getClienteInformacionOferta( String perfilRiesgo,  String origenOperacion,
			 String riesgoTotal,  String fechaNacimiento,  String perfilPreferencia,  String agenciaOriginacion,
			 String identificacionCliente,  String calificacionMupi,  String coberturaExcepcionada,  String tipoJoya,
			 String descripcion,  String estadoJoya,  String tipoOroKilataje,  String pesoGr,  String tienePiedras,
			 String detallePiedras,  String descuentoPesoPiedras,  String pesoNeto,  String precioOro,
			 String valorAplicableCredito,  String valorRealizacion,  String numeroPiezas,  String descuentoSuelda)
			throws RelativeException {
		return ClienteConsultaOfertaConsumer.callCalculadoraClienteObject(perfilRiesgo, origenOperacion, riesgoTotal, fechaNacimiento, perfilPreferencia, agenciaOriginacion, identificacionCliente, calificacionMupi, coberturaExcepcionada, tipoJoya, descripcion, estadoJoya, tipoOroKilataje, pesoGr, tienePiedras, detallePiedras, descuentoPesoPiedras, pesoNeto, precioOro, valorAplicableCredito, valorRealizacion, numeroPiezas, descuentoSuelda);
	}
	
	
	public SimularResponse getClienteInformacionOfertaGarantia(EnvioInformacionPersonaOfertaWrapper envioInformacionPersonaOfertaWrapper) 
			throws RelativeException {
		return ClienteCalculadoraOfertaGarantiaConsumer.callCalculadoraClienteObject(envioInformacionPersonaOfertaWrapper);
	}
}