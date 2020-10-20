package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoDireccionCliente;
import com.relative.quski.repository.DireccionClienteRepository;
import com.relative.quski.repository.spec.DireccionByIdClienteAndTipoDireccionSpec;
import com.relative.quski.repository.spec.DireccionByIdClienteSpec;


@Stateless(mappedName = "direccionClienteRepository")
public class DireccionClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoDireccionCliente> implements DireccionClienteRepository {

	@Override
	public List<TbQoDireccionCliente> findByIdCliente(Long id) throws RelativeException {
		try {
			return findAllBySpecification(
					new DireccionByIdClienteSpec( id ));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	@Override
	public List<TbQoDireccionCliente> findByIdClienteAndTipoDireccion(Long idC, String tipoDireccion) throws RelativeException {
		try {
			return findAllBySpecification(
					new DireccionByIdClienteAndTipoDireccionSpec( idC, tipoDireccion ));
		} catch (Exception e) {

			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}


	
	

	
}
