package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoNegociacionCalculo;
import com.relative.quski.repository.NegociacionCalculoRepository;
import com.relative.quski.repository.spec.NegociacionCalculoByIdSpec;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "negociacionCalculoRepositoryImp")
public class NegociacionCalculoRepositoryImp extends GeneralRepositoryImp<Long, TbQoNegociacionCalculo>
		implements NegociacionCalculoRepository {

	@Override
	public List<TbQoNegociacionCalculo> findById(long id) throws RelativeException {
		try {
			return findAllBySpecification(new NegociacionCalculoByIdSpec(id));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Al buscar negociacionCalculo por id");
		}

	}
}
