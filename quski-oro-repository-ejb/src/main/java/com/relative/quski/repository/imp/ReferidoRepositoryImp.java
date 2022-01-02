package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoReferido;
import com.relative.quski.repository.ReferidoRepository;
import com.relative.quski.repository.spec.ReferidoByIdNegociacion;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "referidoRepository")
public class ReferidoRepositoryImp extends GeneralRepositoryImp<Long, TbQoReferido>
		implements ReferidoRepository {

	@Inject
	Logger log;

	@Override
	public TbQoReferido findByIdNegociacion(Long id) throws RelativeException {
		try {
			List<TbQoReferido> tmp = this.findAllBySpecification(new ReferidoByIdNegociacion(id));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {

			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE ENCONTRAR REFERIDOS PARA ID NEGOCIACION:" + id);
		}
	}

	
	

}
