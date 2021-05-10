package com.relative.quski.repository.imp;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.repository.RegistrarPagoRepository;
import com.relative.quski.repository.spec.RegistrarPagoByIdClientePagoSpec;
import com.relative.quski.repository.spec.RegistrarPagoByIdCreditoSpec;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "registraPagoRepository")
public class RegistrarPagoRepositoryImp extends GeneralRepositoryImp<Long, TbQoRegistrarPago> implements RegistrarPagoRepository {

	@Override
	public List<TbQoRegistrarPago> findByIdClientePago(Long idClientePago) throws RelativeException {
		try {
			List<TbQoRegistrarPago> tmp;
			tmp= this.findAllBySpecification(new RegistrarPagoByIdClientePagoSpec(idClientePago));
			if(tmp !=null && !tmp.isEmpty()) {
				return tmp;
			}
			// TODO Auto-generated method stub
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,"AL BUSCAR EN TB_QO_REGISTRAR_PAGO POR ID_PAGO");
		}
	}
	@Override
	public List<TbQoRegistrarPago> findByIdCredito(Long idCredito) throws RelativeException {
		try {
			List<TbQoRegistrarPago> tmp;
			tmp= this.findAllBySpecification(new RegistrarPagoByIdCreditoSpec(idCredito));
			if(tmp !=null && !tmp.isEmpty()) {
				return tmp;
			}
			// TODO Auto-generated method stub
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,"AL BUSCAR EN TB_QO_REGISTRAR_PAGO POR ID_PAGO");
		}
	}
	@Override
	public void borrarPagos(Long idCredito) throws RelativeException {

		try {
			if(idCredito == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"BORRAR PAGOS ID CREDITO ES OBLIGATORIO");
			}
		
			StringBuilder queryStr =  new StringBuilder();
			queryStr.append("DELETE FROM tb_qo_registrar_pago where 1=1 ");
			
			queryStr.append("and id_credito =:idCredito ");
			Query query = this.getEntityManager().createNativeQuery(queryStr.toString());
			
			query.setParameter("idCredito", idCredito);
			query.executeUpdate();
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BORRAR PAGOS ID CREDITO");
		}
		
	
	}
	
}
