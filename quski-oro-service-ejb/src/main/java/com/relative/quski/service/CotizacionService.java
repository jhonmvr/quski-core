package com.relative.quski.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
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

	/**
	 * Metodo que recibe una cotizacion y la crea para tener el id de la cotizacion
	 * 
	 * @param cot
	 * @return cotLlena
	 * @throws RelativeException
	 */
	public TbQoCotizador crearCotizacionClienteVariableCrediticia(TbQoCotizador cot) throws RelativeException {
		try {
			log.info("Valor que llega al servicio...> " + cot);
			TbQoCotizador cotLlena = new TbQoCotizador();
			List<TbQoVariablesCrediticia> variableCrediticiaLlega = new ArrayList<TbQoVariablesCrediticia>();
			 

			if (cot != null && cot.getId() == null) {
				log.info("Valor que llega al IF...> " + cot);
				log.info("******INGRESA AL IF");
							
				for (TbQoVariablesCrediticia varCredi : cot.getTbQoVariablesCrediticias()) {
					variableCrediticiaLlega.add(varCredi);
				}
				cotLlena.setTbQoVariablesCrediticias(variableCrediticiaLlega);
				cotLlena = this.qos.manageCotizador(cot);

			} else {
				log.info("ES ANTIGUO");
				cotLlena = this.qos.findCotizadorById(cot.getId());

			}
			return cotLlena;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}

	}

}
