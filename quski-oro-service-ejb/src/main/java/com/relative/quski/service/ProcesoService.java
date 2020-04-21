package com.relative.quski.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.Repository;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.model.TbMiParametro;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoReasignacionActividad;
import com.relative.quski.repository.CreditoNegociacionRepository;
import com.relative.quski.repository.ReasignacionRepository;

@Stateless
public class ProcesoService {
	@Inject
	Logger log;
	@Inject
	CreditoNegociacionRepository creditoNegociacionRepository;
	@Inject
	ReasignacionRepository reasignacionRepository;

	/**
	 * Metodo que realiza la busqueda por Código Operacion y EstadoOperacion
	 * 
	 * @param pw
	 * @param codigOperacion
	 * @param estado
	 * @return
	 * @throws RelativeException
	 */
	public List<TbQoCreditoNegociacion> findByCreditoNegociacion(PaginatedWrapper pw, String codigOperacion,
			EstadoOperacionEnum estado) throws RelativeException {

		if (pw != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return creditoNegociacionRepository.findBycodigOpEstado(codigOperacion, estado, pw.getStartRecord(),
					pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
		} else {
			return creditoNegociacionRepository.findBycodigOpEstado(codigOperacion, estado);
		}
	}

	public Long countfindBycodigOperacionEstado(String codigOperacion, EstadoOperacionEnum estado)
			throws RelativeException {
		try {
			return creditoNegociacionRepository.countfindBycodigOpEstado(codigOperacion, estado);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"codigOperacion y estado no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que realiza la busqueda de las reasignaciones por id
	 */
	public TbQoReasignacionActividad findParametroById(Long id) throws RelativeException {
		try {
			return reasignacionRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Reasignacion no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbQoReasignacionActividad manageReasignacion(TbQoReasignacionActividad send) throws RelativeException {
		try {
			log.info("INGRESA A "+send.getId());
			TbQoReasignacionActividad persisted = null;
			if (send != null && send.getId()!= null  ) {
				persisted = this.findParametroById(send.getId());
				persisted = this.updateParametro(send, persisted);
				return persisted;
			} else if (send != null && send.getId() == null) {
				// send.setFechaActualizacion( new Timestamp(System.currentTimeMillis()) );
				// send.setFechaCreacion( new Timestamp(System.currentTimeMillis()) );
				persisted = reasignacionRepository.add(send);
				return persisted;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo reasignacion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la CausaNegativa " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbQoReasignacionActividad updateParametro(TbQoReasignacionActividad send,
			TbQoReasignacionActividad persisted) throws RelativeException {
		try {
			if (send.getIdUsuarioAntiguo() != null)
				persisted.setIdUsuarioAntiguo(send.getIdUsuarioAntiguo());
			if (send.getIdUsuarioNuevo() != null)
				persisted.setIdUsuarioNuevo(send.getIdUsuarioAntiguo());
			if (send.getObservacion() != null)
				persisted.setObservacion(send.getObservacion());
		
			return reasignacionRepository.update(persisted);

		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando CausaNegativa " + e.getMessage());
		}
	}



}
