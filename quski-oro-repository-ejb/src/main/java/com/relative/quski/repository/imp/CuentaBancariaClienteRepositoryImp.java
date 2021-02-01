package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoCuentaBancariaCliente;
import com.relative.quski.repository.CuentaBancariaRepository;
import com.relative.quski.repository.spec.CuentaBancariaByIdClienteSinEstadoSpec;
import com.relative.quski.repository.spec.CuentaBancariaByIdClienteSpec;
import com.relative.quski.repository.spec.CuentaByClienteAndCuentaSpec;


@Stateless(mappedName = "cuentaBancariaRepository")
public class CuentaBancariaClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoCuentaBancariaCliente> implements CuentaBancariaRepository {
	@Inject
	Logger log;
	@Override
	public List<TbQoCuentaBancariaCliente> findByIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoCuentaBancariaCliente> list = findAllBySpecification(new CuentaBancariaByIdClienteSpec( id ));
			log.info("ESTOY BUSCANDO CUENTAS SIZE =====> " + list.size());

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
	public List<TbQoCuentaBancariaCliente> findByAllIdCliente(Long id) throws RelativeException {
		try {
			List<TbQoCuentaBancariaCliente> list = findAllBySpecification(new CuentaBancariaByIdClienteSinEstadoSpec( id ));
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
	public TbQoCuentaBancariaCliente findByClienteAndCuenta(Long id, String cuenta) throws RelativeException {
		try {
			List<TbQoCuentaBancariaCliente> list = findAllBySpecification( new CuentaByClienteAndCuentaSpec( id, cuenta) );
			if(list.isEmpty() || list.size() > 1) {
				return null;
			}
			return list.get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR");
		}
	}
	@Override
	public void deleteAllByIdCliente(Long id) throws RelativeException {
		try {
			if(id == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"BORRAR CUENTA BANCARIA ID ES OBLIGATORIO");
			}
			StringBuilder queryStr =  new StringBuilder();
			queryStr.append("DELETE FROM tb_qo_cuenta_bancaria_cliente where 1=1 ");
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
