package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoTelefonoCliente;
import com.relative.quski.repository.TelefonoClienteRepository;
import com.relative.quski.repository.spec.TelefonoAllByIdClienteSpec;
import com.relative.quski.repository.spec.TelefonoByClienteAndTipoSpec;
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

	@Override
	public TbQoTelefonoCliente findByClienteAndTipo(String identificacion, String tipoTelefono)
			throws RelativeException {
		try {
			List<TbQoTelefonoCliente> list = findAllBySpecification(new TelefonoByClienteAndTipoSpec( identificacion,tipoTelefono ));
		
			if(list != null && !list.isEmpty()) {
				return list.get(0);
			}
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR");
		}
	}

	@Override
	public List<TbQoTelefonoCliente> findAllByIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoTelefonoCliente> list = findAllBySpecification(new TelefonoAllByIdClienteSpec( id ));
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

	@Override
	public void deleteAllByIdCliente(Long id) throws RelativeException {
		try {
			if(id == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"BORRAR TELEFONOS DEL CLIENTE ID ES OBLIGATORIO");
			}
			StringBuilder queryStr =  new StringBuilder();
			queryStr.append("DELETE FROM tb_qo_telefono_cliente where 1=1 ");
			
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
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BORRAR TELEFONOS DEL CLIENTE");
		}
	}

}
