package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoIngresoEgresoCliente;
import com.relative.quski.repository.IngresoEgresoClienteRepository;
import com.relative.quski.repository.spec.IngresoEgresoByIdClienteSpec;
import com.relative.quski.repository.spec.IngresoEgresoByIdSpec;


@Stateless(mappedName = "ingresoEgresoClienteRepository")
public class IngresoEgresoClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoIngresoEgresoCliente> implements IngresoEgresoClienteRepository {
	@Inject
	Logger log;
	
	@Override
	public List<TbQoIngresoEgresoCliente> findByIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoIngresoEgresoCliente>  list =  findAllBySpecification(new IngresoEgresoByIdClienteSpec( id ));
			if(!list.isEmpty() ) {
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}
	@Override
	public TbQoIngresoEgresoCliente findById(Long id) throws RelativeException {
		try {
			List<TbQoIngresoEgresoCliente>  list =  findAllBySpecification(new IngresoEgresoByIdSpec( id ));
			if(!list.isEmpty() ) {
				if(list.size() <= 1) {
					return list.get(0);
				}else {
					return null;
				}
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}
	

	
}
