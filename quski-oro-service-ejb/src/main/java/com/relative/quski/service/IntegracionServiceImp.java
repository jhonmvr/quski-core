package com.relative.quski.service;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.integracion.calculadora.cliente.consumer.ClienteCalculadoraConsumer;
import com.relative.integracion.calculadora.cliente.consumer.ClienteConsultaOfertaConsumer;
import com.relative.integracion.calculadora.cliente.wrapper.Informacion;
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

	public Informacion getClienteInformacion(final String tipoIdentificacion, final String identificacion,
			final String tipoConsulta, final String calificacion) throws RelativeException {
		return ClienteCalculadoraConsumer.callCalculadoraClienteObject(tipoIdentificacion, identificacion, tipoConsulta,
				calificacion);
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


	public SimularResponse getClienteInformacionOferta(final String perfilRiesgo, final String origenOperacion,
			final String riesgoTotal,final  String fechaNacimiento,final  String perfilPreferencia, final String agenciaOriginacion,
			final String identificacionCliente,final  String calificacionMupi, final String coberturaExcepcionada,final  String tipoJoya,
			final String descripcion,final  String estadoJoya,final  String tipoOroKilataje, final String pesoGr,final  String tienePiedras,
			final String detallePiedras,final  String descuentoPesoPiedras,final  String pesoNeto, final String precioOro,
			final String valorAplicableCredito,final  String valorRealizacion,final  String numeroPiezas,final  String descuentoSuelda)
			throws RelativeException {
		return ClienteConsultaOfertaConsumer.callCalculadoraClienteObject(perfilRiesgo, origenOperacion, riesgoTotal, fechaNacimiento, perfilPreferencia, agenciaOriginacion, identificacionCliente, calificacionMupi, coberturaExcepcionada, tipoJoya, descripcion, estadoJoya, tipoOroKilataje, pesoGr, tienePiedras, detallePiedras, descuentoPesoPiedras, pesoNeto, precioOro, valorAplicableCredito, valorRealizacion, numeroPiezas, descuentoSuelda);
	}
}
