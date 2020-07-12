package com.relative.quski.repository.imp;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoRiesgoAcumulado;
import com.relative.quski.repository.RiesgoAcumuladoRepository;
import com.relative.quski.repository.spec.RiesgoAcumuladoByIdClienteSpec;
import com.relative.quski.repository.spec.RiesgoAcumuladoByIdSpec;

/**
 * Session Bean implementation class AgenciaRepositoryImp
 */
@Stateless(mappedName = "agenciaRepository")
public class RiesgoAcumuladoRepositoryImp extends GeneralRepositoryImp<Long, TbQoRiesgoAcumulado> implements RiesgoAcumuladoRepository  {
	@Inject
	Logger log;
    /**
     * Default constructor. 
     */
    public RiesgoAcumuladoRepositoryImp() {
    	//Void
    }
	@Override
	public TbQoRiesgoAcumulado findById( Long id ) throws RelativeException {
		try {
			List<TbQoRiesgoAcumulado> listRiesgoAcumulado = this.findAllBySpecification(new RiesgoAcumuladoByIdSpec( id ));
			log.info("NUMERO DE EXCEPCIONES RECUPERADAS ---------------------------> " + listRiesgoAcumulado.size());
			if (!listRiesgoAcumulado.isEmpty()) {
				if (listRiesgoAcumulado.size() <= 1) {
					log.info("Retorna el valor de la lista ------------------------> " + listRiesgoAcumulado);
					return listRiesgoAcumulado.get(0);
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
	public List<TbQoRiesgoAcumulado> findByIdCliente( Long idCliente ) throws RelativeException {
		try {
        	return findAllBySpecification( new RiesgoAcumuladoByIdClienteSpec( idCliente ) );
    	} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer Excepciones: " + e.getMessage());
    	}
	}
}
