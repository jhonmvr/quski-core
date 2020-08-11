package com.relative.quski.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoVariablesCrediticia;
import com.relative.quski.repository.CotizadorRepository;

@Stateless
public class CotizacionService {

	@Inject
	Logger log;
	@Inject
	QuskiOroService qos;
	@Inject
	CotizadorRepository cotizacionRepository;

	public CotizacionService() {
		super();
	}

	/**
	 * Metodo que busca la cotizacion completa activa por cedula de cliente
	 * 
	 * @author KLÉBER GUERRA - Relative Engine
	 * @param cedulaCliente
	 * @return cotizacion
	 * @throws RelativeException
	 */
	public TbQoCotizador buscarCotizacionActivaPorCedula(String cedulaCliente) throws RelativeException {

		log.info("INGRESA AL METODO buscarCotizacionActivaPorCedula " + cedulaCliente);
		TbQoCotizador coti = new TbQoCotizador();
		coti = cotizacionRepository.findCotizadorByCedula(cedulaCliente);
		log.info("VALOR QUE RETORNA DE LA BUSQUEDA POR CEDULA COTI>> " + coti);

		List<TbQoCotizador> cotizaciones = new ArrayList<TbQoCotizador>();
		cotizaciones = qos.listByCliente(null, cedulaCliente);

		log.info("VALOR LENGTH DEL LISTADO DE COTIZACIONES>>>>>>>>>> " + cotizaciones.size());

		for (TbQoCotizador cot : cotizaciones) {
			if (cot.getEstado().equals(EstadoEnum.ACT)) {
				coti = cot;
			}
		}
		log.info("BUSCAR VARIABLES CREDITICIAS Y PRECIO ORO");
		if (coti.getTbQoVariablesCrediticias() != null && coti.getTbQoPrecioOros() != null) {
			log.info("VALOR DE LA COTIZACION GET VARIABLE CREDITICIA>> " + coti.getTbQoVariablesCrediticias()
					+ "VALOR GET PRECIO ORO>>>" + coti.getTbQoPrecioOros());

		} else {
			log.info("INGRESA AL ELESE buscarCotizacionActivaPorCedula");
		}

		log.info(" GET VARIABLE CREDITICIA PARA RETORNAR>> " + coti.getTbQoVariablesCrediticias());
		log.info("GET PRECIO ORO PARA RETORNAR ES>> " + coti.getTbQoPrecioOros());
		return coti;
	}

	/**
	 * Metodo que recibe cotizacion y la crea al cliente la cotizacion y la Variable
	 * Crediticia
	 * 
	 * @author KLÉBER GUERRA - Relative Engine
	 * @param cot
	 * @return cotLlena
	 * @throws RelativeException
	 */

	public TbQoCotizador crearCotizacionClienteVariableCrediticia(TbQoCotizador cot) throws RelativeException {
		try {
			log.info("INGRESA A crearCotizacionClienteVariableCrediticia=====> IDCLIENTE "
					+ cot.getTbQoCliente().getId());
			log.info("CEDULA EN crearCotizacionClienteVariableCrediticia=====> NUMERO DE CEDULA "
					+ cot.getTbQoCliente().getCedulaCliente());
			TbQoCotizador cotLlena = new TbQoCotizador();
	
			// BUSQUEDA POR LA CEDULA
			List<TbQoVariablesCrediticia> variableCrediticiaLlega = cot.getTbQoVariablesCrediticias();
			log.info("crearCotizacionClienteVariableCrediticia ====VALOR DE LA COTIZACION====> "+cot);
			if (cot!=null && cot.getId()!=null) {
				log.info("crearCotizacionClienteVariableCrediticia=====Ingresa a la cotizacion ===> "+cot.getTbQoCliente().getCedulaCliente());
				this.buscarCotizacionActivaPorCedula(cot.getTbQoCliente().getCedulaCliente());
				log.info("crearCotizacionClienteVariableCrediticia======Ingresa a caducar la cotizacion====> "+cot);
				//this.caducarCotizacion(cot);
				log.info("crearCotizacionClienteVariableCrediticia========Cotizacion caducada====> "+cot);
				
			}
			if (cot != null && cot.getId() == null && cot.getTbQoCliente().getCedulaCliente() != null) {
								
				TbQoCliente cliente = this.qos.manageCliente(cot.getTbQoCliente());
				cot.setTbQoCliente(cliente);
				log.info("EL VALOR DEL CLIENTE FUERA DEL MANAGE ES =====>" + cot.getTbQoCliente());
				cotLlena = this.qos.manageCotizador(cot);
				log.info("idCotizacion=====> " + cotLlena.getId());
				for (TbQoVariablesCrediticia varCredi : variableCrediticiaLlega) {
					log.info("====> varable crediticia===> " + varCredi.getNombre());
					varCredi.setTbQoCotizador(cotLlena);
					this.qos.manageVariablesCrediticia(varCredi);
				}
				cotLlena.setTbQoVariablesCrediticias(variableCrediticiaLlega);
			} else {
				TbQoCliente cliente = this.qos.manageCliente(cot.getTbQoCliente());
				cot.setTbQoCliente(cliente);
				log.info("EL VALOR DEL CLIENTE FUERA DEL MANAGE ES =====>" + cot.getTbQoCliente());
				// RARO
				log.info("===>COTIZACION EXISTE====> ");
				cotLlena = this.qos.findCotizadorById(cot.getId());
				log.info("VALOR  COTIZACION ESTADO ACT====>>>" + cotLlena.getId());

			}
			return cotLlena;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					"Error al crear la cotizacion y varables crediticias " + e.getMessage());
		}

	}

	/***
	 * METODO QUE REALIZA LA CADUCACION DE LA COTIZACION ANTERIOR
	 * 
	 * @author KLÉBER GUERRA - Relative Engine
	 * @param cot
	 * @return
	 * @throws RelativeException
	 */
	public TbQoCotizador caducarCotizacion(TbQoCotizador cot) throws RelativeException {
		log.info("ingresa a caducar  --> " + cot);
		if (cot != null && cot.getId() != null) {
			cot.setEstado(EstadoEnum.INA);

		}
		return this.qos.manageCotizador(cot);

	}

	public TbQoCliente buscarCliente(String identificacion) throws RelativeException {
		TbQoCliente cliente = qos.findClienteByIdentificacionWithCotizacion(identificacion);
		return cliente;
	}

}
