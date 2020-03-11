package com.relative.quski.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.repository.CreditoNegociacionRepository;
import com.relative.quski.repository.spec.AsignacionByParamsSpec;
/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "creditoNegociacionRepository")
public class CreditoNegociacionImp  extends GeneralRepositoryImp<Long, TbQoCreditoNegociacion> implements CreditoNegociacionRepository {

	@Override
	public List<TbQoCreditoNegociacion> findAsignacionByParams(String codigoProceso, String nombreAgencia, String nombreProceso, int cedula) {
			return 	findAllBySpecification(
						new AsignacionByParamsSpec(codigoProceso, nombreAgencia, nombreProceso, cedula)
					);
	}

}
