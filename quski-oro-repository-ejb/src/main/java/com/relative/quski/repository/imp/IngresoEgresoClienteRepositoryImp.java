package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoIngresoEgresoCliente;
import com.relative.quski.repository.IngresoEgresoClienteRepository;
import com.relative.quski.repository.spec.IngresoEgresoByIdClienteSpec;


@Stateless(mappedName = "ingresoEgresoClienteRepository")
public class IngresoEgresoClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoIngresoEgresoCliente> implements IngresoEgresoClienteRepository {

	@Override
	public List<TbQoIngresoEgresoCliente> findByIdCliente(Long id) throws RelativeException {
		try {
			return findAllBySpecification(new IngresoEgresoByIdClienteSpec( id ));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}
	
	

	
}
