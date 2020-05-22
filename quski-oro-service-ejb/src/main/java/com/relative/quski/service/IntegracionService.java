package com.relative.quski.service;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.hazelcast.internal.cluster.impl.operations.FinalizeJoinOp;
import com.hazelcast.spi.impl.operationservice.impl.responses.Response;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.integracion.calculadora.cliente.ServicePerson;
import com.relative.integracion.calculadora.cliente.ServicePersonSoap;
import com.relative.integracion.calculadora.cliente.consumer.ClienteCalculadoraConsumer;
import com.relative.integracion.calculadora.cliente.wrapper.Informacion;
import com.relative.integracion.calculadora.util.IntegracionUtil;

@Stateless
public class IntegracionService {
	@Inject
	Logger log;
	@Inject 
	private ClienteCalculadoraConsumer respuesta;
	
	/**
	 * Metodo que se encarga de extraer la informacion de clientes desde el servicio Quski Clientes
	 * @param tipoIdentificacion tipo de indetifiacion, los valores a tomar son:<br>
	 * 1. C cedula<br>
	 * 2. R ruc
	 * @param identificacion Identifiacion del cliente (cedula, ruc)
	 * @param tipoConsulta Tipo de consulta a realizar:<br>
	 * 1. CC Consulta client<br>
	 * 2. Tienes qu ver que otros parametros recibe en tipo consulta
	 * @param calificacion calificacion del cliente
	 * @return Objeto con la informacion retornado si el cliente tiene datos, o el error generado.
	 */
	
}
	

