package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoReferenciaPersonal;
import com.relative.quski.repository.ReferenciaPersonalRepository;
import com.relative.quski.repository.spec.ReferenciaPersonalByIdClienteSpec;

@Stateless(mappedName = "referenciaPersonalRepository")
public class ReferenciaPersonalRepositoryImp extends GeneralRepositoryImp<Long, TbQoReferenciaPersonal>
		implements ReferenciaPersonalRepository {

	@Override
	public List<TbQoReferenciaPersonal> findByIdCliente(Long id) throws RelativeException {
		try {
			return findAllBySpecification(new ReferenciaPersonalByIdClienteSpec(id));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR referencias personales");

		}

	}
}
