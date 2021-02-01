package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoDireccionCliente;
import com.relative.quski.repository.DireccionClienteRepository;
import com.relative.quski.repository.spec.DireccionAllByIdClienteSpec;
import com.relative.quski.repository.spec.DireccionByIdClienteAndTipoDireccionSpec;
import com.relative.quski.repository.spec.DireccionByIdClienteSpec;


@Stateless(mappedName = "direccionClienteRepository")
public class DireccionClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoDireccionCliente> implements DireccionClienteRepository {
	@Inject
	Logger log;
	@Override
	public List<TbQoDireccionCliente> findByIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoDireccionCliente> list = findAllBySpecification(new DireccionByIdClienteSpec( id ));
			log.info("ESTOY BUSCANDO DIRECCIONES SIZE =====> " + list.size());
			if(!list.isEmpty()) {
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
	public List<TbQoDireccionCliente> findByIdClienteAndTipoDireccion(Long idC, String tipoDireccion) throws RelativeException {
		try {
			return findAllBySpecification(
					new DireccionByIdClienteAndTipoDireccionSpec( idC, tipoDireccion ));
		} catch (Exception e) {

			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	@Override
	public List<TbQoDireccionCliente> findAllByIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoDireccionCliente> list = findAllBySpecification(new DireccionAllByIdClienteSpec( id ));
			log.info("ESTOY BUSCANDO DIRECCIONES SIZE =====> " + list.size());
			if(!list.isEmpty()) {
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
	public void deleteAllByIdCliente(Long id) throws RelativeException {
		try {
			if(id == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"BORRAR DIRECCION DEL CLIENTE ID ES OBLIGATORIO");
			}
			StringBuilder queryStr =  new StringBuilder();
			queryStr.append("DELETE FROM tb_qo_direccion_cliente where 1=1 ");
			
			queryStr.append("and id_cliente =:idCliente ");
			Query query = this.getEntityManager().createNativeQuery(queryStr.toString());
			
			query.setParameter("idCliente", id);
			query.executeUpdate();
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BORRAR DIRECCION DEL CLIENTE");
		}
	}
	
}
