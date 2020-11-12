package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoRubro;
import com.relative.quski.repository.RubroRepository;
import com.relative.quski.repository.spec.RubrosByIdCreditoSpec;
import com.relative.quski.util.QuskiOroConstantes;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "rubroRepository")
public class RubroRepositoryImp extends GeneralRepositoryImp<Long, TbQoRubro> implements RubroRepository{

	@Override
	public List<TbQoRubro> findByIdCredito(Long id) throws RelativeException {
		try {
			List<TbQoRubro> list = this.findAllBySpecification( new RubrosByIdCreditoSpec( id ) );
			if(!list.isEmpty()) {
				return list;
			}else {
				list = null;
				return list;
			}
		}catch(Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	
	
}
