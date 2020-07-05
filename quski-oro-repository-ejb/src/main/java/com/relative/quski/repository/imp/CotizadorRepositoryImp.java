package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.repository.CotizadorRepository;
import com.relative.quski.repository.spec.CotizadorByIdentificacionClienteSpec;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "cotizadorRepository")
public class CotizadorRepositoryImp extends GeneralRepositoryImp<Long, TbQoCotizador> implements CotizadorRepository {
	@Inject
	Logger log;

	@Override
	public List<TbQoCotizador> findByCliente(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, String cedulaCliente) throws RelativeException {
		try {
			return findAllBySpecificationPaged(new CotizadorByIdentificacionClienteSpec(cedulaCliente), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	@Override
	public List<TbQoCotizador> findByCliente(String cedulaCliente) throws RelativeException {
		try {
			return findAllBySpecification(new CotizadorByIdentificacionClienteSpec(cedulaCliente));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	@Override
	public Long countByCliente(String cedulaCliente) throws RelativeException {

		try {
			return countBySpecification(new CotizadorByIdentificacionClienteSpec(cedulaCliente));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	/**
	 * Metodo que realiza la busqueda por cedula
	 * 
	 */
	@Override
	public TbQoCotizador findCotizadorByCedula(String cedulaCliente) throws RelativeException {
		try {
			log.info(" ANTES DEL IF <-------------------------------------------------------> " + cedulaCliente );
			List<TbQoCotizador> tmp = this.findAllBySpecification(new CotizadorByIdentificacionClienteSpec(cedulaCliente));
			log.info("LISTA DE COTIZACIONES ACTIVAS DEL CLIENTE ANTES DEL IF -------------------------------------------------------> " + tmp.size());

			if (tmp != null && !tmp.isEmpty()) {
				log.info("LISTA DE COTIZACIONES ACTIVAS DEL CLIENTE  " + tmp.size());
				return tmp.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR cotizador");
		}
	}

	
	
}
