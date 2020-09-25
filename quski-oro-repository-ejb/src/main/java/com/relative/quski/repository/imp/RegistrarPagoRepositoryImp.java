package com.relative.quski.repository.imp;


import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.repository.RegistrarPagoRepository;
import com.relative.quski.repository.spec.RegistrarPagoByIdClientePagoSpec;

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
	
}
