package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;


import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.repository.ExcepcionesRepository;
import com.relative.quski.repository.spec.ExcepcionByIdSpec;
import com.relative.quski.repository.spec.ExcepcionesByIdClienteSpec;
import com.relative.quski.repository.spec.ExcepcionesByIdNegociacionSpec;
import com.relative.quski.repository.spec.ExcepcionesByTipoExcepcionAndIdNegociacionAndCaracteristicaSpec;
import com.relative.quski.repository.spec.ExcepcionesByTipoExcepcionAndIdNegociacionAndestadoExcepcionSpec;
import com.relative.quski.repository.spec.ExcepcionesByTipoExcepcionAndIdNegociacionSpec;

/**
 * Session Bean implementation class AgenciaRepositoryImp
 */
@Stateless(mappedName = "excepcionesRepository")
public class ExcepcionesRepositoryImp extends GeneralRepositoryImp<Long, TbQoExcepcion>
		implements ExcepcionesRepository {
	@Inject
	Logger log;
	String mensaje = "OCURRIO UN ERROR AL LEER LAS EXCEPCIONES";

	/**
	 * Default constructor.
	 */
	public ExcepcionesRepositoryImp() {
		// Void
	}

	@Override
	public TbQoExcepcion findById(Long id) throws RelativeException {
		try {
			List<TbQoExcepcion> listExcepciones = this.findAllBySpecification(new ExcepcionByIdSpec(id));
			if (!listExcepciones.isEmpty()) {
				if (listExcepciones.size() <= 1) {
					return listExcepciones.get(0);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_READ,
							"EXISTE MAS DE UNA EXCEPCION, ERROR DE DESARROLLO (IMP)");
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ + this.mensaje + e.getMessage());
		}
	}

	@Override
	public List<TbQoExcepcion> findByIdNegociacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, Long idNegociacion) throws RelativeException {
		try {
			return findAllBySpecificationPaged(new ExcepcionesByIdNegociacionSpec(idNegociacion), startRecord, pageSize,
					sortFields, sortDirections);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ + this.mensaje + e.getMessage());
		}
	}

	@Override
	public List<TbQoExcepcion> findByIdNegociacion(Long idNegociacion) throws RelativeException {
		try {
			List<TbQoExcepcion>  list = findAllBySpecification(new ExcepcionesByIdNegociacionSpec(idNegociacion));
			if( !list.isEmpty() ) {
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ + this.mensaje + e.getMessage());
		}
	}

	@Override
	public Long countByIdNegociacion(Long idNegociacion) throws RelativeException {
		try {
			return countBySpecification(new ExcepcionesByIdNegociacionSpec(idNegociacion));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM + this.mensaje + e.getMessage());
		}
	}

	@Override
	public List<TbQoExcepcion> findByIdCliente(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, Long idCliente) throws RelativeException {
		try {
			return findAllBySpecificationPaged(new ExcepcionesByIdClienteSpec(idCliente), startRecord, pageSize,
					sortFields, sortDirections);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ + this.mensaje + e.getMessage());
		}
	}

	@Override
	public List<TbQoExcepcion> findByIdCliente(Long idCliente) throws RelativeException {
		try {
			return findAllBySpecification(new ExcepcionesByIdClienteSpec(idCliente));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ + this.mensaje + e.getMessage());
		}
	}

	@Override
	public Long countByIdCliente(Long idCliente) throws RelativeException {
		try {
			return countBySpecification(new ExcepcionesByIdClienteSpec(idCliente));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					" No  se puedieron contar los registros " + e.getMessage());
		}
	}

	@Override
	public List<TbQoExcepcion> findByTipoExcepcionAndIdNegociacion(String tipoExcepcion, Long idNegociacion)
			throws RelativeException {
		try {
			return findAllBySpecification(
					new ExcepcionesByTipoExcepcionAndIdNegociacionSpec(tipoExcepcion, idNegociacion));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Ocurrio un error al leer Excepciones: " + e.getMessage());
		}
	}

	@Override
	public List<TbQoExcepcion> findByTipoExcepcionAndIdNegociacion(int startRecord, Integer pageSize,
			String sortFields, String sortDirections, String tipoExcepcion, Long idNegociacion)
			throws RelativeException {
		try {
			return findAllBySpecificationPaged(
					new ExcepcionesByTipoExcepcionAndIdNegociacionSpec(tipoExcepcion, idNegociacion), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					" Ocurrio un error al leer Excepciones: " + e.getMessage());
		}
	}

	@Override
	public Long countByTipoExcepcionAndIdNegociacion(String tipoExcepcion, Long idNegociacion)
			throws RelativeException {
		try {
			return countBySpecification(
					new ExcepcionesByTipoExcepcionAndIdNegociacionSpec(tipoExcepcion, idNegociacion));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					" No  se puedieron contar los registros " + e.getMessage());
		}
	}

	@Override
	public TbQoExcepcion findByTipoExcepcionAndIdNegociacionAndestadoExcepcion(Long idNegociacion,
			String tipoExcepcion, EstadoExcepcionEnum estadoExcepcion) throws RelativeException {
		try {
			List<TbQoExcepcion> listExcepciones = this.findAllBySpecification(
					new ExcepcionesByTipoExcepcionAndIdNegociacionAndestadoExcepcionSpec(tipoExcepcion, idNegociacion,
							estadoExcepcion));
			if (!listExcepciones.isEmpty()) {
				if (listExcepciones.size() <= 1) {
					return listExcepciones.get(0);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_READ,
							"EXISTE MAS DE UNA EXCEPCION, ERROR DE DESARROLLO (IMP)");
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Ocurrio un error al leer Excepciones: " + e.getMessage());
		}
	}

	@Override
	public List<TbQoExcepcion> findByTipoExcepcionAndIdNegociacionAndCaracteristica(int startRecord, Integer pageSize,
			String sortFields, String sortDirections, String tipoExcepcion, Long idNegociacion, String caracteristica)
			throws RelativeException {
		try {
			return findAllBySpecificationPaged(new ExcepcionesByTipoExcepcionAndIdNegociacionAndCaracteristicaSpec(
					tipoExcepcion, idNegociacion, caracteristica), startRecord, pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					" Ocurrio un error al leer Excepciones: " + e.getMessage());
		}
	}

	@Override
	public Long countByTipoExcepcionAndIdNegociacionAndCaracteristica(String tipoExcepcion, Long idNegociacion,
			String caracteristica) throws RelativeException {
		try {
			return countBySpecification(new ExcepcionesByTipoExcepcionAndIdNegociacionAndCaracteristicaSpec(
					tipoExcepcion, idNegociacion, caracteristica));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					" No  se puedieron contar los registros " + e.getMessage());
		}
	}

	@Override
	public List<TbQoExcepcion> findByTipoExcepcionAndIdNegociacionAndCaracteristica(String tipoExcepcion,
			Long idNegociacion, String caracteristica) throws RelativeException {
		try {
			return findAllBySpecification(new ExcepcionesByTipoExcepcionAndIdNegociacionAndCaracteristicaSpec(
					tipoExcepcion, idNegociacion, caracteristica));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Ocurrio un error al leer Excepciones: " + e.getMessage());
		}
	}

}
