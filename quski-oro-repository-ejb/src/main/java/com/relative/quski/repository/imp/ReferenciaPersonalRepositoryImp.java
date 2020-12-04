package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoReferenciaPersonal;
import com.relative.quski.repository.ReferenciaPersonalRepository;
import com.relative.quski.repository.spec.ReferenciaPersonalAllByIdClienteSpec;
import com.relative.quski.repository.spec.ReferenciaPersonalByIdClienteSpec;

@Stateless(mappedName = "referenciaPersonalRepository")
public class ReferenciaPersonalRepositoryImp extends GeneralRepositoryImp<Long, TbQoReferenciaPersonal>
		implements ReferenciaPersonalRepository {
	@Inject
	Logger log;
	
	@Override
	public List<TbQoReferenciaPersonal> findByIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoReferenciaPersonal> list = findAllBySpecification(new ReferenciaPersonalByIdClienteSpec(id));
			log.info("ESTOY BUSCANDO REFERENCIAS SIZE =====> " + list.size());

			if(!list.isEmpty() ) {
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR referencias personales");

		}

	}

	@Override
	public List<TbQoReferenciaPersonal> findAllByIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoReferenciaPersonal> list = findAllBySpecification(new ReferenciaPersonalAllByIdClienteSpec(id));

			if(!list.isEmpty() ) {
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR referencias personales");
		}
	}
}
