package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoPatrimonio;
import com.relative.quski.repository.PatrimonioRepository;
import com.relative.quski.repository.spec.PatrimonioByIdClienteSpec;
/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "patrimonioRepository")
public class PatrimonioRepositoryImp extends GeneralRepositoryImp<Long, TbQoPatrimonio> implements PatrimonioRepository{
	@Inject
	Logger log;
	
	@Override
	public List<TbQoPatrimonio> findByIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoPatrimonio> list = findAllBySpecification( new PatrimonioByIdClienteSpec( id ));
			log.info("ESTOY BUSCANDO PATRIMONIOS SIZE =====> " + list.size());
			if( !list.isEmpty() ) {
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}
}
