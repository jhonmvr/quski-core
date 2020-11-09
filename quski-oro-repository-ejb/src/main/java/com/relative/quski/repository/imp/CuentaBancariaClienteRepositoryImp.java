package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoCuentaBancariaCliente;
import com.relative.quski.repository.CuentaBancariaRepository;
import com.relative.quski.repository.spec.CuentaBancariaByIdClienteSpec;


@Stateless(mappedName = "cuentaBancariaRepository")
public class CuentaBancariaClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoCuentaBancariaCliente> implements CuentaBancariaRepository {

	@Override
	public List<TbQoCuentaBancariaCliente> findByIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoCuentaBancariaCliente> list = findAllBySpecification(new CuentaBancariaByIdClienteSpec( id ));
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
