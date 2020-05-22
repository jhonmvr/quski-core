package com.relative.quski.service;

import com.relative.core.exception.RelativeException;
import com.relative.integracion.calculadora.cliente.consumer.ClienteCalculadoraConsumer;
import com.relative.integracion.calculadora.cliente.wrapper.Informacion;
import javax.ejb.Stateless;

@Stateless
public class IntegracionServiceImp
{
    public Informacion getClienteInformacion(final String tipoIdentificacion, final String identificacion, final String tipoConsulta, final String calificacion) throws RelativeException {
        return ClienteCalculadoraConsumer.callCalculadoraClienteObject(tipoIdentificacion, identificacion, tipoConsulta, calificacion);
    }
}
	
	

	

