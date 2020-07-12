package com.relative.quski.repository.imp;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoExcepcione;
import com.relative.quski.repository.ExcepcionesRepository;
import com.relative.quski.repository.spec.ExcepcionByIdSpec;
import com.relative.quski.repository.spec.ExcepcionesByIdClienteSpec;
import com.relative.quski.repository.spec.ExcepcionesByIdNegociacionSpec;
import com.relative.quski.repository.spec.ExcepcionesByTipoExcepcionAndIdNegociacionAndestadoExcepcionSpec;
import com.relative.quski.repository.spec.ExcepcionesByTipoExcepcionAndIdNegociacionSpec;

/**
 * Session Bean implementation class AgenciaRepositoryImp
 */
@Stateless(mappedName = "agenciaRepository")
public class ExcepcionesRepositoryImp extends GeneralRepositoryImp<Long, TbQoExcepcione> implements ExcepcionesRepository  {
	@Inject
	Logger log;
    /**
     * Default constructor. 
     */
    public ExcepcionesRepositoryImp() {
    	//Void
    }
	@Override
	public TbQoExcepcione findById(Long id) throws RelativeException {
		try {
			List<TbQoExcepcione> listExcepciones = this.findAllBySpecification(new ExcepcionByIdSpec( id ));
			log.info("NUMERO DE EXCEPCIONES RECUPERADAS ---------------------------> " + listExcepciones.size());
			if (!listExcepciones.isEmpty()) {
				if (listExcepciones.size() <= 1) {
					log.info("Retorna el valor de la lista ------------------------> " + listExcepciones);
					return listExcepciones.get(0);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_READ, "EXISTE MAS DE UNA EXCEPCION, ERROR DE DESARROLLO (IMP)");
				}
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR EN LA BUSQUEDA, NO EXISTEN EXCEPCIONES CON ESE ID (IMP)");
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Ocurrio un error al leer Excepciones: " + e.getMessage());
		}
	}
	@Override
	public List<TbQoExcepcione> findByIdNegociacion(Long idNegociacion) throws RelativeException {
		try {
        	return findAllBySpecification( new ExcepcionesByIdNegociacionSpec( idNegociacion ) );
    	} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer Excepciones: " + e.getMessage());
    	}
	}
	@Override
	public List<TbQoExcepcione> findByIdCliente(Long idCliente) throws RelativeException {
		try {
        	return findAllBySpecification( new ExcepcionesByIdClienteSpec( idCliente ) );
    	} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer Excepciones: " + e.getMessage());
    	}
	}
	@Override
	public List<TbQoExcepcione> findByTipoExcepcionAndIdNegociacion(String tipoExcepcion, Long idNegociacion) throws RelativeException {
		try {
        	return findAllBySpecification( new ExcepcionesByTipoExcepcionAndIdNegociacionSpec( tipoExcepcion, idNegociacion ) );
    	} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer Excepciones: " + e.getMessage());
    	}
	}
	@Override
	public TbQoExcepcione findByTipoExcepcionAndIdNegociacionAndestadoExcepcion(String tipoExcepcion,
			Long idNegociacion, String estadoExcepcion) throws RelativeException {
		try {
			List<TbQoExcepcione> listExcepciones = this.findAllBySpecification( new ExcepcionesByTipoExcepcionAndIdNegociacionAndestadoExcepcionSpec( tipoExcepcion,  idNegociacion, estadoExcepcion ) );
			log.info("NUMERO DE EXCEPCIONES RECUPERADAS ---------------------------> " + listExcepciones.size());
			if (!listExcepciones.isEmpty()) {
				if (listExcepciones.size() <= 1) {
					log.info("Retorna el valor de la lista ------------------------> " + listExcepciones);
					return listExcepciones.get(0);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_READ, "EXISTE MAS DE UNA EXCEPCION, ERROR DE DESARROLLO (IMP)");
				}
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR EN LA BUSQUEDA, NO EXISTEN EXCEPCIONES CON ESE ID (IMP)");
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Ocurrio un error al leer Excepciones: " + e.getMessage());
		}
	}
}
