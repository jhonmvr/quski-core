package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoDatoTrabajoCliente;
import com.relative.quski.repository.DatoTrabajoClienteRepository;
import com.relative.quski.repository.spec.DatoTrabajoByIdClienteSpec;


@Stateless(mappedName = "datoTrabajoClienteRepository")
public class DatoTrabajoClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoDatoTrabajoCliente> implements DatoTrabajoClienteRepository {

	@Override
	public List<TbQoDatoTrabajoCliente> findByIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoDatoTrabajoCliente> list = findAllBySpecification(new DatoTrabajoByIdClienteSpec( id ));
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
