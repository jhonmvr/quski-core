package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.repository.NegociacionRepository;
import com.relative.quski.repository.spec.NegociacionByIdSpec;
import com.relative.quski.util.QuskiOroConstantes;


@Stateless(mappedName = "negociacionRepository")
public class NegociacionRepositoryImp extends GeneralRepositoryImp<Long, TbQoNegociacion> implements NegociacionRepository {
	@Inject
	Logger log;
	
	@Override
	public TbQoNegociacion findById( Long id ) throws RelativeException{
		try {
			List<TbQoNegociacion> list = this.findAllBySpecification(new NegociacionByIdSpec( id ));
			if (!list.isEmpty()) {
				log.info("ESTOY BUSCANDO UNA NEGOCIACION =====> " + list.size());
				if (list.size() <= 1) {
					return list.get(0);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_READ,
							"EXISTE MAS DE UNA NEGOCIACION, ERROR DE DESARROLLO (IMP)");
				}
			} else {
				return null;
			}
		} catch(RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA);
		}
	}
	

}
