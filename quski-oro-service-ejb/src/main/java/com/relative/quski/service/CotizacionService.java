package com.relative.quski.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.model.TbQoVariableCrediticia;
import com.relative.quski.repository.CotizadorRepository;

@Stateless
public class CotizacionService {

	public CotizacionService() {
		super();
	}

	@Inject
	Logger log;
	@Inject
	QuskiOroService qos;
	@Inject
	CotizadorRepository cotizacionRepository;

	/**
	 * Metodo que busca la cotizacion completa activa por cedula de cliente
	 * 
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
	 * Metodo que recibe una cotizacion y la crea para tener el id de la cotizacion
	 * 
	 * @param cot
	 * @return cotLlena
	 * @throws RelativeException
	 */
	public TbQoCotizador crearCotizacionClienteVariableCrediticia(TbQoCotizador cot) throws RelativeException {
		try {
			log.info("VALOR QUE LLEGA DEL FRONT...> " + cot);
			log.info("VALOR DEL ID QUE LLEGA>>" + cot.getId());
			TbQoCotizador cotLlena = new TbQoCotizador();
			List<TbQoVariableCrediticia> variableCrediticiaLlega = new ArrayList<TbQoVariableCrediticia>();
			if (cot != null && cot.getId() == null) {
				log.info("Valor que llega al IF...> " + cot);
				log.info("******INGRESA AL IF");

				for (TbQoVariableCrediticia varCredi : cot.getTbQoVariablesCrediticias()) {
					variableCrediticiaLlega.add(varCredi);
				}
				cotLlena.setTbQoVariablesCrediticias(variableCrediticiaLlega);
				cotLlena = this.qos.manageCotizador(cot);

			} else {
				if (cot.getEstado().equals(EstadoEnum.ACT))
					log.info("ES ANTIGUO");
				cotLlena = this.qos.findCotizadorById(cot.getId());
				log.info("VALOR  COTIZACION ESTADO ACT>>>" + cotLlena);

			}
			return cotLlena;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}

	}

}
