package com.relative.quski.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.repository.CreditoNegociacionRepository;
import com.relative.quski.repository.spec.AsignacionByParamsSpec;
/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "creditoNegociacionRepository")
public class CreditoNegociacionImp  extends GeneralRepositoryImp<Long, TbQoCreditoNegociacion> implements CreditoNegociacionRepository {

	@Override
	public List<TbQoCreditoNegociacion> findByParams(String codigoProceso,  String nombreProceso, String nombreAgencia,
			String cedula) throws RelativeException {
			try {
				return 	findAllBySpecification(
							new AsignacionByParamsSpec(codigoProceso,  nombreProceso, nombreAgencia, cedula)
						);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR CREDITONEGOCIACION POR PARAMETROS");
			}
	}

}
