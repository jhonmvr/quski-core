package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.repository.ProcesoRepository;
import com.relative.quski.repository.spec.ProcesoByIdSpec;
import com.relative.quski.util.QuskiOroConstantes;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "procesoRepository")
public class ProcesoRepositoryImp extends GeneralRepositoryImp<Long, TbQoProceso> implements ProcesoRepository {

	@Override
	public TbQoProceso findById(Long id) throws RelativeException {
		try {
			List<TbQoProceso> list = this.findAllBySpecification( new ProcesoByIdSpec( id ) );
			if( !list.isEmpty() ) {
				if( list.size() == 1 ) {
					return list.get(0);
				}else {
					throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION);
				}
			}else {
				return null;
			}
		}catch(RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMensaje());
		}
	}
}
