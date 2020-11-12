package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoTelefonoCliente;
import com.relative.quski.repository.TelefonoClienteRepository;
import com.relative.quski.repository.spec.TelefonoByIdClienteSpec;


@Stateless(mappedName = "telefonoClienteRepository")
public class TelefonoClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoTelefonoCliente> implements TelefonoClienteRepository {
	@Inject
	Logger log;
	
	@Override
	public List<TbQoTelefonoCliente> findByIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoTelefonoCliente> list = findAllBySpecification(new TelefonoByIdClienteSpec( id ));
			log.info("ESTOY BUSCANDO TELEFONOS SIZE =====> " + list.size());
			if(!list.isEmpty()) {
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR");
		}
	}

}
