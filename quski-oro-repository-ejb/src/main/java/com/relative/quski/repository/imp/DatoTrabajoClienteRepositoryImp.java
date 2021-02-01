package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoDatoTrabajoCliente;
import com.relative.quski.repository.DatoTrabajoClienteRepository;
import com.relative.quski.repository.spec.DatoTrabajoAllByIdClienteSpec;
import com.relative.quski.repository.spec.DatoTrabajoByIdClienteSpec;
import com.relative.quski.repository.spec.DatoTrabajoByIdSoftbankSpec;


@Stateless(mappedName = "datoTrabajoClienteRepository")
public class DatoTrabajoClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoDatoTrabajoCliente> implements DatoTrabajoClienteRepository {
	@Inject
	Logger log;
	@Override
	public List<TbQoDatoTrabajoCliente> findByIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoDatoTrabajoCliente> list = findAllBySpecification(new DatoTrabajoByIdClienteSpec( id ));
			log.info("ESTOY BUSCANDO DATOS TRABAJO SIZE =====> " + list.size());
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
	public TbQoDatoTrabajoCliente findByIdSoftbank(Long id) throws RelativeException {
		try {
			List<TbQoDatoTrabajoCliente> list = findAllBySpecification(new DatoTrabajoByIdSoftbankSpec( id ));
			log.info("ESTOY BUSCANDO DATOS TRABAJO SIZE =====> " + list.size());
			if(!list.isEmpty()) {
				if( list.size() <= 1) {
					return list.get(0);
				}else {
				 return null;
				}
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR");
		}
	}
	@Override
	public List<TbQoDatoTrabajoCliente> findAllByIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoDatoTrabajoCliente> list = findAllBySpecification(new DatoTrabajoAllByIdClienteSpec( id ));
			log.info("ESTOY BUSCANDO DATOS TRABAJO SIZE =====> " + list.size());
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
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"BORRAR DATOS DE TRABAJO ID CREDITO ES OBLIGATORIO");
			}
			StringBuilder queryStr =  new StringBuilder();
			queryStr.append("DELETE FROM tb_qo_dato_trabajo_cliente where 1=1 ");
			
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
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BORRAR DATOS DE TRABAJO");
		}
	}
}
